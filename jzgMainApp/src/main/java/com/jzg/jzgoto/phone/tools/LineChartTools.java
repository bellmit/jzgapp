package com.jzg.jzgoto.phone.tools;

import java.util.ArrayList;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class LineChartTools {
	
	public static final int[] CHART_COLORS = {
         Color.rgb(255, 88, 0), Color.rgb(255, 127, 0), Color.rgb(255, 159, 0),
         Color.rgb(255, 194, 65), Color.rgb(255, 210, 38)
	 };
	public static void createLineChart(LineChart lineChart, ArrayList<String> xVal,
			ArrayList<Entry> yVals) {
		lineChart.setDrawBorders(false);  //是否在折线图上添加边框    
	    
		XAxis xAxis = lineChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setSpaceBetweenLabels(0);
		xAxis.setDrawGridLines(false);
		xAxis.setDrawAxisLine(true);
		xAxis.setTextColor(Color.LTGRAY);
		xAxis.setTextSize(14);
		xAxis.setAvoidFirstLastClipping(true);	//设置首末数据展现
//		xAxis.setLabelsToSkip(1);		//设置跳过几个显示

		YAxis yAlxis = lineChart.getAxisLeft();
		yAlxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
		yAlxis.setTextSize(14);
		yAlxis.setTextColor(Color.LTGRAY);
		yAlxis.setDrawAxisLine(false);
		yAlxis.setDrawGridLines(true);
		yAlxis.setAxisLineColor(Color.LTGRAY);
		
		
		
		
        // no description text    
        lineChart.setDescription("");// 数据描述    
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview    
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");    
            
        // enable / disable grid background    
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色    
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度    
    
        // enable touch gestures    
        lineChart.setTouchEnabled(false); // 设置是否可以触摸    
    
        // enable scaling and dragging    
        lineChart.setDragEnabled(false);// 是否可以拖拽    
        lineChart.setScaleEnabled(false);// 是否可以缩放    
    
        // if disabled, scaling can be done on x- and y-axis separately    
        lineChart.setPinchZoom(false);//     
    
//        lineChart.setBackgroundColor(Color.rgb(114, 188, 223));// 设置背景    
    
        // get the legend (only possible after setting data)    
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的    
    
        // modify the legend ...    
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);    
        mLegend.setForm(LegendForm.CIRCLE);// 样式    
        mLegend.setFormSize(6f);// 字体    
        mLegend.setTextColor(Color.WHITE);// 颜色    
//      mLegend.setTypeface(mTf);// 字体    
    
        lineChart.animateX(2500); // 立即执行的动画,x轴 
        
        
     // add a nice and smooth animation
     		// 禁止dateset
        lineChart.getLegend().setEnabled(false);
        lineChart.getAxisLeft().setEnabled(true);
        lineChart.getAxisRight().setEnabled(false);
     		// scaling can now only be done on x- and y-axis separately
        lineChart.setPinchZoom(false);
        lineChart.setDrawGridBackground(false);
        lineChart.getAxisLeft().setDrawGridLines(true);
     		

     // create a dataset and give it a type    
        // y轴的数据集合    
        LineDataSet lineDataSet = new LineDataSet(yVals, "测试折线图" /*显示在比例图上*/);    
        // mLineDataSet.setFillAlpha(110);    
        // mLineDataSet.setFillColor(Color.RED);    
    
        //用y轴的集合来设置参数    
        lineDataSet.setLineWidth(1.75f); // 线宽    
        lineDataSet.setCircleSize(4f);// 显示的圆形大小    
        lineDataSet.setColor(Color.rgb(83, 153, 232));// 显示颜色    
        lineDataSet.setCircleColor(Color.rgb(83, 153, 232));// 圆形的颜色    
        lineDataSet.setHighLightColor(Color.rgb(83, 153, 232)); // 高亮的线的颜色    
        
        lineDataSet.setDrawCircles(true); // 图标上的数据点不用小圆圈表示
        lineDataSet.setDrawCubic(true); // 设置允许曲线平滑
        lineDataSet.setCubicIntensity(0.4f); // 设置折现的平滑度
        lineDataSet.setDrawFilled(true); // 设置允许填充
        lineDataSet.setFillColor(Color.rgb(154, 226, 244)); // 设置允许填充颜色
        
        
    
        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();    
        lineDataSets.add(lineDataSet); // add the datasets    
    
        // create a data object with the datasets    
        LineData lineData = new LineData(xVal, lineDataSets);
        
        // add data    
        lineChart.setData(lineData); // 设置数据    
        lineData.setValueFormatter(new MyValueFormatter());
        lineChart.setData(lineData);
        lineChart.invalidate();
        
        for (DataSet<?> set1 : lineChart.getData().getDataSets()) {
			set1.setDrawValues(true);
			set1.setValueTextSize(14);
			set1.setValueTextColor(Color.LTGRAY);
		}
	}

}




