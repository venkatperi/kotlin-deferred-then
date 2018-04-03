[kotlin-deferred-then](../../index.md) / [com.vperi.promise](../index.md) / [kotlinx.coroutines.experimental.Deferred](index.md) / [catch](./catch.md)

# catch

`fun <T, V> Deferred<`[`T`](catch.md#T)`>.catch(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, failureHandler: `[`FailureHandler`](../-failure-handler.md)`<`[`V`](catch.md#V)`>): Deferred<`[`V`](catch.md#V)`>`

Handles the failure case of [then](then.md)

### Parameters

`context` - context of the coroutine. The default value is [DefaultDispatcher](#).

`failureHandler` - Invoked when the current [Deferred](#) completes exceptionally