package com.wolppi.ktivo.viewArea

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.KtivoListener
import com.wolppi.ktivo.service.model.AreaModel
import com.wolppi.ktivo.service.repository.local.repository.*

class AreaViewHolder(itemView: View, val listener: KtivoListener) : RecyclerView.ViewHolder(itemView) {

    private val mAreaLocalRepository = AreaLocalRepository(itemView.context)

    private var mAreaName: TextView = itemView.findViewById(R.id.area_name)
    private var mAreaImage: ImageView = itemView.findViewById(R.id.area_image)

    fun bindData(model: AreaModel) {

        mAreaName.text = model.name

        mAreaName.setOnClickListener {
            listener.onListClick(model.idArea)
        }
        mAreaImage.setOnClickListener {
            listener.onListClick(model.idArea)
        }

    }
}

