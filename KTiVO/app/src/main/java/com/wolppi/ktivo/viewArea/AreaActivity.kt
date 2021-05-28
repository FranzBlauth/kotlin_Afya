package com.wolppi.ktivo.viewArea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wolppi.ktivo.R


class AreaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teste_area_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AreaFragment.newInstance())
                .commitNow()
        }
    }
}