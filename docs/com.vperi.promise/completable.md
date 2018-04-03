[kotlin-deferred-then](../index.md) / [com.vperi.promise](index.md) / [completable](./completable.md)

# completable

`fun <T> completable(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = DefaultDispatcher, executor: `[`Executor`](-executor.md)`<`[`T`](completable.md#T)`>): Deferred<`[`T`](completable.md#T)`>`

Convenience function. A promise-like constructor that
returns a [Deferred](#). The single parameter [executor](completable.md#com.vperi.promise$completable(kotlin.coroutines.experimental.CoroutineContext, kotlin.SuspendFunction2((kotlin.Function1((com.vperi.promise.completable.T, kotlin.Unit)), kotlin.Function1((kotlin.Throwable, )), )))/executor) is
called immediately with two functions that complete
the returned [Deferred](#) successfully with a value of
type [T](completable.md#T) (or exceptionally with a [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))

### Parameters

`executor` - A function that is passed with the arguments
    resolve and reject. The [executor](completable.md#com.vperi.promise$completable(kotlin.coroutines.experimental.CoroutineContext, kotlin.SuspendFunction2((kotlin.Function1((com.vperi.promise.completable.T, kotlin.Unit)), kotlin.Function1((kotlin.Throwable, )), )))/executor) function is executed immediately,
    passing resolve and reject functions (it may be called
    before the constructor even returns the created object).