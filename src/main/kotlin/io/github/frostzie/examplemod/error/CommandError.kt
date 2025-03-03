package io.github.frostzie.examplemod.error

class CommandError(message: String, couse: Throwable) : Error(message, couse)