package com.vperi.promise

import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Returns a [Deferred] which completes with the [Job]
 *
 * @param context context of the coroutine. The default value is [DefaultDispatcher].
 */
fun Job.toDeferred(
  context: CoroutineContext = DefaultDispatcher
): Deferred<Unit> =
  async(context) { join() }

