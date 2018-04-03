package com.vperi.promise

import kotlinx.coroutines.experimental.cancelAndJoin
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class Examples {

  @Test
  fun example1A() {
    runBlocking {
      val job = launch {
        try {
          repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
          }
        } finally {
          println("I'm running finally")
        }
      }
      delay(1300L) // delay a bit
      job.cancelAndJoin() // cancels the job and waits
    }
  }

  @Test
  fun example1B() {
    runBlocking {
      val job = launch {
        repeat(1000) { i ->
          println("I'm sleeping $i ...")
          delay(500L)
        }
      }.toDeferred().finally {

      }
      delay(1300L) // delay a bit
      job.cancelAndJoin() // cancels the job and waits
    }
  }
}