package ru.maxssoft.roommigrations.listener

/**
 * @author m.sidorov
 *
 * Слушатель для миграций, который можно подвесить в MigrationBuilder
 */

const val MIGRATIONS_TAG: String = "Migrations"

interface MigrationListener {

    fun onStart(startVersion: Int, endVersion: Int)
    fun onSuccess(startVersion: Int, endVersion: Int)
    fun onError(startVersion: Int, endVersion: Int, error: Throwable)

}