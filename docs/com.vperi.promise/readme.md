[kotlin-deferred-then](../index.md) / [com.vperi.promise](./index.md)

## Package com.vperi.promise

### Types

| Name | Summary |
|---|---|
| [Result](-result/index.md) | `sealed class Result<T>`<br>Represents the result of a Job/Deferred |

### Type Aliases

| Name | Summary |
|---|---|
| [Executor](-executor.md) | `typealias Executor<V> = suspend ((`[`V`](-executor.md#V)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [FailureHandler](-failure-handler.md) | `typealias FailureHandler<T> = suspend (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`T`](-failure-handler.md#T) |
| [SuccessHandler](-success-handler.md) | `typealias SuccessHandler<T, V> = suspend (`[`T`](-success-handler.md#T)`) -> `[`V`](-success-handler.md#V) |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [kotlinx.coroutines.experimental.Deferred](kotlinx.coroutines.experimental.-deferred/index.md) |  |
| [kotlinx.coroutines.experimental.Job](kotlinx.coroutines.experimental.-job/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [completable](completable.md) | `fun <T> completable(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, executor: `[`Executor`](-executor.md)`<`[`T`](completable.md#T)`>): Deferred<`[`T`](completable.md#T)`>`<br>Convenience function. A promise-like constructor that returns a [Deferred](#). The single parameter [executor](completable.md#com.vperi.promise$completable(kotlin.coroutines.experimental.CoroutineContext, kotlin.SuspendFunction2((kotlin.Function1((com.vperi.promise.completable.T, kotlin.Unit)), kotlin.Function1((kotlin.Throwable, )), )))/executor) is called immediately with two functions that complete the returned [Deferred](#) successfully with a value of type [T](completable.md#T) (or exceptionally with a [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [completed](completed.md) | `fun <T> completed(value: `[`T`](completed.md#T)`): Deferred<`[`T`](completed.md#T)`>`<br>Convenience function that returns a already completed [Deferred](#) |
| [completedExceptionally](completed-exceptionally.md) | `fun <T> completedExceptionally(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): Deferred<`[`T`](completed-exceptionally.md#T)`>`<br>Convenience function that returns a [Deferred](#) that is already completed exceptionally with [error](completed-exceptionally.md#com.vperi.promise$completedExceptionally(kotlin.Throwable)/error) |
