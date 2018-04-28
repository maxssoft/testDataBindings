package com.example.msidorov.testdatabindings.data.database.migrations

import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.example.msidorov.testdatabindings.data.database.migrations.base.MigrationAssetsScript
import com.example.msidorov.testdatabindings.data.database.migrations.base.MigrationScript

/**
 * @author m.sidorov
 */
class MigrationManager {

    val migrations : Array<Migration>

    constructor(context: Context){
        migrations = registerMigrations(context)
    }

    companion object {
        // Версия базы данных
        const val DATABE_VERSION: Int = 7
    }

    // Регстрация всех миграций базы данных
    // Миграции должны добавляться строго последовательно
    private fun registerMigrations(context: Context): Array<Migration> {
        return MigrationBuilder()
                .add( MigrationScript(2, "update users set login = login where id = -1") )
                .add( MigrationAssetsScript(3, context) )
                .add( MigrationScript(4, "") )
                .add( MigrationAssetsScript(5, context) )
                .add( MigrationScript(5, 7, "/* test migrate over several versions */") )
                .build()
    }

    class MigrationBuilder {

        private val migrations: MutableList<Migration> = mutableListOf()
        private var lastVersion = 1 // Версия 1 существует всегда и для нее не нужна миграция

        fun build(): Array<Migration> {
            return migrations.toTypedArray()
        }

        // Регстрирует миграцию, здесь реализован контроль, что правильно указана версия миграции и нет дырок
        // Миграции должны регистрироваться строго последовательно!!!
        fun add(migration: Migration) : MigrationBuilder {
            return apply {
                if (migration.startVersion < lastVersion){
                    throw IllegalStateException("Error of registration version [${migration.startVersion}], this version already registered")
                }
                if (migration.startVersion > lastVersion){
                    throw IllegalStateException("Error of registration version [${migration.startVersion}], versions from $lastVersion has not been registered")
                }

                migrations.add(migration)
                lastVersion = migration.endVersion
            }
        }

    }

}