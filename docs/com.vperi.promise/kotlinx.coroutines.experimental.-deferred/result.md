[kotlin-deferred-then](../../index.md) / [com.vperi.promise](../index.md) / [kotlinx.coroutines.experimental.Deferred](index.md) / [result](./result.md)

# result

`suspend fun <T> Deferred<`[`T`](result.md#T)`>.result(): `[`DeferredResult`](../-deferred-result/index.md)`<`[`T`](result.md#T)`>`

Waits for the [Deferred](#) to complete and returns the result
(either success or failure) as a [DeferredResult](../-deferred-result/index.md).

**Return**
DeferredResult

