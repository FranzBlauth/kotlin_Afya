package com.wolppi.ktivo.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Sensor")
class SensorModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("idKey")
    @ColumnInfo(name = "idKey")
    var idKey: Int = 0

    @SerializedName("id_Sensor")
    @ColumnInfo(name = "idSensor")
    var idSensor: String = ""

    @SerializedName("id_User")
    @ColumnInfo(name = "idUser")
    var idUser: String = ""

    @SerializedName("id_Area")
    @ColumnInfo(name ="idArea")
    var idArea: String = ""

    @SerializedName("id_Place")
    @ColumnInfo(name = "idPlace")
    var idPlace: String = ""

    @SerializedName("id_Contact")
    @ColumnInfo (name = "idContact")
    var idContact: String = ""

    @SerializedName("id_Provider")
    @ColumnInfo(name = "idProvider")
    var idProvider: String = ""

    @SerializedName("identifierDB")
    @ColumnInfo(name = "identifierDB")
    var identifierDB: String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = ""

    @SerializedName("serialNumber")
    @ColumnInfo(name = "serialNumber")
    var serialNumber: String = ""

    @SerializedName("tempMax")
    @ColumnInfo(name = "tempMax")
    var tempMax: String = ""

    @SerializedName("tempMin")
    @ColumnInfo(name = "tempMin")
    var tempMin: String = ""

    @SerializedName("timeInitial")
    @ColumnInfo(name = "timeInitial")
    var timeInitial: String = ""

    @SerializedName("timeFinal")
    @ColumnInfo(name = "timeFinal")
    var timeFinal: String = ""

    @SerializedName("sun")
    @ColumnInfo(name = "sun")
    var sun: String = ""

    @SerializedName("mon")
    @ColumnInfo(name = "mon")
    var mon: String = ""

    @SerializedName("tue")
    @ColumnInfo(name = "tue")
    var tue: String = ""

    @SerializedName("wed")
    @ColumnInfo(name = "wed")
    var wed: String = ""

    @SerializedName("thu")
    @ColumnInfo(name = "thu")
    var thu: String = ""

    @SerializedName("fri")
    @ColumnInfo(name = "fri")
    var fri: String = ""

    @SerializedName("sat")
    @ColumnInfo(name = "sat")
    var sat: String = ""

    @SerializedName("push")
    @ColumnInfo (name = "push")
    var push: String = ""

    @SerializedName("sms")
    @ColumnInfo(name = "sms")
    var sms: String = ""

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String = ""

    @SerializedName("dateCreate")
    @ColumnInfo(name = "date")
    var date: String = ""

    @SerializedName("note")
    @ColumnInfo(name = "note")
    var note: String = ""

    @SerializedName("operation")
    @ColumnInfo(name = "operation")
    var operation: String = ""
}
