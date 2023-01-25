package com.example.myWallet.extras

import com.example.myWallet.R
import com.example.myWallet.models.Category
import com.example.myWallet.utils.toUri

object DefaultCategories {
    var list = mutableListOf(
        Category(1, "Awards", Category.INCOME, MyList.category.indexOf(R.drawable.money_bag_1)),
        Category(2, "Coupons", Category.INCOME, MyList.category.indexOf(R.drawable.voucher)),
        Category(3, "Grants", Category.INCOME, MyList.category.indexOf(R.drawable.info)),
        Category(4, "Lottery", Category.INCOME, MyList.category.indexOf(R.drawable.info)),
        Category(5, "Refunds", Category.INCOME, MyList.category.indexOf(R.drawable.refund)),
        Category(6, "Rental", Category.INCOME, MyList.category.indexOf(R.drawable.info)),
        Category(7, "Salary", Category.INCOME, MyList.category.indexOf(R.drawable.wallet)),
        Category(8, "Sale", Category.INCOME, MyList.category.indexOf(R.drawable.info)),
        Category(9, "Renting", Category.INCOME, MyList.category.indexOf(R.drawable.info)),
//checked  humanitarian money money 2
        Category(10, "Beauty", Category.EXPENSE, MyList.category.indexOf(R.drawable.beauty)),
        Category(11, "Bills", Category.EXPENSE, MyList.category.indexOf(R.drawable.tax)),
        Category(12, "Car", Category.EXPENSE, MyList.category.indexOf(R.drawable.info)),
        Category(13, "Clothing", Category.EXPENSE, MyList.category.indexOf(R.drawable.apparel)),
        Category(14, "Education", Category.EXPENSE, MyList.category.indexOf(R.drawable.mortarboard)),
        Category(15, "Electronics", Category.EXPENSE, MyList.category.indexOf(R.drawable.responsive)),
        Category(16, "Entertainment", Category.EXPENSE, MyList.category.indexOf(R.drawable.clapperboard)),
        Category(17, "Food", Category.EXPENSE, MyList.category.indexOf(R.drawable.restaurant)),
        Category(18, "Health", Category.EXPENSE, MyList.category.indexOf(R.drawable.heartbeat)),
        Category(19, "Home", Category.EXPENSE, MyList.category.indexOf(R.drawable.plot)),
        Category(20, "Insurance", Category.EXPENSE, MyList.category.indexOf(R.drawable.info)),
        Category(21, "Shopping", Category.EXPENSE, MyList.category.indexOf(R.drawable.shopping_cart)),
        Category(22, "Social", Category.EXPENSE, MyList.category.indexOf(R.drawable.info)),
        Category(23, "Sport", Category.EXPENSE, MyList.category.indexOf(R.drawable.tennis)),
        Category(24, "Tax", Category.EXPENSE, MyList.category.indexOf(R.drawable.info)),
        Category(25, "Telephone", Category.EXPENSE, MyList.category.indexOf(R.drawable.telephone)),
        Category(26, "Transportation", Category.EXPENSE, MyList.category.indexOf(R.drawable.bus)),
        Category(27, "Baby", Category.EXPENSE, MyList.category.indexOf(R.drawable.baby_bottle)),
    )
}