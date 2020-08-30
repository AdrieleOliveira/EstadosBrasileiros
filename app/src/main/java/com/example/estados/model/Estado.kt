package com.example.estados.model

import java.io.Serializable

data class Estado (
    var id: Long,
    var nome: String,
    var sigla: String,
    var regiao: Regiao
): Serializable {}