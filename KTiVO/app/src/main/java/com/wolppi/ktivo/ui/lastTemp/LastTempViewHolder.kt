package com.wolppi.ktivo.ui.lastTemp

import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.wolppi.ktivo.R
import com.wolppi.ktivo.service.model.LastTempModel
import com.wolppi.ktivo.service.repository.local.repository.*
import java.util.*

class LastTempViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @RequiresApi(Build.VERSION_CODES.N)

    var local = Locale("pt", "BR")

    @RequiresApi(Build.VERSION_CODES.N)
    private val mDateFormat = SimpleDateFormat("EEEE dd/MM/yyyy HH:mm", local)

    private val mAreaLocalRepository = AreaLocalRepository(itemView.context)
    private val mPlaceLocalRepository = PlaceLocalRepository(itemView.context)
    private val mSensorLocalRepository = SensorLocalRepository(itemView.context)

    private var mAreaName: TextView = itemView.findViewById(R.id.area_data)
    private var mPlaceName: TextView = itemView.findViewById(R.id.place_data)
    private var mSensorName: TextView = itemView.findViewById(R.id.sensor_data)
    private var mMinDef: TextView = itemView.findViewById(R.id.min_def)
    private var mLastTemp: TextView = itemView.findViewById(R.id.last_temp)
    private var mMaxDef: TextView = itemView.findViewById(R.id.max_def)
    private var mHumidity: TextView = itemView.findViewById(R.id.humidity)
    private var mLastDate: TextView = itemView.findViewById(R.id.last_date)
    private var mBattery: TextView = itemView.findViewById(R.id.battery)
    private var mIconThermometer: ImageView = itemView.findViewById(R.id.ic_thermometer)

    @RequiresApi(Build.VERSION_CODES.N)
    fun bindData(model: LastTempModel) {

        val modelSensor = mSensorLocalRepository.picSensor(model.id_Sensor)
        if (modelSensor != null) {
            mSensorName.text = modelSensor.name
            mMinDef.text = modelSensor.tempMin
            mMaxDef.text = modelSensor.tempMax

            val modelArea = mAreaLocalRepository.picArea(modelSensor.idArea)
            if (modelArea != null) {
                mAreaName.text = modelArea.name
            } else {
                mAreaName.text = "Sem Descrição de Área"
            }

            val modelPlace = mPlaceLocalRepository.picPlace(modelSensor.idPlace)
            if (modelPlace != null) {
                mPlaceName.text = modelPlace.name
            } else {
                mPlaceName.text = "Sem Descrição do Local"
            }

        } else {
            mSensorName.text = "Sem Descrição do Sensor"
            mMinDef.text = "00"
            mMaxDef.text = "00"
        }

        val date =
            java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(model.dateTime)
        this.mLastDate.text = mDateFormat.format(date)

        mLastTemp.text = model.temperature
        mHumidity.text = model.humidity + "%"
        mBattery.text = model.battery + "%"

        mIconThermometer.setColorFilter(Color.parseColor("#05913D")) // Green

        val tempMin = ((mMinDef.text).toString()).toDouble()
        val tempMax = ((mMaxDef.text).toString()).toDouble()
        val temp = ((mLastTemp.text).toString()).toDouble()

        if (temp > tempMax) {
            mIconThermometer.setColorFilter(Color.parseColor("#D54D56")) // Red
        } else {
            if (temp < tempMin) {
                mIconThermometer.setColorFilter(Color.parseColor("#AAF2F2")) // Blue
            }
        }
    }
}

