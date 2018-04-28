package com.example.msidorov.testdatabindings.data.database.migrations.base

import android.arch.persistence.db.SupportSQLiteDatabase

/**
 * Миграция, основанная на анонимном блоке
 * @author m.sidorov
 */
class MigrationBlock(startVersion: Int, endVersion: Int, private val block: (SupportSQLiteDatabase) -> Unit) : BaseMigration(startVersion, endVersion) {

    constructor(version: Int, block: (SupportSQLiteDatabase) -> Unit) : this(version - 1, version, block)

    override fun doMigrate(database: SupportSQLiteDatabase) {
        block(database)
    }
}

