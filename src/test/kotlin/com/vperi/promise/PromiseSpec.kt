package com.vperi.promise

import com.vperi.kotlin.test.wait
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

object PromiseSpec : Spek({

  describe("completed()") {
    it("returns a completed Deferred") {
      runBlocking {
        assertEquals(101, completed(101).await())
      }
    }
  }

  describe("completedExceptionally") {
    it("throws") {
      wait(1000) {
        try {
          completedExceptionally<Int>(Exception("test")).await()
        } catch (e: Exception) {
          assertEquals("test", e.message)
          resume()
        }
      }
    }
  }

  describe("then") {
    it("is called with the result of the current deferred") {
      wait(1000, 1) {
        completed(1)
          .then {
            assertEquals(1, it)
            resume()
          }
      }
    }

    it("can be chained") {
      wait(1000, 2) {
        completed(1)
          .then {
            assertEquals(1, it)
            resume()
            it + 1
          }
          .then {
            assertEquals(2, it)
            resume()
          }
      }
    }
  }

  describe("catch") {
    it("is called on error") {
      wait(1000) {
        completedExceptionally<Int>(Exception("test"))
          .then {
            throw Error("shouldn't get here")
          }
          .catch {
            assertEquals("test", it.message)
            resume()
          }
      }
    }

    it("can be called down the line") {
      wait(1000) {
        completedExceptionally<Int>(Exception("test"))
          .then { throw Error("shouldn't get here") }
          .then { throw Error("shouldn't get here") }
          .then { throw Error("shouldn't get here") }
          .then { throw Error("shouldn't get here") }
          .catch {
            assertEquals("test", it.message)
            resume()
          }
      }
    }

    it("can fix an error and continue") {
      wait(1000, 2) {
        completedExceptionally<Int>(Exception("test"))
          .then {
            throw Error("shouldn't get here")
          }
          .catch {
            assertEquals("test", it.message)
            resume()
            2
          }.then {
            assertEquals(2, it)
            resume()
          }
      }
    }
  }

  describe("finally") {
    it("is called on success") {
      wait(1000, 1) {
        completed(1)
          .finally {
            when (it) {
              is DeferredResult.Value -> assertEquals(1, it.value)
              else -> throw Exception()
            }
            resume()
          }
      }
    }

    it("is called on error") {
      wait(1000, 1) {
        completedExceptionally<Int>(Exception("test"))
          .finally {
            when (it) {
              is DeferredResult.Error -> assertEquals("test", it.error.message)
              else -> throw Exception()
            }
            resume()
          }
      }
    }
  }

  describe("nested deferreds") {
    it("unwraps deferreds") {
      wait(1000, 1) {
        completed(completed(1))
          .then { it: Int ->
            assertEquals(1, it)
            resume()
          }
      }
    }
  }
})