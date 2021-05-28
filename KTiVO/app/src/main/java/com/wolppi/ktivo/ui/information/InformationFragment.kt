package com.wolppi.ktivo.ui.information

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wolppi.ktivo.R
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import kotlinx.android.synthetic.main.fragment_information.*

class InformationFragment : Fragment() {

    private lateinit var mInformationViewModel: InformationViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {

        mInformationViewModel = ViewModelProvider(this).get(InformationViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_information, container, false)
        observe()
        (activity as AppCompatActivity).supportActionBar?.hide()
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.visibility = View.VISIBLE; //to show
        return root
    }

    override fun onResume() {
        super.onResume()
        loadConfiguration()
    }


    private fun observe() {
        mInformationViewModel.returnConfiguration.observe(viewLifecycleOwner, {
            progressBar.visibility = View.GONE;
            if (it[0].urlData != ""){
                val mPreferences = loadSharedPreferences()
                text_url_data.text = it[0].urlData.substring(47)
                text_url_photos.text = it[0].urlPhotos.substring(47)
                text_url_register.text = it[0].urlRecords.substring(47 )
                text_uuid.text = mPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)
                text_token.text = mPreferences.getShared(KtivoConstants.SHARED.TOKEN)
                text_system.text = "KTiVO está ON !!!!"
                ic_check.setImageResource(R.drawable.ic_check_circle);
                ic_check.setColorFilter(Color.parseColor("#05913D"))
            }
        })
    }

    private fun loadConfiguration() {
        mInformationViewModel.loadConfiguration()
    }

    private fun loadSharedPreferences() : SharedPreferenceRepository{
        return mInformationViewModel.loadShared()
    }
}