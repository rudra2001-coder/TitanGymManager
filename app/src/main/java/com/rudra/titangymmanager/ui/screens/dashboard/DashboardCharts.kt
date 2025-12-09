package com.rudra.titangymmanager.ui.screens.dashboard

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.rudra.titangymmanager.data.model.MonthlyIncome
import com.rudra.titangymmanager.data.model.MonthlyNewMembers
import androidx.core.graphics.toColorInt

@Composable
fun ActiveExpiredPieChart(active: Int, expired: Int) {
    AndroidView(factory = { context ->
        PieChart(context).apply {
            val entries = mutableListOf<PieEntry>()

            if (active > 0) entries.add(PieEntry(active.toFloat(), "Active"))
            if (expired > 0) entries.add(PieEntry(expired.toFloat(), "Expired"))

            if (entries.isEmpty()) {
                entries.add(PieEntry(1f, "No Data"))
            }

            val dataSet = PieDataSet(entries, "")

            // Premium color palette
            val colors = mutableListOf<Int>()
            if (active > 0) colors.add(android.graphics.Color.parseColor("#4CAF50"))
            if (expired > 0) colors.add(android.graphics.Color.parseColor("#F44336"))
            if (entries.size == 1 && active + expired == 0) {
                colors.add(android.graphics.Color.parseColor("#9E9E9E"))
            }

            dataSet.colors = colors
            dataSet.sliceSpace = 2f
            dataSet.selectionShift = 7f
            dataSet.valueTextSize = 14f
            dataSet.valueTextColor = android.graphics.Color.WHITE
            dataSet.valueTypeface = android.graphics.Typeface.DEFAULT_BOLD
            dataSet.setDrawValues(true)

            val pieData = PieData(dataSet)
            pieData.setValueFormatter(PercentFormatter(this))
            data = pieData

            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 45f
            transparentCircleRadius = 50f
            var transparentCircleColor = android.graphics.Color.parseColor("#11000000")
            setHoleColor(android.graphics.Color.WHITE)

            // Center text with styling
            setDrawCenterText(true)
            if (active + expired > 0) {
                val percent = if (active + expired > 0) {
                    (active * 100 / (active + expired))
                } else 0
                centerText = "Active\n$percent%"
            } else {
                centerText = "No\nData"
            }

            // Style center text
            setCenterTextSize(18f)
            setCenterTextColor("#333333".toColorInt())
            var centerTextTypeface = android.graphics.Typeface.DEFAULT_BOLD

            // Legend customization
            legend.isEnabled = false

            // Animation
            animateY(1400, com.github.mikephil.charting.animation.Easing.EaseInOutQuad)

            // Extra settings for premium look
            setDrawEntryLabels(false)
            setUsePercentValues(true)
            setTransparentCircleAlpha(50)
            setEntryLabelColor(android.graphics.Color.WHITE)
            setEntryLabelTextSize(12f)
        }
    })
}

@Composable
fun MonthlyNewMembersBarChart(monthlyNewMembers: List<MonthlyNewMembers>) {
    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                val entries = monthlyNewMembers.mapIndexed { index, data ->
                    BarEntry(index.toFloat(), data.count.toFloat())
                }
                val dataSet = BarDataSet(entries, "New Members")

                // Premium gradient for bars
                val startColor = android.graphics.Color.parseColor("#4CAF50")
                val endColor = android.graphics.Color.parseColor("#388E3C")
                dataSet.colors = listOf(startColor)
                dataSet.setGradientColor(startColor, endColor)
                dataSet.valueTextSize = 11f
                dataSet.valueTextColor = android.graphics.Color.WHITE
                dataSet.valueTypeface = android.graphics.Typeface.DEFAULT_BOLD
                dataSet.setDrawValues(true)

                // Rounded bars
                dataSet.barBorderWidth = 0f

                val barData = BarData(dataSet)
                barData.barWidth = 0.5f
                barData.setValueTextSize(10f)
                data = barData

                // X-axis premium styling
                val labels = monthlyNewMembers.map {
                    it.month.substring(0, 3)  // Show only first 3 letters
                }
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.textSize = 11f
                xAxis.textColor = android.graphics.Color.parseColor("#666666")
                xAxis.axisLineColor = android.graphics.Color.parseColor("#E0E0E0")
                xAxis.axisLineWidth = 1.5f
                xAxis.labelCount = labels.size.coerceAtMost(6)
                xAxis.granularity = 1f
                xAxis.setDrawAxisLine(true)
                xAxis.typeface = android.graphics.Typeface.DEFAULT_BOLD

                // Left Y-axis premium styling
                axisLeft.setDrawGridLines(true)
                axisLeft.gridColor = android.graphics.Color.parseColor("#E8E8E8")
                axisLeft.gridLineWidth = 1f
                axisLeft.textColor = android.graphics.Color.parseColor("#666666")
                axisLeft.axisMinimum = 0f
                axisLeft.setDrawAxisLine(false)
                axisLeft.textSize = 10f
                axisLeft.typeface = android.graphics.Typeface.DEFAULT_BOLD
                axisLeft.axisLineWidth = 1.5f

                // Right Y-axis
                axisRight.isEnabled = false

                // Description
                description.isEnabled = false

                // Legend
                legend.isEnabled = false

                // Extra premium settings
                setDrawGridBackground(false)
                setDrawBorders(false)
                setTouchEnabled(true)
                setPinchZoom(false)
                setDoubleTapToZoomEnabled(false)
                setDrawBarShadow(false)
                setScaleEnabled(true)
                setDragEnabled(true)

                // Animation
                animateY(1200, com.github.mikephil.charting.animation.Easing.EaseInOutQuad)

                // Viewport
                setVisibleXRangeMaximum(6f)

                // Fit bars within view
                val fitBars = null
                fitBars
                invalidate()
            }
        },
        modifier = Modifier.height(250.dp)
    )
}

@Composable
fun MonthlyIncomeGraph(monthlyIncomeData: List<MonthlyIncome>) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                val entries = monthlyIncomeData.mapIndexed { index, data ->
                    Entry(index.toFloat(), data.income.toFloat())
                }
                val dataSet = LineDataSet(entries, "Monthly Income")

                // Premium line styling
                dataSet.color = android.graphics.Color.parseColor("#2196F3")
                dataSet.lineWidth = 3f
                dataSet.setDrawCircles(true)
                dataSet.setDrawCircleHole(true)
                dataSet.circleRadius = 5f
                dataSet.circleHoleRadius = 2.5f
                dataSet.circleColors = listOf(android.graphics.Color.parseColor("#2196F3"))
                dataSet.circleHoleColor = android.graphics.Color.WHITE
                dataSet.valueTextSize = 10f
                dataSet.valueTextColor = android.graphics.Color.parseColor("#333333")
                dataSet.valueTypeface = android.graphics.Typeface.DEFAULT_BOLD
                dataSet.setDrawValues(true)

                // Gradient fill under line
                dataSet.setDrawFilled(true)
                val gradientDrawable = android.graphics.drawable.GradientDrawable(
                    android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(
                        android.graphics.Color.parseColor("#552196F3"),
                        android.graphics.Color.parseColor("#052196F3")
                    )
                )
                gradientDrawable.cornerRadius = 0f
                dataSet.fillDrawable = gradientDrawable

                // Enable cubic lines for smooth curves
                dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
                dataSet.cubicIntensity = 0.2f

                val lineData = LineData(dataSet)
                data = lineData

                // X-axis premium styling
                val labels = monthlyIncomeData.map {
                    it.month.substring(0, 3)
                }
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.textSize = 11f
                xAxis.textColor = android.graphics.Color.parseColor("#666666")
                xAxis.axisLineColor = android.graphics.Color.parseColor("#E0E0E0")
                xAxis.axisLineWidth = 1.5f
                xAxis.labelCount = labels.size.coerceAtMost(6)
                xAxis.granularity = 1f
                xAxis.setDrawAxisLine(true)
                xAxis.typeface = android.graphics.Typeface.DEFAULT_BOLD

                // Left Y-axis with currency formatting
                axisLeft.setDrawGridLines(true)
                axisLeft.gridColor = android.graphics.Color.parseColor("#E8E8E8")
                axisLeft.gridLineWidth = 1f
                axisLeft.textColor = android.graphics.Color.parseColor("#666666")
                axisLeft.axisMinimum = 0f
                axisLeft.setDrawAxisLine(false)
                axisLeft.textSize = 10f
                axisLeft.typeface = android.graphics.Typeface.DEFAULT_BOLD
                axisLeft.valueFormatter = object : ValueFormatter() {
                    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                        return "৳${value.toInt()}"
                    }
                }

                // Right Y-axis disabled
                axisRight.isEnabled = false

                // Description
                description.isEnabled = false

                // Legend
                legend.isEnabled = false

                // Extra premium settings
                setDrawGridBackground(false)
                setDrawBorders(false)
                setTouchEnabled(true)
                setPinchZoom(true)
                setDoubleTapToZoomEnabled(true)
                setScaleEnabled(true)
                setDragEnabled(true)

                // Animation
                animateX(1500, com.github.mikephil.charting.animation.Easing.EaseInOutQuad)

                // Viewport
                setVisibleXRangeMaximum(6f)

                invalidate()
            }
        },
        modifier = Modifier.height(250.dp)
    )
}