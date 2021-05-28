package com.wolppi.ktivo.service.repository.local.repository

import android.content.Context
import com.wolppi.ktivo.service.model.AreaModel
import com.wolppi.ktivo.service.model.ContactModel
import com.wolppi.ktivo.service.repository.local.dao.KtivoDatabase

class ContactLocalRepository (context: Context){

    private val mDataBase = KtivoDatabase.getDatabase(context).contactDAO()

    fun saveContactLocal(contact: ContactModel): Boolean {
        return mDataBase.saveContacts(contact) > 0
    }

    fun updateContact(contact: ContactModel): Boolean {
        return mDataBase.updateContact(contact) > 1
    }

    fun deleteContact(contact: ContactModel) {
        return mDataBase.deleteContact(contact)
    }

    fun saveAll(model: List<ContactModel>) {
        return mDataBase.saveList(model)
    }

    fun getAll(): List<ContactModel> {
        return mDataBase.getAll()
    }

    fun picContact(uuid: String): ContactModel {
        return mDataBase.picContact(uuid)
    }

    fun countRows(): Int {
        return  mDataBase.countRows()
    }


}