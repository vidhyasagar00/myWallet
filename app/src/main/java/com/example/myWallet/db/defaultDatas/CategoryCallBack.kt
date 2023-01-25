package com.example.myWallet.db.defaultDatas

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myWallet.db.CategoryDao
import com.example.myWallet.extras.DefaultCategories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class CategoryCallBack @Inject constructor(
    private val provider: Provider<CategoryDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        provider.get().insertDefaultCategory(DefaultCategories.list)
    }
}
