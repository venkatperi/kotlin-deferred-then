package com.vperi.promise

/**
 * Represents the result of a Job/Deferred
 */
@Suppress("unused")
sealed class Result<T> {
  data class Value<T>(val value: T) : Result<T>()
  data class Error<T>(val error: Throwable) : Result<T>()
}