package com.example.msidorov.testdatabindings.data.entity

import android.arch.persistence.room.*
import com.example.msidorov.testdatabindings.domain.model.Contact
import com.example.msidorov.testdatabindings.domain.model.User

/**
 * @author m.sidorov
 */
@Entity(tableName = "users",
        indices = arrayOf(Index(value = "login", name = "idxLogin", unique = true))
)
class UserEntity : User {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) override var id: Long = 0

    @ColumnInfo(name = "login")
    override var login: String = "";

    @ColumnInfo(name = "user_name")
    override var userName: String = "";

    @ColumnInfo(name = "description")
    override var description: String = "";

    @ColumnInfo(name = "password_hash")
    override var passwordHash: String = ""

    @ColumnInfo(name = "test_add_column")
    var testAddColumn: Int = 0

    @Ignore
    override val contacts: List<Contact>? = null

    override fun setPassword(password: String) {
        passwordHash = calculatePasswordHash(password);
    }

}