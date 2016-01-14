package inc.gb.dashboardcomponet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.components.Legend;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;

    String xValues[] = {"Jan", "FEB", "MAR", "APR"};

    String[][] yValues = {{"2", "40", "60", "34"}, {"19", "66", "34", "54"},
            {"33", "50", "54", "70"}
    };


    LinearLayout.LayoutParams param;

    String[] Datalabel = {"Mezzo", "C-FIX", "StilSep"};

    private int[] mColors = new int[]{
            Color.GREEN,
            Color.RED,
            Color.BLUE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.test);



        GraphChart graphChart = new GraphChart(this);
        View pieChart = graphChart.getPieChart("Dr Assist", new String[]{"A", "B", "C", "D"}, true, new String[]{"24", "56", "34", "35"}, true, false, Color.BLACK, Color.BLACK, Legend.LegendPosition.BELOW_CHART_CENTER);

        param = new LinearLayout.LayoutParams(350, 350);
        param.rightMargin = 20;


        linearLayout.addView(pieChart, param);

        View linechart = graphChart.getLineChart("Line Chart", xValues, yValues, Datalabel, mColors);

        linearLayout.addView(linechart, param);

        View StackbarChart = graphChart.getStackBarChart("Stack BarChart", new String[]{"Births", "Divorces", "Marriages"}, new String[]{"JAN", "FEB", "MAR", "APR "},
                new float[][]{{200, 400, 300}, {300, 900, 500}, {400, 800, 700}, {500, 900, 100}},
                new int[]{Color.GREEN, Color.BLUE, Color.RED});

        linearLayout.addView(StackbarChart, param);


        String[] xValuesLabel = {"June", "July", "Aug", "Sept"};
        String[] yValues = {"20", "80", "150", "60"};

        View view = graphChart.getBarChart(xValuesLabel, yValues);
        linearLayout.addView(view, param);


        //-----

        String[] xValues = {"Jan", "Feb", "Mar", "Apr"};
        String[] competitors = {"C-FIX", "Mezzo", "Dempi"};
        int[][] dataSet = {{200, 400, 300}, {300, 900, 500}, {400, 800, 700}, {500, 900, 100}};
        int[] colorArray = {Color.CYAN, Color.RED, Color.BLUE};

        View view3 = graphChart.getMultiBarChart(xValues, colorArray, competitors, dataSet);
        linearLayout.addView(view3,param);



        graphChart.getThreeTextViewforpie().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "We got a menu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
