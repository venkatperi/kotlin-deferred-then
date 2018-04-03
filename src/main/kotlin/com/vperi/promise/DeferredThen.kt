@file:Suppress("unused")

package com.vperi.promise

import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlin.coroutines.experimental.CoroutineContext

typealias SuccessHandler<T, V> = suspend (T) -> V

typealias FailureHandler<T> = suspend (Throwable) -> T

typealias Executor<V> = suspend ((V) -> Unit, (Throwable) -> Unit) -> Unit

/**
 * Convenience function. A promise-like constructor that
 * returns a [Deferred]. The single parameter [executor] is
 * called immediately with two functions that complete
 * the returned [Deferred] successfully with a value of
 * type [T] (or exceptionally with a [Throwable])
 *
 * @param executor A function that is passed with the arguments
 *    resolve and reject. The [executor] function is executed immediately,
 *    passing resolve and reject functions (it may be called
 *    before the constructor even returns the created object).
 *
 *    If an error is thrown in the executor function, the [Deferred]
 *    is rejected.
 */
fun <T> completable(
  context: CoroutineContext = DefaultDispatcher,
  executor: Executor<T>
): Deferred<T> =
  CompletableDeferred<T>().apply {
    async(context) {
      executor(
        { it: T -> complete(it) },
        { it: Throwable -> completeExceptionally(it) })
    }
  }

/**
 * Convenience function that returns a already completed [Deferred]
 *
 * @param value Completed value of the [Deferred]
 */
fun <T> completed(value: T): Deferred<T> =
  CompletableDeferred(value)

/**
 * Convenience function that returns a [Deferred] that is already
 * completed exceptionally with [error]
 *
 * @param error the reason for exceptional completion
 */
fun <T> completedExceptionally(error: Throwable): Deferred<T> =
  CompletableDeferred<T>().apply {
    completeExceptionally(error)
  }

/**
 * Waits for the [Deferred] to complete and returns the result
 * (either success or failure) as a [Result].
 *
 * @return Result
 */
suspend fun <T> Deferred<T>.result(): Result<T> =
  try {
    Result.Value(await())
  } catch (e: Throwable) {
    Result.Error(e)
  }

/**
 * Returns a [Deferred]. When the current [Deferred] completes
 * [successHandler] (or [failureHandler] if it completes exceptionally)
 * is called with the success result (or failure reason).
 *
 * If the invoked handler:
 *    - returns a value, the returned [Deferred] is completed with that value
 *    - throws an exception, the returned [Deferred] completes exceptionally with the exception
 *
 * @param context context of the coroutine. The default value is [DefaultDispatcher].
 * @param successHandler Invoked when the current [Deferred] completes successfully
 * @param failureHandler Invoked when the current [Deferred] completes exceptionally
 */
fun <T, V> Deferred<T>.then(
  context: CoroutineContext = DefaultDispatcher,
  successHandler: SuccessHandler<T, V>,
  failureHandler: FailureHandler<V>?
): Deferred<V> {
  return async(context) {
    result().let {
      when (it) {
        is Result.Value -> successHandler(it.value)
        is Result.Error ->
          when (failureHandler) {
            null -> throw it.error
            else -> failureHandler(it.error)
          }
      }
    }
  }
}

/**
 * Handles the success case of [then]
 *
 * @param context context of the coroutine. The default value is [DefaultDispatcher].
 * @param successHandler Invoked when the current [Deferred] completes successfully
 */
fun <T, V> Deferred<T>.then(
  context: CoroutineContext = DefaultDispatcher,
  successHandler: SuccessHandler<T, V>
): Deferred<V> =
  then(context, successHandler, null)

/**
 * Special case when current [Deferred] returns [Nothing]
 *
 * @param context context of the coroutine. The default value is [DefaultDispatcher].
 * @param successHandler Invoked when the current [Deferred] completes successfully
 */
@JvmName("thenNothing")
fun <V> Deferred<Nothing>.then(
  context: CoroutineContext = DefaultDispatcher,
  successHandler: SuccessHandler<Unit, V>
): Deferred<V> =
  then(context, successHandler, null)

/**
 * unwrapping [then]
 *
 * @param context context of the coroutine. The default value is [DefaultDispatcher].
 * @param successHandler Invoked when the current [Deferred] completes successfully
 */
@JvmName("pThen")
fun <T, V> Deferred<Deferred<T>>.then(
  context: CoroutineContext = DefaultDispatcher,
  successHandler: SuccessHandler<T, V>
): Deferred<V> =
  async(context) {
    successHandler(await().await())
  }

/**
 * Handles the failure case of [then]
 *
 * @param context context of the coroutine. The default value is [DefaultDispatcher].
 * @param failureHandler Invoked when the current [Deferred] completes exceptionally
 */
fun <T, V> Deferred<T>.catch(
  context: CoroutineContext = DefaultDispatcher,
  failureHandler: FailureHandler<V>
): Deferred<V> =
  then(context, { it as V }, failureHandler)

/**
 * Returns a [Deferred]. When the current [Deferred] completes,
 * [handler] is called with the success result (or failure exception).
 *
 * If the invoked handler:
 *    - returns a value, the returned [Deferred] is completed with that value
 *    - throws an exception, the returned [Deferred] completes exceptionally with the exception
 *
 * @param context context of the coroutine. The default value is [DefaultDispatcher].
 * @param handler Invoked with the result (success/failure) of the current [Deferred]
 */
fun <T, V> Deferred<T>.finally(
  context: CoroutineContext = DefaultDispatcher,
  handler: suspend (Result<T>) -> V
): Deferred<V> =
  async(context) { handler(result()) }

