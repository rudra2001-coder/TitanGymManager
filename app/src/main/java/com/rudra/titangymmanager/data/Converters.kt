package com.rudra.titangymmanager.data

import androidx.room.TypeConverter
import com.rudra.titangymmanager.data.model.ExpenseCategory

class Converters {
    @TypeConverter
    fun fromExpenseCategory(category: ExpenseCategory): String {
        return category.name
    }

    @TypeConverter
    fun toExpenseCategory(category: String): ExpenseCategory {
        return ExpenseCategory.valueOf(category)
    }
}
