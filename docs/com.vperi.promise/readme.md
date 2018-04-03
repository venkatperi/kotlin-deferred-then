[kotlin-deferred-then](../index.md) / [com.vperi.promise](./index.md)

## Package com.vperi.promise

### Types

| Name | Summary |
|---|---|
| [DeferredResult](-deferred-result/index.md) | `sealed class DeferredResult<T>` |

### Type Aliases

| Name | Summary |
|---|---|
| [FailureHandler](-failure-handler.md) | `typealias FailureHandler<T> = suspend (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`T`](-failure-handler.md#T) |
| [SuccessHandler](-success-handler.md) | `typealias SuccessHandler<T, V> = suspend (`[`T`](-success-handler.md#T)`) -> `[`V`](-success-handler.md#V) |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [kotlinx.coroutines.experimental.Deferred](kotlinx.coroutines.experimental.-deferred/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [completed](completed.md) | `fun <T> completed(value: `[`T`](completed.md#T)`): Deferred<`[`T`](completed.md#T)`>`<br>Convenience function that returns a already completed [Deferred](#) |
| [completedExceptionally](completed-exceptionally.md) | `fun <T> completedExceptionally(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): Deferred<`[`T`](completed-exceptionally.md#T)`>`<br>Convenience function that returns a [Deferred](#) that is already completed exceptionally with [error](completed-exceptionally.md#com.vperi.promise$completedExceptionally(kotlin.Throwable)/error) |
