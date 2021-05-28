package com.wolppi.ktivo.service.repository.local.dao

import androidx.room.*
import com.wolppi.ktivo.service.model.AreaModel
import com.wolppi.ktivo.service.model.ContactModel

@Dao
interface ContactDAO {

    @Insert
    fun saveContacts(contact: ContactModel): Long

    @Insert
    @JvmSuppressWildcards
    fun saveList(objects: List<ContactModel>)

    @Update
    fun updateContact(contact: ContactModel): Int

    @Delete
    fun deleteContact(contact: ContactModel)

    @Query("SELECT * FROM Contact WHERE id = :uuid LIMIT 1 ")
    fun picContact(uuid: String): ContactModel

    @Query("SELECT * FROM Contact")
    fun getAll(): List<ContactModel>

    @Query("SELECT COUNT(id) FROM Contact")
    fun countRows(): Int

}