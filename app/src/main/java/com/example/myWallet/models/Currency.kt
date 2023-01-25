package com.example.myWallet.models

data class Currency(
    val id: Int,
    var name: String? = null,
    var symbol: String? = null,
    var code: String? = null,
    var country: String? = null,
    var isAdded: Boolean = false,
)