package ru.maxssoft.roommigrations.migration

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import ru.maxssoft.roommigrations.DatabaseMigrationError
import ru.maxssoft.roommigrations.listener.MigrationListener

/**
 * @author m.sidorov
 *
 * Абстрактный класс с реализацией кода, выполняющего обновление
 */
abstract class BaseMigration(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {

    val version: Int

    init {
        version = endVersion
    }

    var listener: MigrationListener? = null

    // Выполняет миграцию базы данных и фиксирует ее в логах
    final override fun migrate(database: SupportSQLiteDatabase) {
        listener?.onStart(startVersion, endVersion)
        try {

            doMigrate(database)

            listener?.onSuccess(startVersion, endVersion)
        } catch (e: Exception) {
            listener?.onError(startVersion, endVersion, e)
            throw DatabaseMigrationError("Error of migrate database to $endVersion version", e)
        }
    }

    protected abstract fun doMigrate(database: SupportSQLiteDatabase)

}

