package com.example.msidorov.testdatabindings.data.database.migrations

/**
 * Ошибка миграции базы данных
 * @author m.sidorov
 */
class DatabaseMigrationError constructor(message: String = "", cause: Throwable? = null) : Error (message, cause)