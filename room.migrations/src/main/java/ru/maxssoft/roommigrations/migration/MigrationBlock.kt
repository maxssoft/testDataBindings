package ru.maxssoft.roommigrations.migration

import android.arch.persistence.db.SupportSQLiteDatabase

/**
 * @author m.sidorov
 *
 * Миграция, основанная на анонимном блоке кода
 */
class MigrationBlock(startVersion: Int, endVersion: Int, private val block: (SupportSQLiteDatabase) -> Unit) : BaseMigration(startVersion, endVersion) {

    constructor(version: Int, block: (SupportSQLiteDatabase) -> Unit) : this(version - 1, version, block)

    override fun doMigrate(database: SupportSQLiteDatabase) {
        block(database)
    }
}

