package com.example.msidorov.testdatabindings.utils

import android.util.Log

/**
 * @author m.sidorov
 */

enum class LogLevel(value: Int) {
    DEBUG(Log.DEBUG), INFO(Log.INFO), WARN(Log.WARN), ERROR(Log.ERROR), ASSERT(Log.ASSERT)
}

fun Any.log(level: LogLevel, message: String?, error: Throwable? = null) {
    when (level) {
        LogLevel.DEBUG -> Log.d(javaClass.simpleName, message.isNull(""), error)
        LogLevel.INFO -> Log.i(javaClass.simpleName, message.isNull(""), error)
        LogLevel.WARN -> Log.w(javaClass.simpleName, message.isNull(""), error)
        LogLevel.ERROR -> Log.e(javaClass.simpleName, message.isNull(""), error)
        LogLevel.ASSERT -> Log.e(javaClass.simpleName, message.isNull(""), error)
    }
}

