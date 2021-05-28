package com.wolppi.ktivo.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Area")
class AreaModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("idKey")
    @ColumnInfo(name = "idKey")
    var idKey: Int = 0

    @SerializedName("id_Area")
    @ColumnInfo(name = "idArea")
    var idArea: String = ""

    @SerializedName("id_User")
    @ColumnInfo(name = "id_User")
    var idUser: String = ""

    @SerializedName("id_Contact")
    @ColumnInfo(name = "idContact")
    var idContact: String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = ""

    @SerializedName("emailSw")
    @ColumnInfo(name = "emailSw")
    var emailSw: String = ""

    @SerializedName("pushSw")
    @ColumnInfo(name = "pushSw")
    var pushSw: String = ""

    @SerializedName("smsSw")
    @ColumnInfo(name = "smsSw")
    var smsSw: String = ""

    @SerializedName("date_Modify")
    @ColumnInfo(name = "date")
    var date: String = ""

    @SerializedName("date_Create")
    @ColumnInfo(name = "dateCreate")
    var dateCreate: String = ""

    @SerializedName("operation")
    @ColumnInfo(name = "operation")
    var operation: String = ""

}
