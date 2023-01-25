package com.example.myWallet.ui.addBudgetBottomSheet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myWallet.db.AppDatabase
import com.example.myWallet.models.Account
import com.example.myWallet.models.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddBudgetBottomSheetViewModel @Inject constructor(private val db: AppDatabase) : ViewModel() {

    companion object {
        const val ADD_CLICKED = "buttonAdd"
    }

    private val _categoryList = MutableStateFlow(emptyList<Category>())
    val categoryList = _categoryList.asStateFlow()

    private val _accountList = MutableStateFlow(emptyList<Account>())
    val accountList = _accountList.asStateFlow()

    private val _clicks = MutableSharedFlow<String>()
    val clicks = _clicks.asSharedFlow()

    fun getCategory() {
        Log.e("TAG_POSITION", "getCategory: ${_categoryList.value.size}")
        viewModelScope.launch(Dispatchers.IO) {
            _categoryList.update {
                withContext(Dispatchers.Default) { db.categoryDao().getAllCategories() }
            }

        }
    }

    fun getAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            _accountList.update { withContext(Dispatchers.Default) { db.accountsDao().getAllAccounts() } }
        }
    }

    fun onAddClick() {
        viewModelScope.launch {
            _clicks.emit(ADD_CLICKED)
        }
    }
}