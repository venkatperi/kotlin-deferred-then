package com.vperi.promise

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class Examples {

  @Test
  fun example1A() {
    runBlocking {
      val job = async(coroutineContext) {
        delay(200)
        "World"
      }

      job.then(coroutineContext) {
        println("then: Hello $it")
      }
    }
  }

  @Test
  fun example2() {
    runBlocking {
      val job = async(coroutineContext) {
        delay(200)
        throw Exception("Oops")
      }

      val result = job.catch(coroutineContext) {
        println("catch: ${it.message}")
        "World"
      }.await()

      println("result: Hello $result")
    }
  }

  @Test
  fun example3() {
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

      println("result: Hello $result")
    }
  }

}