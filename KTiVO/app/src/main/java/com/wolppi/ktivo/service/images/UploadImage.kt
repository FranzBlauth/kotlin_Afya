package com.wolppi.ktivo.service.images

import android.app.Application
import android.app.ProgressDialog
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.internal.http.promisesBody
import java.io.File


class UploadImage(activity: Application) {

    var activity = activity;
    var dialog: ProgressDialog? = null
    private var serverURL: String = ""
    var serverAPI: String = "Configuration/filetransfer.php"
    var serverUploadDirectoryPath: String = ""
    private val client = OkHttpClient()
    private val mSharedPreferenceRepository = SharedPreferenceRepository(activity)

    fun uploadFile(sourceFileUri: Uri, uploadedFileName: String? = null) {
        val b = sourceFileUri
        val pathFromUri = URIPathHelper().getPath(activity, sourceFileUri)
        val c = ""
        serverUploadDirectoryPath = mSharedPreferenceRepository.getShared(KtivoConstants.SHARED.URL_DATA)
        serverURL = serverUploadDirectoryPath + serverAPI
        val ffiillee = File(pathFromUri)
        val a = ffiillee.length()
        uploadFile(File(pathFromUri), uploadedFileName)
    }

    private fun uploadFile(sourceFile: File, uploadedFileName: String? = null) {
        Thread {
            val mimeType = getMimeType(sourceFile);
            val tamanho = sourceFile.totalSpace
            if (mimeType == null) {
                Log.e("file error", "Not able to get mime type")
                return@Thread
            }
            val fileName: String = uploadedFileName ?: sourceFile.name
            try {
                val requestBody: RequestBody =
                    MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart(
                            "file",
                            fileName,
                            sourceFile.asRequestBody(mimeType.toMediaTypeOrNull())
                        )
                        .build()
                val request: Request = Request.Builder().url(serverURL).post(requestBody).build()
                val response: Response = client.newCall(request).execute()
                val a = ""
                if (response.isSuccessful) {
                    val h = response.body!!.string()
                    val c = response.body.toString()
                    val d = response.promisesBody()
                    Log.d("Retorno", "voltou do filetransfer $c")
                    Log.d("File upload", "success, path: $serverUploadDirectoryPath$fileName")
                    showToast("File uploaded successfully at $serverUploadDirectoryPath$fileName")
                } else {
                    Log.e("File upload", "failed")
                    showToast("File uploading failed")
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.e("File upload", "failed")
                showToast("File uploading failed")
            }
        }.start()
    }

    // url = file path or whatever suitable URL you want.
    private fun getMimeType(file: File): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    private fun showToast(message: String) {
            val a = ""
    }


}