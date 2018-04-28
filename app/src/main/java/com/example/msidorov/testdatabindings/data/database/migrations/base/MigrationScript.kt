package com.example.msidorov.testdatabindings.data.database.migrations.base

import android.arch.persistence.db.SupportSQLiteDatabase
import com.example.msidorov.testdatabindings.data.database.execSQL
import com.example.msidorov.testdatabindings.data.database.utils.SqlScript

/**
 * Миграция, основанная на sql скрипте
 * @author m.sidorov
 */
class MigrationScript(startVersion: Int, endVersion: Int, private val sqlScript: String) : BaseMigration(startVersion, endVersion) {

    constructor(version: Int, sqlScript: String) : this(version - 1, version, sqlScript)

    override fun doMigrate(database: SupportSQLiteDatabase) {
        SqlScript()
                .read(sqlScript)
                .execSQL(database)
    }
}

