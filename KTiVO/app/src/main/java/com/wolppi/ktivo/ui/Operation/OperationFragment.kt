package com.wolppi.ktivo.ui.Operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wolppi.ktivo.R

class OperationFragment : Fragment() {

    private lateinit var operationViewModel: OperationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        operationViewModel = ViewModelProvider(this).get(OperationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_operation, container, false)
        return root
    }
}