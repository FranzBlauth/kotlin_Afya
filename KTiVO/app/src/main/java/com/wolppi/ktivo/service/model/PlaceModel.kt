package com.wolppi.ktivo.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Place")
class PlaceModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("idKey")
    @ColumnInfo(name = "idKey")
    var idKey: Int = 0

    @SerializedName("id_Place")
    @ColumnInfo(name = "idPlace")
    var idPlace : String = ""

    @SerializedName("id_Users")
    @ColumnInfo(name = "idUser")
    var idUser : String = ""

    @SerializedName("id_Area")
    @ColumnInfo(name = "id_Area")
    var idArea : String = ""

    @SerializedName("id_Contact")
    @ColumnInfo(name = "id_Contact")
    var idContact : String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name : String = ""

    @SerializedName("emailSw")
    @ColumnInfo(name = "emailSw")
    var emailSw : String = ""

    @SerializedName("pushSw")
    @ColumnInfo(name = "pushSw")
    var pushSw : String = ""

    @SerializedName("smsSw")
    @ColumnInfo(name = "smsSw")
    var smsSw : String = ""

    @SerializedName("operation")
    @ColumnInfo(name = "operation")
    var operation : String = ""

    @SerializedName("date_Modify")
    @ColumnInfo(name = "date")
    var date : String = ""

}