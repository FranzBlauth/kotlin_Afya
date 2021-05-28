package com.wolppi.ktivo.ui.lastTemp

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.wolppi.ktivo.R
import com.wolppi.ktivo.service.model.LastTempModel

class LastTempAdapter  : RecyclerView.Adapter<LastTempViewHolder>() {

    private var mList: List<LastTempModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastTempViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_last_temp, parent, false)
        return LastTempViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: LastTempViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun updateList(list: List<LastTempModel>){
        mList = list
        notifyDataSetChanged()
    }
}




