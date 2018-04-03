[kotlin-deferred-then](../../index.md) / [com.vperi.promise](../index.md) / [kotlinx.coroutines.experimental.Deferred](index.md) / [catch](./catch.md)

# catch

`fun <T> Deferred<`[`T`](catch.md#T)`>.catch(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, failureHandler: `[`FailureHandler`](../-failure-handler.md)`<`[`T`](catch.md#T)`>): Deferred<`[`T`](catch.md#T)`>`

Handles the failure case of [then](then.md)
