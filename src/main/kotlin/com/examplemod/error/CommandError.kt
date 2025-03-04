package com.examplemod.error

class CommandError(message: String, couse: Throwable) : Error(message, couse)