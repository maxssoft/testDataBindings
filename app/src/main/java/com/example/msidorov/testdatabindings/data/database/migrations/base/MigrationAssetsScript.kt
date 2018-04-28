package com.example.msidorov.testdatabindings.data.database.migrations.base

import android.arch.persistence.db.SupportSQLiteDatabase
import android.content.Context
import com.example.msidorov.testdatabindings.data.database.execSQL
import com.example.msidorov.testdatabindings.data.database.utils.SqlScript

/**
 * Миграция, основанная на asset ресурсе с sql скриптом
 * SQL скрипт с миграцией должен лежать в файле Assets/migrations/migrate_V$version.sql
 * @author m.sidorov
 */
class MigrationAssetsScript(startVersion: Int, endVersion: Int, private val context: Context) : BaseMigration(startVersion, endVersion) {

    constructor(version: Int, context: Context) : this(version - 1, version, context)

    override fun doMigrate(database: SupportSQLiteDatabase) {
        SqlScript()
                .read(context, "migrations/migrate_V$version.sql")
                .execSQL(database)
    }
}
