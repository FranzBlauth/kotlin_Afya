package com.wolppi.ktivo.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Configuration")
class ConfigurationModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "contactUUID")
    var contactUUID: String = ""

    @ColumnInfo(name = "numberInstall")
    var numberInstall: Int = 0

    @ColumnInfo(name = "urlData")
    var urlData: String = ""

    @ColumnInfo(name = "urlPhotos")
    var urlPhotos: String = ""

    @ColumnInfo(name = "urlRecords")
    var urlRecords: String = ""

}