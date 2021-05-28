package com.wolppi.ktivo.service.images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wolppi.ktivo.R
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_pick_image.*

@Suppress("DEPRECATION")
class PickGalleryActivity : AppCompatActivity() {

    private lateinit var mImageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_image)

        mImageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        //BUTTON CLICK

        btnTakePhoto.setOnClickListener {

            var popup = PopupMenu(this, btnTakePhoto)
            popup.inflate(R.menu.pick_images_menu)
            popup.setOnMenuItemClickListener {
                Toast.makeText(this, "Item : " + it.title, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()

//            //check runtime permission
//            if (VERSION.SDK_INT >= VERSION_CODES.M) {
//                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
//                    PackageManager.PERMISSION_DENIED
//                ) {
//                    //permission denied
//                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
//                    //show popup to request runtime permission
//                    requestPermissions(permissions, PERMISSION_CODE);
//                } else {
//                    //permission already granted
//                    pickImageFromGallery();
//                }
//            } else {
//                //system OS is < Marshmallow
//                pickImageFromGallery();
//            }
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;

        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            viewImage.setImageURI(data?.data)
            if (data?.data != null) {
                 var imageUri: Uri = data.data!!
                 mImageViewModel.loadImage(imageUri, "Vamos na Unimed")
            }
        }
    }
}