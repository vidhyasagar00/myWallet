package com.example.myWallet.ui.tabHomeFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myWallet.db.AppDatabase
import com.example.myWallet.models.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TabHomeViewModel @Inject constructor(
    var db: AppDatabase
) : ViewModel() {

    val accountPosition = MutableStateFlow(0)

    private val _accounts = MutableStateFlow(emptyList<Account>())
    val accounts = _accounts.asStateFlow()

    val amount: StateFlow<String?> = combine(accountPosition, _accounts) { position, acts ->
        when {
            acts.isNotEmpty() -> acts[position].initialAmount
            else -> "0"
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "0")


    val spinner: StateFlow<List<String>?> = _accounts.map { acc ->
        acc.map { it.title }.toList()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)


    fun getAccountsList() {
        Log.e("TAG_POSITION", "position: ${_accounts.value.size}")
        viewModelScope.launch(Dispatchers.IO) {
            val accounts = db.accountsDao().getAllAccounts()
            _accounts.update {
                withContext(Dispatchers.Default) { accounts }
            }
        }
    }
}