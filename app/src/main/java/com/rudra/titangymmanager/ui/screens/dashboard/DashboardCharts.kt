package com.rudra.titangymmanager.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.rudra.titangymmanager.data.model.MonthlyNewMembers

@Composable
fun ActiveExpiredPieChart(active: Int, expired: Int) {
    AndroidView(factory = { context ->
        PieChart(context).apply {
            val entries = listOf(
                PieEntry(active.toFloat(), "Active"),
                PieEntry(expired.toFloat(), "Expired")
            )
            val dataSet = PieDataSet(entries, "Member Status")
            dataSet.colors = listOf(
                android.graphics.Color.GREEN,
                android.graphics.Color.RED
            )
            data = PieData(dataSet)
            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 58f
            transparentCircleRadius = 61f
            setDrawCenterText(true)
            centerText = "Members"
            animateY(1000)
        }
    })
}

@Composable
fun MonthlyNewMembersBarChart(monthlyNewMembers: List<MonthlyNewMembers>) {
    AndroidView(factory = { context ->
        BarChart(context).apply {
            val entries = monthlyNewMembers.mapIndexed { index, data ->
                BarEntry(index.toFloat(), data.count.toFloat())
            }
            val dataSet = BarDataSet(entries, "New Members")
            val labels = monthlyNewMembers.map { it.month }

            data = BarData(dataSet)
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            description.isEnabled = false
            animateY(1000)
        }
    })
}
