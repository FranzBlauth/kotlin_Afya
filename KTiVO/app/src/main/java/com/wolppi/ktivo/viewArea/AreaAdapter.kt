package com.wolppi.ktivo.viewArea

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.KtivoListener
import com.wolppi.ktivo.service.model.AreaModel

class AreaAdapter  : RecyclerView.Adapter<AreaViewHolder>() {

    private var mList: List<AreaModel> = arrayListOf()
    private lateinit var mListener: KtivoListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_area, parent, false)
        return AreaViewHolder(item, mListener)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun attachListener(listener: KtivoListener) {
        mListener = listener
    }

    fun updateList(list: List<AreaModel>){
        mList = list
        notifyDataSetChanged()
    }
}




