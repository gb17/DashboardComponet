package inc.gb.dashboardcomponet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by GB on 1/11/16.
 */
public class GraphChart {
    Context mContext;
    Typeface tf;
    TextView threeTextViewforpie;
    TextView threeTextViewforline;
    LineChart mLineChart;
    PieChart mPieChart;
    BarChart stackedBarChart;
    BarChart mChart;
    TextView threeTextViewforStackedBarChart;

    public BarChart getStackedBarChart() {
        return stackedBarChart;
    }

    public void setStackedBarChart(BarChart stackedBarChart) {
        this.stackedBarChart = stackedBarChart;
    }

    public TextView getThreeTextViewforStackedBarChart() {
        return threeTextViewforStackedBarChart;
    }

    public void setThreeTextViewforStackedBarChart(TextView threeTextViewforStackedBarChart) {
        this.threeTextViewforStackedBarChart = threeTextViewforStackedBarChart;
    }


    public TextView getThreeTextViewforline() {
        return threeTextViewforline;
    }

    public void setThreeTextViewforline(TextView threeTextViewforline) {
        this.threeTextViewforline = threeTextViewforline;
    }


    public TextView getThreeTextViewforpie() {
        return threeTextViewforpie;
    }

    public void setThreeTextViewforpie(TextView threeTextViewforpie) {
        this.threeTextViewforpie = threeTextViewforpie;
    }


    public PieChart getmPieChart() {
        return mPieChart;
    }

    public void setmPieChart(PieChart mPieChart) {
        this.mPieChart = mPieChart;
    }


    private int[] mColors = new int[]{
            Color.GREEN,
            Color.RED,
            Color.BLUE
    };


    public LineChart getmLineChart() {
        return mLineChart;
    }

    public void setmLineChart(LineChart mLineChart) {
        this.mLineChart = mLineChart;
    }


    GraphChart(Context mContext) {
        this.mContext = mContext;

    }

    public View getPieChart(String Heading, String[] XavluesLable, boolean XavluesLableToShow, String[] yValues, boolean yValuesToShow, boolean centerHole,
                            int ChartLableColor, int LegendTextColor, Legend.LegendPosition position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View pieView = inflater.inflate(R.layout.graph_layout_piechart, null);
        mPieChart = (PieChart) pieView.findViewById(R.id.chart1);
        setmPieChart(mPieChart);
        TextView headingTextView = (TextView) pieView.findViewById(R.id.heading);
        headingTextView.setText(Heading);
        threeTextViewforpie = (TextView) pieView.findViewById(R.id.three);

        setThreeTextViewforpie(threeTextViewforpie);


        //   PieChart mPieChart = new PieChart(mContext);

        mPieChart.setUsePercentValues(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        mPieChart.setDescription("");
        tf = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        mPieChart.setDrawHoleEnabled(centerHole);
        mPieChart.setHoleColorTransparent(true);
        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);


        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        /**
         * Set data and color to pieChart
         * */

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < yValues.length; i++) {
            yVals1.add(new Entry(Float.parseFloat(yValues[i]), i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < XavluesLable.length; i++)
            xVals.add(XavluesLable[i]);

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);


        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);


        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);


        //  data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(ChartLableColor);
        data.setValueTypeface(tf);
        mPieChart.setData(data);

        // undo all highlights
        mPieChart.highlightValues(null);

        mPieChart.invalidate();


        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mPieChart.spin(2000, 0, 360);


        ///Lable to show or not

        if (XavluesLableToShow)
            for (IDataSet<?> set : mPieChart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());

        if (yValuesToShow)
            mPieChart.setDrawSliceText(!mPieChart.isDrawSliceTextEnabled());


        Legend l = mPieChart.getLegend();

        l.setPosition(position);
        l.setTextColor(LegendTextColor);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);


        return pieView;


    }


    public View getLineChart(String Heading, String[] xValues, String[][] yValues, String[] Datalabel, int[] mColors) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View lineView = inflater.inflate(R.layout.graph_layout_linechart, null);
        mLineChart = (LineChart) lineView.findViewById(R.id.chart1);
        setmLineChart(mLineChart);

        TextView headingTextView = (TextView) lineView.findViewById(R.id.heading);
        headingTextView.setText(Heading);

        setThreeTextViewforline(headingTextView);

        mLineChart.setDrawGridBackground(false);
        mLineChart.setDescription("");
        mLineChart.setDrawBorders(true);

        mLineChart.animateXY(3000, 3000);


        mLineChart.getAxisLeft().setDrawAxisLine(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawAxisLine(false);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawLabels(false);
        mLineChart.getXAxis().setDrawAxisLine(false);
        mLineChart.getXAxis().setDrawGridLines(false);

        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getXAxis().setTextColor(Color.GRAY);


        // enable touch gestures
        mLineChart.setTouchEnabled(false);

        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(false);

        mLineChart.resetTracking();


        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xValues.length; i++) {
            xVals.add(xValues[i]);
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        for (int z = 0; z < yValues.length; z++) {

            ArrayList<Entry> values = new ArrayList<Entry>();

            for (int i = 0; i < yValues[z].length; i++) {

                values.add(new Entry(Float.parseFloat(yValues[z][i]), i));
            }

            LineDataSet d = new LineDataSet(values, Datalabel[z]);

            d.setLineWidth(2.5f);
            d.setCircleSize(4f);

            int color = mColors[z % mColors.length];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets.add(d);
        }

        // make the first DataSet dashed


        ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        LineData data = new LineData(xVals, dataSets);
        mLineChart.setData(data);
        mLineChart.invalidate();

        List<ILineDataSet> sets = mLineChart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
        Legend l = mLineChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        return lineView;


    }

    public View getStackBarChart(String Heading, String[] xLegneds, String[] xLables, float[][] dataset, int[] StackColors) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View stackedBarview = inflater.inflate(R.layout.graph_layout_stackbar, null);
        stackedBarChart = (BarChart) stackedBarview.findViewById(R.id.chart1);


        setStackedBarChart(stackedBarChart);


        TextView headingTextView = (TextView) stackedBarview.findViewById(R.id.heading);
        headingTextView.setText(Heading);

        setThreeTextViewforStackedBarChart(headingTextView);
        stackedBarChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        stackedBarChart.setMaxVisibleValueCount(60);

        stackedBarChart.setDrawGridBackground(false);

        // scaling can now only be done on x- and y-axis separately
        stackedBarChart.setPinchZoom(false);

        stackedBarChart.setDrawGridBackground(false);
        stackedBarChart.setDrawBarShadow(false);

        stackedBarChart.setDrawValueAboveBar(false);
        stackedBarChart.animateXY(3000, 3000);
        // change the position of the y-labels
        YAxis yLabels = stackedBarChart.getAxisLeft();

        stackedBarChart.getAxisRight().setEnabled(false);

        XAxis xLabels = stackedBarChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        XAxis xAxis = stackedBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelsToSkip(0);

        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(10);


        YAxis leftAxis = stackedBarChart.getAxisLeft();

        leftAxis.setDrawGridLines(false);

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);

        // setting data
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xLables.length; i++) {
            xVals.add(xLables[i]);
        }
//float [][] where no. of col is number competitor and no .of rows no xvalues
        float[][] dataSet = {{200, 400, 300}, {300, 900, 500}, {400, 800, 700}, {500, 900, 100}};

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();


        for (int i = 0; i < xLables.length; i++) {
            List<Float> list = new ArrayList<Float>();
            for (int i1 = 0; i1 < dataSet[i].length; i1++)
                list.add(dataSet[i][i1]);

            float[] arr = convertIntegers(list);

            yVals1.add(new BarEntry(arr, i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(StackColors);
        set1.setStackLabels(xLegneds);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        // data.setValueFormatter(new MyValueFormatter());

        stackedBarChart.setData(data);


        for (IBarDataSet set : stackedBarChart.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());
        Legend l = stackedBarChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        return stackedBarview;

    }


    public View getBarChart(String[] xValuesLabel, String[] yValues) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_barchart, null);

        mChart = (BarChart) view.findViewById(R.id.chart1);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        Typeface mTf = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);

        //    YAxisValueFormatter custom = new MyYAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setDrawGridLines(false);
        leftAxis.setLabelCount(8, false);
        //     leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(3, false);
        rightAxis.setDrawLabels(false);
        //    rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setYOffset(2f);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xValuesLabel.length; i++) {
            xVals.add(xValuesLabel[i]);
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < xValuesLabel.length; i++) {
            yVals1.add(new BarEntry((Float.parseFloat(yValues[i])), i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
        set1.setBarSpacePercent(35f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
        data.setValueTypeface(mTf);

        mChart.setData(data);
        return view;
    }


    public View getMultiBarChart(String[] xValues, int[] colorArray, String[] competitors, int[][] mainData) {
        //2-D array no of column has to be number of competitor and no of rows should be equal to no of x values
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_multibarchart, null);

        mChart = (BarChart) view.findViewById(R.id.chart1);
        mChart.setDescription("");

//        mChart.setDrawBorders(true);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        //MyMarkerView mv = new MyMarkerView(context, R.layout.custom_marker_view);

        // define an offset to change the original position of the marker
        // (optional)
        // mv.setOffsets(-mv.getMeasuredWidth() / 2, -mv.getMeasuredHeight());

        // set the marker to the chart
        //mChart.setMarkerView(mv);

        tf = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xValues.length; i++) {
            xVals.add(xValues[i]);
        }


        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        BarDataSet set1 = null;

        for (int values = 0; values < competitors.length; values++) {

            ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();

            for (int i = 0; i < xValues.length; i++) {
                yValues.add(new BarEntry(mainData[i][values], i));
            }
            set1 = new BarDataSet(yValues, competitors[values]);
            set1.setColor(colorArray[values]);
            dataSets.add(set1);
        }


        BarData data = new BarData(xVals, dataSets);
//        data.setValueFormatter(new LargeValueFormatter());

        // add space between the dataset groups in percent of bar-width
        data.setGroupSpace(80f);
        data.setValueTypeface(tf);

        mChart.setData(data);
        mChart.invalidate();


        for (IBarDataSet set : mChart.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setTypeface(tf);
        l.setYOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xl = mChart.getXAxis();
        xl.setTypeface(tf);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawGridLines(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(30f);
        mChart.getAxisRight().setEnabled(false);
        return view;
    }


    public float[] convertIntegers(List<Float> integers) {
        float[] ret = new float[integers.size()];
        Iterator<Float> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().floatValue();
        }
        return ret;
    }


    private int[] getColors() {

        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < stacksize; i++) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS[i];
        }

        return colors;
    }


}
