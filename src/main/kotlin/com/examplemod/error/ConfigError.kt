package com.examplemod.error

class ConfigError(message: String, couse: Throwable) : Error(message, couse)