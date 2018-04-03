@file:Suppress("unused")

package com.vperi.promise

import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlin.coroutines.experimental.CoroutineContext

typealias SuccessHandler<T, V> = suspend (T) -> V

typealias FailureHandler<T> = suspend (Throwable) -> T

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

suspend fun <T> Deferred<T>.result(): DeferredResult<T> =
  try {
    DeferredResult.Value(await())
  } catch (e: Exception) {
    DeferredResult.Error(e)
  }

/**
 * Returns a [Deferred]. When the current [Deferred] completes
 * [successHandler] (or [failureHandler] if it completes exceptionally)
 * is called with the success result (or failure reason).
 *
 * If the invoked handler:
 *    - returns a value, the returned [Deferred] is completed with that value
 *    - throws an exception, the returned [Deferred] completes exceptionally with the exception
 */
fun <T, V> Deferred<T>.then(
  context: CoroutineContext = DefaultDispatcher,
  successHandler: SuccessHandler<T, V>,
  failureHandler: FailureHandler<V>?
): Deferred<V> {
  return async(context) {
    result().let {
      when (it) {
        is DeferredResult.Value -> successHandler(it.value)
        is DeferredResult.Error ->
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
 */
fun <T, V> Deferred<T>.then(
  context: CoroutineContext = DefaultDispatcher,
  successHandler: SuccessHandler<T, V>
): Deferred<V> =
  then(context, successHandler, null)

/**
 * Special case when current [Deferred] returns [Nothing]
 */
@JvmName("thenNothing")
fun <V> Deferred<Nothing>.then(
  context: CoroutineContext = DefaultDispatcher,
  successHandler: SuccessHandler<Unit, V>
): Deferred<V> =
  then(context, successHandler, null)

/**
 * unwrapping [then]
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
 */
fun <T> Deferred<T>.catch(
  context: CoroutineContext = DefaultDispatcher,
  failureHandler: FailureHandler<T>
): Deferred<T> =
  then(context, { it }, failureHandler)

/**
 * Returns a [Deferred]. When the current [Deferred] completes,
 * [handler] is called with the success result (or failure exception).
 *
 * If the invoked handler:
 *    - returns a value, the returned [Deferred] is completed with that value
 *    - throws an exception, the returned [Deferred] completes exceptionally with the exception
 */
fun <T, V> Deferred<T>.finally(
  context: CoroutineContext = DefaultDispatcher,
  handler: suspend (DeferredResult<T>) -> V
): Deferred<V> =
  async(context) { handler(result()) }

