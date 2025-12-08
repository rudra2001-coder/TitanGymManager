package com.rudra.titangymmanager.ui.screens.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.repository.ExpenseRepository
import com.rudra.titangymmanager.data.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _income = MutableStateFlow(0.0)
    val income = _income.asStateFlow()

    private val _expenses = MutableStateFlow(0.0)
    val expenses = _expenses.asStateFlow()

    val profitLoss = combine(income, expenses) { income, expenses ->
        income - expenses
    }

    fun loadReport(startDate: Long, endDate: Long) {
        viewModelScope.launch {
            paymentRepository.getPaymentsBetween(startDate, endDate).collect { payments ->
                _income.value = payments.sumOf { it.amount }
            }
        }
        viewModelScope.launch {
            expenseRepository.getExpensesBetween(startDate, endDate).collect { expenses ->
                _expenses.value = expenses.sumOf { it.amount }
            }
        }
    }

    fun loadDailyReport() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startDate = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val endDate = calendar.timeInMillis
        loadReport(startDate, endDate)
    }

    fun loadWeeklyReport() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        val startDate = calendar.timeInMillis
        calendar.add(Calendar.WEEK_OF_YEAR, 1)
        val endDate = calendar.timeInMillis
        loadReport(startDate, endDate)
    }

    fun loadMonthlyReport() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val startDate = calendar.timeInMillis
        calendar.add(Calendar.MONTH, 1)
        val endDate = calendar.timeInMillis
        loadReport(startDate, endDate)
    }

    fun loadYearlyReport() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, 1)
        val startDate = calendar.timeInMillis
        calendar.add(Calendar.YEAR, 1)
        val endDate = calendar.timeInMillis
        loadReport(startDate, endDate)
    }
}
