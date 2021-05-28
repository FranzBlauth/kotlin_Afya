package com.wolppi.ktivo.viewLogin

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wolppi.ktivo.MainActivity
import com.wolppi.ktivo.R
import com.wolppi.ktivo.service.model.ContactModel
import com.wolppi.ktivo.service.model.LastTempModel
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mLoginViewModel: LoginViewModel
    private var mUUID: String = ""
    private var mContactModel: ContactModel = ContactModel()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mLoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        progressBar = findViewById(R.id.progressBar);

         button_go.setOnClickListener {
            if (checkInformation()) {
                if (mLoginViewModel.isConnectionAvailable()) {
                    progressBar.visibility = VISIBLE; //to show
                    checkNumberInstall() // just execute it if never run ktivo app
                } else {
                    Toast.makeText(this, getString(R.string.ERROR_INTERNET_CONNECTION), Toast.LENGTH_LONG).show()
                }
            }
        }

        observe()
        checkConfiguration()  // Verify if is de first time to run a ktivo
    }


    private fun observe() {

        mLoginViewModel.checkConfigurationOK.observe(this, Observer {
            if (it) {
                progressBar.visibility = VISIBLE; // to show
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                progressBar.visibility = View.GONE; // to hide
            }
        })

        mLoginViewModel.login.observe(this, Observer {
            if (!it.success()) {
                val message = it.failure()
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        })

        mLoginViewModel.installOk.observe(this, Observer {
            if (it) {
                handleContacts()
                saveContactLocal()
            } else {
                editNumberInstalation.isEnabled = true
                editEmail.isEnabled = true
                editPhone.isEnabled = true
                editName.isEnabled = true
                Toast.makeText(this, getString(R.string.num_instalation_Invalid), Toast.LENGTH_LONG)
                    .show()
            }
        })

        mLoginViewModel.contactLocalOK.observe(this, Observer {
            if (it) {
                saveConfiguration()
            } else {
                Toast.makeText(this, getString(R.string.contact_local_error), Toast.LENGTH_SHORT)
                    .show()
            }
        })

        mLoginViewModel.saveConfigurationOK.observe(this, Observer {
            if (it) {
                saveContactRemote()
            } else {
                Toast.makeText(this, getString(R.string.configuration_error), Toast.LENGTH_SHORT)
                    .show()
            }
        })

        mLoginViewModel.contactRemoteOK.observe(this, Observer {
            if (it) {
                loadAreaFirstTime()
                loadPlaceFirstTime()
                loadProviderFirstTime()
                loadContactFirstTime()
                loadSensorFirstTime()
                loadLastTempRemote()
            } else {
                Toast.makeText(this, getString(R.string.contact_remote_error), Toast.LENGTH_SHORT)
                    .show()
            }
        })

        mLoginViewModel.saveLastTempRemoteRepository.observe(this, Observer {
            saveLastTempLocal(it)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }

    private fun checkConfiguration() {
        mLoginViewModel.checkConfiguration()
    }

    private fun checkInformation(): Boolean {

        if (editName.text.toString() == "") {
            dialogMessage("Campo Nome Obrigatório!")
            return false
        } else {
            if (editEmail.text.toString() == "") {
                dialogMessage("Campo Email Obrigatório!")
                return false
            } else {
                if (editPhone.text.toString() == "") {
                    dialogMessage("Campo Celular Obrigatório!")
                    return false
                } else {
                    if (editNumberInstalation.text.toString() == "") {
                        dialogMessage("Campo Número Instalação Obrigatório!")
                        return false
                    } else {
                        editNumberInstalation.isEnabled = false
                        editEmail.isEnabled = false
                        editPhone.isEnabled = false
                        editName.isEnabled = false
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun checkNumberInstall() {
        val numberInstall = (editNumberInstalation.text.toString()).toInt()
        mLoginViewModel.checkNumberInstall(numberInstall)
    }

    private fun handleContacts() {

        mUUID = UUID.randomUUID().toString().toUpperCase(Locale.ROOT)
        mContactModel.id = mUUID
        mContactModel.name = editName.text.toString()
        mContactModel.email = editEmail.text.toString()
        mContactModel.cellPhone = editPhone.text.toString()
        mContactModel.nickName = editName.text.toString()
        mContactModel.emailSw = "true"
        mContactModel.pushSw = "true"
        mContactModel.smsSw = "true"

    }

    private fun saveContactRemote() {
        mLoginViewModel.saveContactRemote(mContactModel)
    }

    private fun saveContactLocal() {
        mLoginViewModel.saveContactLocal(mContactModel)
    }

    private fun saveConfiguration() {
        mLoginViewModel.saveConfiguration(mUUID)
    }

    private fun loadAreaFirstTime() {
        mLoginViewModel.loadAreaFirstTime()
    }

    private fun loadPlaceFirstTime() {
        mLoginViewModel.loadPlaceFirstTime()
    }

    private fun loadContactFirstTime() {
        mLoginViewModel.loadContactFirstTime()
    }

    private fun loadProviderFirstTime() {
        mLoginViewModel.loadProviderFirstTime()
    }

    private fun loadSensorFirstTime() {
        mLoginViewModel.loadSensorFirstTime()
    }

    private fun loadLastTempRemote() {
        mLoginViewModel.loadLastTempRemote()
    }

    private fun saveLastTempLocal(model: List<LastTempModel>) {
        mLoginViewModel.saveLastTempLocal(model)
    }


    private fun dialogMessage(message: String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("KTiVO")
        builder.setMessage(message)
        builder.setIcon(R.mipmap.ic_launcher)

        builder.setPositiveButton(R.string.ok) { dialog, which ->
            Toast.makeText(applicationContext, R.string.null_msg, Toast.LENGTH_SHORT)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Maybe") { dialog, which ->
            Toast.makeText(applicationContext,
                "Maybe", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }


}

