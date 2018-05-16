package ru.maxssoft.roommigrations

import android.arch.persistence.room.migration.Migration
import ru.maxssoft.roommigrations.migration.BaseMigration
import ru.maxssoft.roommigrations.listener.DefaultMigrationListener
import ru.maxssoft.roommigrations.listener.MigrationListener

/**
 * @author m.sidorov
 *
 * Билдер, создающий список миграций
 * При регистрации миграции производится проверка, что версии идут последовательно и не пересекаются
 */
class MigrationBuilder {

    private var listener: MigrationListener = DefaultMigrationListener()
    private val migrations: MutableList<BaseMigration> = mutableListOf()
    private var lastVersion = 1 // Версия 1 существует всегда и для нее не нужна миграция

    fun build(): Array<Migration> {
        for (migration in migrations){
            migration.listener = listener
        }

        return migrations.toTypedArray()
    }

    // Регстрирует миграцию, здесь реализован контроль, что правильно указана версия миграции и нет дырок
    // Миграции должны регистрироваться строго последовательно!!!
    fun add(migration: BaseMigration) : MigrationBuilder {
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

    fun setListener(listener: MigrationListener): MigrationBuilder{
        return apply {
            this.listener = listener
        }
    }

}
