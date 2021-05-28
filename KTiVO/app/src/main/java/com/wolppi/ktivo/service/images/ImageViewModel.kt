package com.wolppi.ktivo.service.images

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.wolppi.ktivo.service.repository.remote.retrofit.CheckInternet


class ImageViewModel(application: Application) : AndroidViewModel(application) {

    private val mCheckInternet = CheckInternet(application)
    private var mUploadUtility = UploadImage(application)

    fun loadImage(sourceFileUri: Uri, uploadedFileName: String?){
        mUploadUtility.uploadFile(sourceFileUri, uploadedFileName)
    }

}
