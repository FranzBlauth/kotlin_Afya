package com.wolppi.ktivo.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Contact")
class ContactModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("idKey")
    @ColumnInfo(name = "idKey")
    var idKey: Int = 0

    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: String = ""

    @SerializedName("id_User")
    @ColumnInfo(name = "idUser")
    var idUser: String = ""

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String = ""

    @ColumnInfo(name = "nickName")
    @SerializedName("nickName")
    var nickName: String = ""

    @ColumnInfo(name = "cellPhone")
    @SerializedName("cellPhone")
    var cellPhone: String = ""

    @ColumnInfo(name = "email")
    @SerializedName("email")
    var email: String = ""

    @ColumnInfo(name = "emailSw")
    @SerializedName("emailSw")
    var emailSw: String = ""

    @ColumnInfo(name = "pushSw")
    @SerializedName("pushSw")
    var pushSw: String = ""

    @ColumnInfo(name = "smsSw")
    @SerializedName("smsSw")
    var smsSw: String = ""

    @ColumnInfo(name = "token")
    @SerializedName("token")
    var token: String = ""

    @ColumnInfo(name = "operation")
    @SerializedName("operation")
    var operation: String = ""
}