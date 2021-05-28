package com.wolppi.ktivo.service.repository.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wolppi.ktivo.service.model.*


@Database(entities = [  ConfigurationModel::class,
                        ContactModel::class,
                        AreaModel::class,
                        PlaceModel::class,
                        SensorModel::class,
                        LastTempModel::class,
                        ProviderModel::class], version = 1)

abstract class KtivoDatabase : RoomDatabase() {

    abstract fun configurationDAO(): ConfigurationDAO
    abstract fun contactDAO(): ContactDAO
    abstract fun areaDAO(): AreaDAO
    abstract fun placeDAO(): PlaceDAO
    abstract fun sensorDAO(): SensorDAO
    abstract fun lastTempDAO(): LastTempDAO
    abstract fun providerDAO(): ProviderDAO

    companion object {

        private lateinit var INSTANCE: KtivoDatabase
        fun getDatabase(context: Context): KtivoDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(KtivoDatabase::class) {

                    INSTANCE = Room.databaseBuilder(context, KtivoDatabase::class.java, "KtivoDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}