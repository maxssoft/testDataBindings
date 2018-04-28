package com.example.msidorov.testdatabindings.data.dao

import android.arch.persistence.room.*
import com.example.msidorov.testdatabindings.data.entity.UserContactEntity

/**
 * @author m.sidorov
 */
@Dao interface UserContactDao {

    @Query("select * from user_contacts where userId = :userId")
    fun getUserContacts(userId: Long): List<UserContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: UserContactEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(contact: UserContactEntity)

    @Delete
    fun delete(contact: UserContactEntity)

}