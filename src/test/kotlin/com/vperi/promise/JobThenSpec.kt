package com.vperi.promise

import com.vperi.kotlin.test.wait
import kotlinx.coroutines.experimental.launch
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object JobThenSpec : Spek({

  describe("then") {
    it("is called when the job completes") {
      wait(1000, 1) {
        launch {}
          .toDeferred()
          .then {
            resume()
          }
      }
    }

  }

})