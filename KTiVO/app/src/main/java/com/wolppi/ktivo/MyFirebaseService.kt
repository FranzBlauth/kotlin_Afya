package com.wolppi.ktivo

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG", "Meu Token achado: $token")
    }
//    override fun onMessageReceived(p0: RemoteMessage) {
//        val a = ""
//        super.onMessageReceived(p0)
//    }
}

//fcfXTK_zRSexL_29dVHjtP:APA91bH9gHrN2sx9QBkL0zpCx9UPT9lAbnPBxhpL6IdDWFK33ZqgyaY5D-2LgLGO4Uo42_tmGD1Y1k5sUwB2Kc7ZHU2sqAzaxLQSzxn4IW5YZBXWewNb1YmYHMGXSVh2wQGcgiygrP6a