package ru.maxssoft.roommigrations.listener

import android.util.Log
import ru.maxssoft.roommigrations.listener.MIGRATIONS_TAG
import ru.maxssoft.roommigrations.listener.MigrationListener

/**
 * @author m.sidorov
 *
 * Слушатель по умолчанию, который фиксирует выполнение обновлений в логе
 */
class DefaultMigrationListener : MigrationListener {

    override fun onStart(startVersion: Int, endVersion: Int) {
        Log.i(MIGRATIONS_TAG, "start migrate database to $endVersion version")
    }

    override fun onSuccess(startVersion: Int, endVersion: Int) {
        Log.i(MIGRATIONS_TAG, "success maigration to $endVersion version")
    }

    override fun onError(startVersion: Int, endVersion: Int, error: Throwable) {
        Log.e(MIGRATIONS_TAG, "database migration to $endVersion version failed with exception\n${error.message}", error)
    }
}