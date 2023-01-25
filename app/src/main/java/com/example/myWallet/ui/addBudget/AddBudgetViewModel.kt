package com.example.myWallet.ui.addBudget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myWallet.R
import com.example.myWallet.db.AccountsDao
import com.example.myWallet.db.CategoryDao
import com.example.myWallet.models.Account
import com.example.myWallet.models.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Add Budget ViewModel
 * @param categoryDao RoomDB Dao parameter
 *
 * @property calculate is a function
 *
 * @return [calculate] function return an double
 *
 * @receiver receiver of an extension function
 *
 * @throws
 * @exception ArithmeticException which can be thrown by a [calculate] method
 *
 * @sample
 *
 * @see com.example.myWallet.ui.addBudgetBottomSheet.AddBudgetBottomSheetFragment this is the fragment for this view-model
 *
 * @author vidhyasagar
 *
 * @since 1/24/2023
 *
 * @constructor AddBudgetViewModel hilt inject constructor
 *
 * @suppress
 */


@HiltViewModel
class AddBudgetViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    private val accountDao: AccountsDao
) : ViewModel() {

    companion object {
        const val INCOME_CATEGORY = "incomeCategory"
        const val EXPENSE_CATEGORY = "expenseCategory"
        const val CATEGORY = "category"
        const val ACCOUNT = "account"
        const val TO_ACCOUNT = "toAccount"
        const val SAVE = "save"
        const val CANCEL = "cancel"
    }

    private val _note = MutableStateFlow("")

    private val defaultAcc = Account(null, "Account", "0", -1)
    private val defaultCat = Category(null, "Category", "0", -1)

    private val _account = MutableStateFlow(defaultAcc)
    val account = _account.asStateFlow()

    private val _accountTo = MutableStateFlow(defaultAcc)
    val accountTo = _accountTo.asStateFlow()

    private val _category = MutableStateFlow(defaultCat)
    val category = _category.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _tempAmount = MutableStateFlow("")
    val tempAmount = _tempAmount.asStateFlow()

    private val canAdd = MutableStateFlow(false)

    private val _clicks = MutableSharedFlow<String>()
    val clicks = _clicks.asSharedFlow()

    private val _saveCancelClicks = MutableSharedFlow<String>()
    val saveCancelClicks = _saveCancelClicks.asSharedFlow()

    private val _symbol = MutableStateFlow("")
    val symbol = _symbol.asStateFlow()

    private val _action = MutableStateFlow(Category.EXPENSE)
    val action = _action.asStateFlow()

    fun onSelectLeft() {
        emitClicks(ACCOUNT)
    }

    fun onSelectRight() {
        if (_action.value == Category.TRANSFER) {
            emitClicks(TO_ACCOUNT)
        } else {
            if (_action.value == Category.INCOME) {
                emitClicks(INCOME_CATEGORY)
            } else {
                emitClicks(EXPENSE_CATEGORY)
            }
        }
    }

    fun onIncomeClick() {
        _action.update { Category.INCOME }
    }

    fun onExpenseClick() {
        _action.update { Category.EXPENSE }
    }

    fun onTransferClick() {
        _action.update { Category.TRANSFER }
    }

    fun onSaveClick() {
        saveBudgetToDb()
    }

    private fun saveBudgetToDb() {
        emitSaveCancel(SAVE)
    }

    fun onCancelClick() {
        emitSaveCancel(CANCEL)
    }


    fun modifyAmount(num: Char) {
        if (num == ' ' && amount.value.isNotEmpty()) {
            _amount.value = _amount.value.substring(0, _amount.value.length - 1)
        } else if (!num.toString().matches("[0-9]".toRegex())) {
            if (num == '=' && tempAmount.value.isEmpty()) {
                return
            }

            if (symbol.value.isEmpty() && num != '=') {
                _tempAmount.value = amount.value
                _amount.value = ""
                _symbol.value = num.toString()
            } else {
                if (num != '=') {
                    calculate()
                    _tempAmount.value = amount.value
                    _amount.value = ""
                    _symbol.value = num.toString()
                } else {
                    if (tempAmount.value.isNotEmpty()) {
                        calculate()
                        _tempAmount.value = ""
                        _symbol.value = ""
                    }
                }
            }
//        } else if (tempAmount.value.isNotEmpty()) {
        } else {
//            if(symbol.value.isNotEmpty()) {
//                _tempAmount.value = amount.value
//                _amount.value = ""
//            }
            _amount.update { it + num }

//            _amount.value += num.toString()
        }
    }

    private fun calculate() {
        when (symbol.value) {
            "+" -> {
                _amount.value = (tempAmount.value.toDouble() + amount.value.toDouble()).toString()
            }
            "-" -> {
                _amount.value = (tempAmount.value.toDouble() - amount.value.toDouble()).toString()
            }
            "*" -> {
                _amount.value = (tempAmount.value.toDouble() * amount.value.toDouble()).toString()
            }
            "/" -> {
                _amount.value = (tempAmount.value.toDouble() / amount.value.toDouble()).toString()
            }
        }
    }

    fun setSelectedAccount(id: Int) {
        viewModelScope.launch {
            val account = withContext(Dispatchers.IO) {
                accountDao.getAccountById(id)
            }
            account.let {
                _account.emit(it)
            }
        }
    }

    private fun emitClicks(click: String) {
        viewModelScope.launch {
            _clicks.emit(click)
        }
    }

    private fun emitSaveCancel(click: String) {
        viewModelScope.launch {
            _saveCancelClicks.emit(click)
        }
    }
}