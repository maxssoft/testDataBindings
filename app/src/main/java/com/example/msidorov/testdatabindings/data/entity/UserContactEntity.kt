package com.example.msidorov.testdatabindings.data.entity

import android.arch.persistence.room.*
import com.example.msidorov.testdatabindings.domain.model.Contact

/**
 * @author m.sidorov
 */
@Entity(tableName = "user_contacts",
        indices = arrayOf(Index(value = "userId", name = "userIdx")),
        foreignKeys = arrayOf(ForeignKey(entity = UserEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId"), onDelete = ForeignKey.CASCADE))
)
class UserContactEntity : Contact {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) override var id: Long = 0

    @ColumnInfo(name = "userId")
    var userId: Int? = null

    @ColumnInfo(name = "value")
    override var value: String = "";

    @ColumnInfo(name = "contact_type")
    var contact_type: Int = 0;

    override var type: Contact.ContactType
        get() = Contact.ContactType.fromOrdinal(contact_type)
        set(value) {
            contact_type = value.ordinal;
        }

}