package com.wolppi.ktivo.service.constants

import android.media.session.MediaSession

class KtivoConstants private constructor() {

    // SharedPreferences
    object SHARED {
        const val KTIVO_SHARED      = "ktivoShared"
        const val UUID_CONTACT      = "uuidContact"
        const val NUMBER_INSTALL    = "numberInstall"
        const val URL_DATA          = "urlData"
        const val URL_PHOTOS        = "urlPhotos"
        const val URL_RECORDS       = "urlRecords"
        const val TOKEN             = "token"
    }

    // Requisições API
    object HEADER {
        const val TOKEN_KEY = "token"
        const val PERSON_KEY = "personkey"
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object BUNDLE {
        const val AREA = "areaId"
        const val PLACE = "placeId"
        const val CONTACT = "contactId"
        const val PROVIDER = "providerId"
        const val SENSOR = "sensorId"
    }

    object TEMPERATURE {
        const val ALL = "all"
        const val NORMAL = "normal"
        const val HOT = "hot"
        const val COLD = "cold"
    }

    object FILTER {
        const val FILTERTEMP = "temperature"
    }

}