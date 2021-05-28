package com.wolppi.ktivo.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Provider")
class ProviderModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("idKey")
    @ColumnInfo(name = "idKey")
    var idKey: Int = 0

    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: String = ""

    @SerializedName("id_user")
    @ColumnInfo(name = "id_User")
    var idUser: String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = ""

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String = ""

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    var phone: String = ""

    @SerializedName("cellPhone")
    @ColumnInfo(name = "cellPhone")
    var cellPhone: String = ""

    @SerializedName("contactPerson")
    @ColumnInfo(name = "contactPerson")
    var contactPerson: String = ""

    @SerializedName("operation")
    @ColumnInfo(name = "operation")
    var operation: String = ""
}