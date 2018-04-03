[![](https://jitpack.io/v/com.vperi/kotlin-promise.svg)](https://jitpack.io/#com.vperi/kotlin-promise) [![Build Status](https://travis-ci.org/venkatperi/kotlin-promise.svg?branch=master)](https://travis-ci.org/venkatperi/kotlin-promise)

# Module kotlin-deferred-then

`kotlin-deferred-then` provides `then/catch/finally` semantics to
kotlin coroutines.

## Examples

### Error Recovery with catch

````kotlin
runBlocking {
  val job = async(coroutineContext) {
    delay(200)
    throw Exception("Oops")
  }

  val result = job.catch(coroutineContext) {
    println("catch: ${it.message}")  //=> catch: Oops
    "World"
  }.await()

  println("result: Hello $result")  //=>result: Hello World
}
````

### finally

```kotlin
runBlocking {
  val job = async(coroutineContext) {
    delay(200)
    throw Exception("Oops")
  }

  val result = job.finally(coroutineContext) {
    when (it) {
      is Result.Value -> it.value
      is Result.Error -> "World"
    }
  }.await()

  println("result: Hello $result") //=>result: Hello World
}
```
