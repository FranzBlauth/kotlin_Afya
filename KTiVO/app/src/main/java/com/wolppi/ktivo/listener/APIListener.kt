package com.wolppi.ktivo.listener

interface APIListener<T> {

    fun onSuccess(model: T)

    fun onFailure(str: String)

}