package ru.maxssoft.roommigrations

/**
 * @author m.sidorov
 *
 * Ошибка миграции базы данных
 * Вызывается в случае ошибок внутри BaseMigration.migrate()
 */
class DatabaseMigrationError constructor(message: String = "", cause: Throwable? = null) : Error (message, cause)