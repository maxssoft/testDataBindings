package ru.maxssoft.roommigrations.migration

import android.arch.persistence.db.SupportSQLiteDatabase
import ru.maxssoft.roommigrations.util.SqlScript

/**
 * @author m.sidorov
 *
 * Миграция, основанная на sql скрипте
 */
class MigrationScript(startVersion: Int, endVersion: Int, private val sqlScript: String) : BaseMigration(startVersion, endVersion) {

    constructor(version: Int, sqlScript: String) : this(version - 1, version, sqlScript)

    override fun doMigrate(database: SupportSQLiteDatabase) {
        SqlScript().read(sqlScript).execSql(database)
    }
}

