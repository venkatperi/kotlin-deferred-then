[kotlin-deferred-then](../../index.md) / [com.vperi.promise](../index.md) / [kotlinx.coroutines.experimental.Deferred](index.md) / [then](./then.md)

# then

`fun <T, V> Deferred<`[`T`](then.md#T)`>.then(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, successHandler: `[`SuccessHandler`](../-success-handler.md)`<`[`T`](then.md#T)`, `[`V`](then.md#V)`>, failureHandler: `[`FailureHandler`](../-failure-handler.md)`<`[`V`](then.md#V)`>?): Deferred<`[`V`](then.md#V)`>`

Returns a [Deferred](#). When the current [Deferred](#) completes
[successHandler](then.md#com.vperi.promise$then(kotlinx.coroutines.experimental.Deferred((com.vperi.promise.then.T)), kotlin.coroutines.experimental.CoroutineContext, kotlin.SuspendFunction1((com.vperi.promise.then.T, com.vperi.promise.then.V)), kotlin.SuspendFunction1((kotlin.Throwable, com.vperi.promise.then.V)))/successHandler) (or [failureHandler](then.md#com.vperi.promise$then(kotlinx.coroutines.experimental.Deferred((com.vperi.promise.then.T)), kotlin.coroutines.experimental.CoroutineContext, kotlin.SuspendFunction1((com.vperi.promise.then.T, com.vperi.promise.then.V)), kotlin.SuspendFunction1((kotlin.Throwable, com.vperi.promise.then.V)))/failureHandler) if it completes exceptionally)
is called with the success result (or failure reason).

If the invoked handler:
    - returns a value, the returned [Deferred](#) is completed with that value
    - throws an exception, the returned [Deferred](#) completes exceptionally with the exception

`fun <T, V> Deferred<`[`T`](then.md#T)`>.then(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, successHandler: `[`SuccessHandler`](../-success-handler.md)`<`[`T`](then.md#T)`, `[`V`](then.md#V)`>): Deferred<`[`V`](then.md#V)`>`

Handles the success case of [then](./then.md)

`@JvmName("thenNothing") fun <V> Deferred<`[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)`>.then(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, successHandler: `[`SuccessHandler`](../-success-handler.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, `[`V`](then.md#V)`>): Deferred<`[`V`](then.md#V)`>`

Special case when current [Deferred](#) returns [Nothing](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)

`@JvmName("pThen") fun <T, V> Deferred<Deferred<`[`T`](then.md#T)`>>.then(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, successHandler: `[`SuccessHandler`](../-success-handler.md)`<`[`T`](then.md#T)`, `[`V`](then.md#V)`>): Deferred<`[`V`](then.md#V)`>`

unwrapping [then](./then.md)

