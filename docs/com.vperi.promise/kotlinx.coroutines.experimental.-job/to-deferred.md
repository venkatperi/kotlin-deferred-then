[kotlin-deferred-then](../../index.md) / [com.vperi.promise](../index.md) / [kotlinx.coroutines.experimental.Job](index.md) / [toDeferred](./to-deferred.md)

# toDeferred

`fun Job.toDeferred(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher): Deferred<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>`

Returns a [Deferred](#) which completes with the [Job](#)

### Parameters

`context` - context of the coroutine. The default value is [DefaultDispatcher](#).