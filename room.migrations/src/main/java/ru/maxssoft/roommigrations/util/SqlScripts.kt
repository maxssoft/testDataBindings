package ru.maxssoft.roommigrations.util

import android.arch.persistence.db.SupportSQLiteDatabase
import android.content.Context
import java.io.File

const val SQL_COMMAND_SEPARATOR: String = ";"

/**
 * @author m.sidorov
 *
 * Класс, формирующий список sql команд
 * SQL команды могут быть получены из разных источников (строка, файл, ресурс)
 */
class SqlScript() {

    // SQL команды
    val commands: MutableList<String> = mutableListOf()

    // разделитель SQL команд
    var commandSeparator = SQL_COMMAND_SEPARATOR

    // Читает SQL команды из строки
    fun read(sql: String): SqlScript {
        commands.addAll(parse(sql))
        return this
    }

    // Читает SQL команды из файла
    fun read(file: File): SqlScript {
        val sql = file.readText(Charsets.UTF_8)
        commands.addAll(parse(sql))
        return this
    }

    // Читает SQL команды из Asset файла
    fun read(context: Context, assetPath: String): SqlScript {
        val sql = context.assets.open(assetPath).bufferedReader().use{
            it.readText()
        }
        commands.addAll(parse(sql))
        return this
    }

    private fun parse(script: String): List<String>{
        if (!script.isEmpty()) {
            return script.split("\\s*$commandSeparator\\s*")
        } else {
            return emptyList()
        }
    }

    fun execSql(database: SupportSQLiteDatabase) {
        for (sql in commands) {
            if (!sql.isBlank()) {
                database.execSQL(sql)
            }
        }
    }

}