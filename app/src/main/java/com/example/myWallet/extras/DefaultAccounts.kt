package com.example.myWallet.extras

import com.example.myWallet.R
import com.example.myWallet.models.Account

object DefaultAccounts {
    val list = listOf(
        Account(1, "cash", "0", MyList.account.indexOf(R.drawable.wallet)),
        Account(2, "bank", "0", MyList.account.indexOf(R.drawable.credit_card)),
    )
}