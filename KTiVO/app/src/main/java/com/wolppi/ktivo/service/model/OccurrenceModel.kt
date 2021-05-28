package com.wolppi.ktivo.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Occurrences")
class OccurrenceModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("idKey")
    @ColumnInfo(name = "idKey")
    var idKey: Int = 0

    @SerializedName("numOccurrence")
    @ColumnInfo(name = "numOccurrence")
    var numOccurrence : String = ""

    @SerializedName("id_Sensor")
    @ColumnInfo(name = "id_Sensor")
    var idSensor : String = ""

    @SerializedName("dateTime")
    @ColumnInfo(name = "dateTime")
    var dateTime : String = ""

    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    var humidity : String = ""

    @SerializedName("battery")
    @ColumnInfo(name = "battery")
    var battery : String = ""

    @SerializedName("nameArea")
    @ColumnInfo(name = "nameArea")
    var nameArea : String = ""

    @SerializedName("namePlace")
    @ColumnInfo(name = "namePlace")
    var namePlace : String = ""

    @SerializedName("nameSensor")
    @ColumnInfo(name = "nameSensor")
    var nameSensor : String = ""

    @SerializedName("tempMax")
    @ColumnInfo(name = "tempMax")
    var tempMax : String = ""

    @SerializedName("tempMin")
    @ColumnInfo(name = "tempMin")
    var tempMin : String = ""

    @SerializedName("temperature")
    @ColumnInfo(name = "temperature")
    var temperature : String = ""

}