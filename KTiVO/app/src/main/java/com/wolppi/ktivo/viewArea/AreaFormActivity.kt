package com.wolppi.ktivo.viewArea

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.wolppi.ktivo.R
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.AreaModel
import kotlinx.android.synthetic.main.activity_area.*
import kotlinx.android.synthetic.main.activity_login.progressBar

class AreaFormActivity : AppCompatActivity() {

    private lateinit var mAreaViewModel: AreaViewModel
    private lateinit var mProgressBar: ProgressBar
    private val mContactListId: MutableList<Int> = arrayListOf()
    private var mAreaId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area)
        supportActionBar?.hide()
        progressBar.visibility = View.GONE; // to hide

        mAreaViewModel = ViewModelProvider(this).get(AreaViewModel::class.java)

        mProgressBar = findViewById(R.id.progressBar);

        button_go_area.setOnClickListener {
            Toast.makeText(this, getString(R.string.ERROR_INTERNET_CONNECTION), Toast.LENGTH_LONG)
                .show()
        }
        observe()
        mAreaViewModel.loadContactLocal()
        loadDataFromActivity()
    }

    private fun observe() {

        mAreaViewModel.loadContactLocal.observe(this, androidx.lifecycle.Observer {

            val list: MutableList<String> = arrayListOf()
            for (item in it) {
                list.add(item.name)
                mContactListId.add(item.idKey)
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
            spinner_contacts.adapter = adapter
            mAreaViewModel.pickArea(mAreaId)
        })

        mAreaViewModel.pickArea.observe(this, androidx.lifecycle.Observer {

            name_area.setText(it.name)

            spinner_contacts.setSelection(getIndex(it.idKey))

        })
    }

    private fun loadDataFromActivity() {
        val bundle = intent.extras
        if (bundle != null) {
            mAreaId = bundle.getString(KtivoConstants.BUNDLE.AREA).toString()
        }
    }

    private fun handleSave() {
        val area = AreaModel().apply {
            this.name = ""
            val mIdContact: Int = mContactListId[spinner_contacts.selectedItemPosition]
            this.idContact = mIdContact.toString()
        }

 //        mAreaViewModel.save(area)
    }


    private fun getIndex(priorityId: Int): Int {
        var index = 0
        for (i in 0 until mContactListId.count()) {
            if (mContactListId[i] == priorityId) {
                index = i
                break
            }
        }
        return index
    }

    private fun dialogMessage(message: String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("KTiVO")
        builder.setMessage(message)
        builder.setIcon(R.mipmap.ic_launcher)

        builder.setPositiveButton(R.string.ok) { dialog, which ->
            Toast.makeText(applicationContext, R.string.null_msg, Toast.LENGTH_SHORT)
        }

        builder.show()
    }

}
