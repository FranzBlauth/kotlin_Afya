package com.wolppi.ktivo.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Appointments")
class LastTempModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @SerializedName("id_Sensor")
    @ColumnInfo(name = "id_Sensor")
    var id_Sensor: String = ""

    @SerializedName("temperature")
    @ColumnInfo(name = "temperature")
    var temperature: String = ""

    @SerializedName("dateTime")
    @ColumnInfo(name = "dateTime")
    var dateTime: String = ""

    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    var humidity: String = ""

    @SerializedName("battery")
    @ColumnInfo(name = "battery")
    var battery: String = ""

    @SerializedName("redCollor")
    @ColumnInfo(name = "redCollor")
    var redCollor : String = ""

    @ColumnInfo(name = "filter")
    var filter : String = ""
}



