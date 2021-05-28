package com.wolppi.ktivo.listener

interface KtivoListener{

    /**
     * Click para edição
     */
    fun onListClick(uuid: String)

    /**
     * Remoção
     */
    fun onDeleteClick(uuid: String)
}