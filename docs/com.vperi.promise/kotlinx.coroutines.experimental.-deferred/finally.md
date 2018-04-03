[kotlin-deferred-then](../../index.md) / [com.vperi.promise](../index.md) / [kotlinx.coroutines.experimental.Deferred](index.md) / [finally](./finally.md)

# finally

`fun <T, V> Deferred<`[`T`](finally.md#T)`>.finally(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, handler: suspend (`[`Result`](../-result/index.md)`<`[`T`](finally.md#T)`>) -> `[`V`](finally.md#V)`): Deferred<`[`V`](finally.md#V)`>`

Returns a [Deferred](#). When the current [Deferred](#) completes,
[handler](finally.md#com.vperi.promise$finally(kotlinx.coroutines.experimental.Deferred((com.vperi.promise.finally.T)), kotlin.coroutines.experimental.CoroutineContext, kotlin.SuspendFunction1((com.vperi.promise.Result((com.vperi.promise.finally.T)), com.vperi.promise.finally.V)))/handler) is called with the success result (or failure exception).

If the invoked handler:
    - returns a value, the returned [Deferred](#) is completed with that value
    - throws an exception, the returned [Deferred](#) completes exceptionally with the exception

### Parameters

`context` - context of the coroutine. The default value is [DefaultDispatcher](#).

`handler` - Invoked with the result (success/failure) of the current [Deferred](#)