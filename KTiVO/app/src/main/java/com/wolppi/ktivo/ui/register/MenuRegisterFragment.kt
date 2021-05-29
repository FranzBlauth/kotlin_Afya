package com.wolppi.ktivo.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wolppi.ktivo.R
import com.wolppi.ktivo.viewArea.AreaFormActivity
import com.wolppi.ktivo.viewArea.AreaActivity

class MenuRegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var mIntent: Intent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAreaText = view.findViewById<TextView>(R.id.text_area)
        val mAreaIcon = view.findViewById<ImageView>(R.id.ic_area)
        val mPlaceIcon = view.findViewById<ImageView>(R.id.ic_place)
        val mPlaceText = view.findViewById<TextView>(R.id.text_place)
        val mContactIcon = view.findViewById<ImageView>(R.id.ic_contact)
        val mContactText = view.findViewById<TextView>(R.id.text_contact)
        val mProviderIcon = view.findViewById<ImageView>(R.id.ic_provider)
        val mProviderText = view.findViewById<TextView>(R.id.text_provider)
        val mSensorText = view.findViewById<TextView>(R.id.text_sensor)
        val mSensorIcon = view.findViewById<ImageView>(R.id.ic_sensor)

        mAreaText.setOnClickListener(this)
        mAreaIcon.setOnClickListener(this)
        mPlaceIcon.setOnClickListener(this)
        mPlaceText.setOnClickListener(this)
        mContactIcon.setOnClickListener(this)
        mContactText.setOnClickListener(this)
        mProviderIcon.setOnClickListener(this)
        mProviderText.setOnClickListener(this)
        mSensorText.setOnClickListener(this)
        mSensorIcon.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val c = v.id
        if (v.id == R.id.text_area || v.id == R.id.ic_area) {
            mIntent = Intent (activity, AreaActivity::class.java)
        }
        if (v.id == R.id.text_place || v.id == R.id.ic_place) {
 //           mIntent = Intent (activity, PlaceFormActivity::class.java)
        }
        if (v.id == R.id.text_contact || v.id == R.id.ic_contact) {
//            mIntent = Intent (activity, Contactsctivity::class.java)
        }
        if (v.id == R.id.text_provider || v.id == R.id.ic_provider) {
//            mIntent = Intent (activity, ProviderFormActivity::class.java)
        }
        if (v.id == R.id.text_sensor || v.id == R.id.ic_sensor) {
//            mIntent = Intent (activity, SensorFormActivity::class.java)
        }
        activity?.startActivity(mIntent)
    }
}
