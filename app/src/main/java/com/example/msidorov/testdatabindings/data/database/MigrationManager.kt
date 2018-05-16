package com.example.msidorov.testdatabindings.data.database

import android.arch.persistence.room.migration.Migration
import android.content.Context
import ru.maxssoft.roommigrations.MigrationBuilder
import ru.maxssoft.roommigrations.migration.MigrationAssetsScript
import ru.maxssoft.roommigrations.migration.MigrationScript

/**
 * @author m.sidorov
 *
 * Регистрация всех миграций БД
 */
object MigrationManager {

    // Текущая версия базы данных
    const val DATABASE_VERSION: Int = 7

    // Регстрация всех миграций базы данных
    // Миграции должны добавляться строго последовательно
    fun registerMigrations(context: Context): Array<Migration> {

        return MigrationBuilder()
                .add(MigrationScript(2, "update users set login = login where id = -1"))
                .add(MigrationAssetsScript(3, context))
                .add(MigrationScript(4, ""))
                .add(MigrationAssetsScript(5, context))
                .add(MigrationScript(5, 7, "/* test migration over several versions */"))
                .build()

    }

}