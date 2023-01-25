package com.example.myWallet.ui.newCategoryPopup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewCategoryPopupViewModel : ViewModel() {
    private val _type = MutableStateFlow("")
    val type = _type.asStateFlow()

    fun setType(t: String) {
        _type.update { t }
    }
}