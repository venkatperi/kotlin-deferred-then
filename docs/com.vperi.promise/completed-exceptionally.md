[kotlin-deferred-then](../index.md) / [com.vperi.promise](index.md) / [completedExceptionally](./completed-exceptionally.md)

# completedExceptionally

`fun <T> completedExceptionally(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): Deferred<`[`T`](completed-exceptionally.md#T)`>`

Convenience function that returns a [Deferred](#) that is already
completed exceptionally with [error](completed-exceptionally.md#com.vperi.promise$completedExceptionally(kotlin.Throwable)/error)

### Parameters

`error` - the reason for exceptional completion