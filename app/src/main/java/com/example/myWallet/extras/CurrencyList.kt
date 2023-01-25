package com.example.myWallet.extras

import com.example.myWallet.models.Currency


//https://gist.githubusercontent.com/ksafranski/2973986/raw/5fda5e87189b066e11c1bf80bbfbecb556cf2cc1/Common-Currency.json
object CurrencyList {
    val list = listOf(
        Currency(1,"Russian Ruble", "₽", "RUB", "Abkhazia"),
        Currency(2,"Euro", "€", "EUR", "Andorra"),
        Currency(3,"Euro", "€", "EUR", "Austria"),
        Currency(4,"Euro", "€", "EUR", "Belgium"),
        Currency(5,"Euro", "€", "EUR", "Croatia"),
        Currency(6,"Euro", "€", "EUR", "Cyprus"),
        Currency(7,"Euro", "€", "EUR", "Estonia"),
        Currency(8,"Euro", "€", "EUR", "Finland"),
        Currency(9,"Euro", "€", "EUR", "France"),
        Currency(10,"Euro", "€", "EUR", "Germany"),
        Currency(11,"Euro", "€", "EUR", "Greece"),
        Currency(12,"Euro", "€", "EUR", "Ireland"),
        Currency(13,"Rupee", "₹", "INR", "India"),
        Currency(14,"Iraq dinar", "ID", "IQD", "Iraq"),
        Currency(15,"Israeli new shekel", "₪", "ILS", "Israel"),
        Currency(16,"Japanese yen", "¥", "JPY", "Japan"),
        Currency(17,"North Korean Won", "₩", "KPW", "North Korea"),
        Currency(18,"South Korean Won", "₩", "KPW", "South Korea"),
        Currency(19,"Nepalese Rupee", "Rs", "NPR", "Nepal"),
    )
}