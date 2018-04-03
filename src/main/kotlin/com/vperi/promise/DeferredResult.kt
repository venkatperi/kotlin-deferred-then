package com.vperi.promise

sealed class DeferredResult<T> {
  data class Value<T>(val value: T) : DeferredResult<T>()
  data class Error<T>(val error: Throwable) : DeferredResult<T>()
}