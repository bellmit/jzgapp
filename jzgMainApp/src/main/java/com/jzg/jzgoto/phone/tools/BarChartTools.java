package com.jzg.jzgoto.phone.tools;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.ValueFormatter;

public class BarChartTools {
	
	public static final int[] CHART_COLORS = {
         Color.rgb(255, 88, 0), Color.rgb(255, 127, 0), Color.rgb(255, 159, 0),
         Color.rgb(255, 194, 65), Color.rgb(255, 210, 38)
	 };
	public static void createBarChart(BarChart mChart, ArrayList<String> xVal,
			ArrayList<BarEntry> yVals) {
		// TODO 左上方简单标题有坐标适配问题，优化再做
		mChart.setDescription("");

		mChart.setMaxVisibleValueCount(10);
		mChart.setTouchEnabled(false);
		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setSpaceBetweenLabels(0);
		xAxis.setDrawGridLines(false);
		xAxis.setDrawAxisLine(true);
		xAxis.setTextColor(Color.LTGRAY);
		xAxis.setTextSize(14);

		YAxis yAlxis = mChart.getAxisLeft();
		yAlxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
		yAlxis.setTextSize(14);
		yAlxis.setTextColor(Color.LTGRAY);
		yAlxis.setDrawAxisLine(false);
		yAlxis.setDrawGridLines(true);
		yAlxis.setAxisLineColor(Color.LTGRAY);
		
		// add a nice and smooth animation
		mChart.animateY(2500);
		// 禁止dateset
		mChart.getLegend().setEnabled(false);
		mChart.getAxisLeft().setEnabled(true);
		mChart.getAxisRight().setEnabled(false);
		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);
		mChart.setDrawBarShadow(false);
		mChart.setDrawGridBackground(false);
		
		mChart.getAxisLeft().setDrawGridLines(true);
		
		// Legend l = mChart.getLegend();
		// l.setPosition(LegendPosition.BELOW_CHART_CENTER);
		// l.setFormSize(8f);
		// l.setFormToTextSpace(4f);
		// l.setXEntrySpace(6f);
		// l.setEnabled(false);

		// mChart.setDrawLegend(false);

		BarDataSet set = new BarDataSet(yVals, "data set");
		set.setColors(CHART_COLORS);
		set.setDrawValues(false);

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set);

		BarData data = new BarData(xVal, dataSets);
		data.setValueFormatter(new MyValueFormatter());
		mChart.setData(data);
		mChart.invalidate();

		for (DataSet<?> set1 : mChart.getData().getDataSets()) {
			set1.setDrawValues(true);
			set1.setValueTextSize(14);
			set1.setValueTextColor(Color.LTGRAY);
		}
	}

}

class MyValueFormatter implements ValueFormatter {

	private DecimalFormat mFormat;

	public MyValueFormatter() {
		mFormat = new DecimalFormat("###,###,###,##0.00");
	}

	@Override
	public String getFormattedValue(float value) {
		return mFormat.format(value) + "";
	}

}
