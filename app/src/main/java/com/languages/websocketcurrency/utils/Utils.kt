package com.languages.websocketcurrency.utils


private const val noErrorMessage = "Unknown error"

fun Exception.messageOrDefault(): String = this.localizedMessage ?: noErrorMessage
