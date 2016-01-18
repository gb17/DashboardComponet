package inc.gb.dashboardcomponet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.components.Legend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Shubham on 1/14/16.
 */
public class InputActivity extends Activity {
    Dialog dialog;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    LinearLayout linearLayout, main_layout;
    Button getchart;
    GraphChart graphChart;
    private int[] mColors = new int[]{
            Color.GREEN,
            Color.RED,
            Color.BLUE, Color.CYAN, Color.BLACK, Color.BLUE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);
        graphChart = new GraphChart(this);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        getchart = (Button) findViewById(R.id.getchart);
        getchart.setOnClickListener(listener);
        dialog = new Dialog(InputActivity.this);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.content_picker);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Display display = ((WindowManager) InputActivity.this
                .getSystemService(InputActivity.this.WINDOW_SERVICE))
                .getDefaultDisplay();
        dialog.setCancelable(false);
        int width = 0;
        int height = 0;
        if (isTablet(InputActivity.this)) {
            width = display.getWidth() / 3;
            height = (display.getHeight() * 2) / 5;
        } else {
            width = display.getWidth();
            height = display.getHeight();
        }

        dialog.getWindow().setLayout(width, height);
        dialog.show();
        Button submit = (Button) dialog.findViewById(R.id.submit);
        spinner1 = (Spinner) dialog.findViewById(R.id.spinner1);
        spinner2 = (Spinner) dialog.findViewById(R.id.spinner2);
        spinner3 = (Spinner) dialog.findViewById(R.id.spinner3);
        spinner4 = (Spinner) dialog.findViewById(R.id.spinner4);
        spinner5 = (Spinner) dialog.findViewById(R.id.spinner5);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                int countA = Integer.parseInt(spinner1.getSelectedItem().toString());
                if (countA != 0) {
                    for (int i = 0; i < countA; i++)
                        addChartInputDialog(linearLayout, countA, 1);
                }

                int countB = Integer.parseInt(spinner2.getSelectedItem().toString());
                if (countB != 0) {
                    for (int i = 0; i < countB; i++)
                        addChartInputDialog(linearLayout, countB, 2);
                }

                int countC = Integer.parseInt(spinner3.getSelectedItem().toString());
                if (countC != 0) {
                    for (int i = 0; i < countC; i++)
                        addChartInputDialog(linearLayout, countC, 3);
                }

                int countD = Integer.parseInt(spinner4.getSelectedItem().toString());
                if (countD != 0) {
                    for (int i = 0; i < countD; i++)
                        addChartInputDialog(linearLayout, countD, 4);
                }

                int countE = Integer.parseInt(spinner5.getSelectedItem().toString());
                if (countE != 0) {
                    for (int i = 0; i < countE; i++)
                        addChartInputDialog(linearLayout, countE, 5);
                }
            }
        });
    }

    private void addChartInputDialog(LinearLayout linearLayout, int count, final int index) {
        LayoutInflater inflater = (LayoutInflater) InputActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.pie_char_input, null);
        TextView header = (TextView) linear.findViewById(R.id.header);
        if (index == 1)
            header.setText("PieChart DataSet");
        else if (index == 2)
            header.setText("LineChart DataSet");
        else if (index == 3)
            header.setText("StackBarChart DataSet");
        else if (index == 4)
            header.setText("BarChart DataSet");
        else if (index == 5)
            header.setText("MultiBarChart DataSet");

        final LinearLayout linear2 = (LinearLayout) linear.findViewById(R.id.second);
        final Spinner addMore = (Spinner) linear.findViewById(R.id.addmore);

        addMore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int count = Integer.parseInt(addMore.getSelectedItem().toString());
                linear2.removeAllViews();
                if (count > 0) {
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    param.rightMargin = 20;
                    if (index == 1 || index == 4)
                        linear2.addView(createGrid(count), param);
                    else if (index == 2 || index == 3 || index == 5)
                        linear2.addView(createGridforline(count + 1, count + 1), param);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        linear.setId((100 * index) + count);
        linearLayout.addView(linear);
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int count = linearLayout.getChildCount();
            if (count > 0) {
                InputMethodManager manager = (InputMethodManager) InputActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                main_layout.removeAllViews();
                TableRow tr = null;
                int divide = 1;
                if (isTablet(InputActivity.this)) {
                    divide = 3;
                }
                for (int i = 0; i < count; i++) {
                    if (i % divide == 0) {
                        tr = new TableRow(InputActivity.this);
                        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                        main_layout.addView(tr);
                    }
                    addViewsBasedOnId(linearLayout.getChildAt(i).getId(), (LinearLayout) linearLayout.getChildAt(i), tr);
                }

            }
        }
    };


    private void addViewsBasedOnId(int id, LinearLayout linearLayout, TableRow tr) {
        if (isBetween(id, 100, 200)) {
            addPieChart(linearLayout, tr, 1);
        } else if (isBetween(id, 200, 300)) {
            addLineStackChart(linearLayout, tr, 2);
        } else if (isBetween(id, 300, 400)) {
            addLineStackChart(linearLayout, tr, 3);
        } else if (isBetween(id, 400, 500)) {
            addPieChart(linearLayout, tr, 4);
        } else if (isBetween(id, 500, 600)) {
            addLineStackChart(linearLayout, tr, 5);
        }
    }

    public void addLineStackChart(LinearLayout linearLayout, TableRow tablerow, int index) {
        TableLayout table = (TableLayout) ((LinearLayout) ((ScrollView) ((RelativeLayout) ((LinearLayout) linearLayout.getChildAt(3)).getChildAt(0)).getChildAt(0)).getChildAt(0)).getChildAt(0);
        ArrayList<String> arrXValues = new ArrayList<>();
        ArrayList<String> dataLabel = new ArrayList<>();
        ArrayList<String> arrYValues = new ArrayList<>();
        for (int m = 0; m < table.getChildCount(); m++) {
            TableRow tr = (TableRow) table.getChildAt(m);
            for (int i = 0; i < tr.getChildCount(); i++) {
                if (m == 0 && i == 0) {

                } else if (m == 0 && i != 0) {
                    arrXValues.add(((EditText) tr.getChildAt(i)).getText().toString());
                } else {
                    if (i == 0)
                        dataLabel.add(((EditText) tr.getChildAt(i)).getText().toString());
                    else {
                        arrYValues.add(((EditText) tr.getChildAt(i)).getText().toString());
                    }
                }
            }
        }

        String[] str1 = convertString(arrXValues);
        String[] str2 = convertString(dataLabel);
        String[][] str3 = new String[str2.length][str1.length];
        int count = 0;
        for (int a = 0; a < str2.length; a++) {
            for (int b = 0; b < str1.length; b++) {
                str3[a][b] = arrYValues.get(count);
                count++;
            }
        }
        View pieChart = null;
        if (index == 2)
            pieChart = graphChart.getLineChart("Line Chart", str1, str3, str2, mColors);
        else if (index == 3) {
            int[] colors = new int[str2.length];
            for (int i = 0; i < colors.length; i++)
                colors[i] = mColors[i];

            pieChart = graphChart.getStackBarChart("StackBar Chart", str1, str2, str3, colors);
        } else if (index == 5) {
            int[] colors = new int[str2.length];
            for (int i = 0; i < colors.length; i++)
                colors[i] = mColors[i];

            pieChart = graphChart.getMultiBarChart("MultiBar Chart", str1, colors, str2, str3);
        }

        pieChart.setBackgroundResource(R.drawable.bg_rounded);
        TableRow.LayoutParams param = null;
        if (isTablet(InputActivity.this))
            param = new TableRow.LayoutParams(340, 340);
        else
            param = new TableRow.LayoutParams(550, 550);

        param.rightMargin = 20;
        param.topMargin = 20;

        tablerow.addView(pieChart, param);
    }

    public void addPieChart(LinearLayout linearLayout, TableRow tablerow, int index) {
        TableLayout table = (TableLayout) ((LinearLayout) ((ScrollView) ((RelativeLayout) ((LinearLayout) linearLayout.getChildAt(3)).getChildAt(0)).getChildAt(0)).getChildAt(0)).getChildAt(0);
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        for (int m = 0; m < table.getChildCount(); m++) {
            TableRow tr = (TableRow) table.getChildAt(m);

            arr1.add(((EditText) tr.getChildAt(0)).getText().toString());
            arr2.add(((EditText) tr.getChildAt(1)).getText().toString());
        }
        String[] str1 = convertString(arr1);
        String[] str2 = convertString(arr2);
        View pieChart = null;
        if (index == 1)
            pieChart = graphChart.getPieChart("Dr Assist", str1, true, str2, true, false, Color.BLACK, Color.BLACK, Legend.LegendPosition.BELOW_CHART_CENTER);
        if (index == 4) {
            pieChart = graphChart.getBarChart(str1, str2);
        }

        pieChart.setBackgroundResource(R.drawable.bg_rounded);
        TableRow.LayoutParams param = null;
        if (isTablet(InputActivity.this))

            param = new TableRow.LayoutParams(340, 340);
        else
            param = new TableRow.LayoutParams(550, 550);

        param.rightMargin = 20;
        param.topMargin = 20;
        tablerow.addView(pieChart, param);
    }

    public String[] convertString(List<String> integers) {
        String[] ret = new String[integers.size()];
        Iterator<String> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next();
        }
        return ret;
    }

    public boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    private View createGrid(int count) {
        TableLayout tbl;
        EditText[] edittext;

        LayoutInflater inflater67 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v9 = inflater67.inflate(R.layout.dasborad_grid, null);
        TableRow.LayoutParams llp = null;

        TableRow tr;

        tbl = (TableLayout) v9.findViewById(R.id.tablesforinv);
        edittext = new EditText[5];
        llp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        llp.setMargins(1, 1, 0, 0);
        tbl.removeAllViews();
        llp = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 1f);
        llp.setMargins(1, 1, 1, 0);
        TableRow.LayoutParams llpweight2 = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 2f);
        llpweight2.setMargins(1, 1, 1, 0);
        for (int j = 0; j < count; j++) {
            tr = new TableRow(InputActivity.this);

            for (int i = 0; i < 2; i++) {
                edittext[i] = new EditText(InputActivity.this);
                edittext[i].setBackgroundColor(Color.WHITE);
                edittext[i].setTextSize(15);
                edittext[i].setSingleLine();
                edittext[i].setLayoutParams(llp);
                edittext[i].setTextColor(Color.BLACK);
                edittext[i].setGravity(Gravity.CENTER_HORIZONTAL);
                if (i == 0) {
                    edittext[i].setKeyListener(null);
                    edittext[i].setClickable(false);
                    edittext[i].setEnabled(false);
                    edittext[i].setText("param-" + j);
                } else {
                    edittext[i].setText("");
                    edittext[i].setInputType(InputType.TYPE_CLASS_NUMBER);
                    edittext[i].setLayoutParams(llp);
                    InputFilter[] filterArray = new InputFilter[1];
                    filterArray[0] = new InputFilter.LengthFilter(2);
                    edittext[i].setFilters(filterArray);
                }
                tr.addView(edittext[i]);

            }
            tbl.addView(tr);
        }
        return v9;
    }

    String[] rowarray = {"SR.NO", "JAN", "FEB", "MAR", "APR", "MAY", "JUNE"};
    String[] colarray = {"", "Parm-1", "Parm-2", "Parm-3", "Parm-4", "Parm-5", "Parm-6", "Parm-7"};

    private View createGridforline(int row, int col) {
        TableLayout tbl;
        EditText[] edittext;
        LayoutInflater inflater67 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v9 = inflater67.inflate(R.layout.dasborad_grid, null);
        TableRow.LayoutParams llp = null;
        TableRow tr;
        tbl = (TableLayout) v9.findViewById(R.id.tablesforinv);
        edittext = new EditText[col];
        llp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        llp.setMargins(1, 1, 0, 0);
        tbl.removeAllViews();
        llp = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 1f);
        llp.setMargins(1, 1, 1, 0);
        TableRow.LayoutParams llpweight2 = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 2f);
        llpweight2.setMargins(1, 1, 1, 0);
        for (int j = 0; j < row; j++) {
            tr = new TableRow(InputActivity.this);

            for (int i = 0; i < col; i++) {
                edittext[i] = new EditText(InputActivity.this);
                edittext[i].setBackgroundColor(Color.WHITE);
                edittext[i].setTextSize(15);
                edittext[i].setSingleLine();
                edittext[i].setLayoutParams(llp);
                edittext[i].setTextColor(Color.BLACK);
                edittext[i].setGravity(Gravity.CENTER_HORIZONTAL);

                if (j == 0) {
                    edittext[i].setText(rowarray[i]);
                }
                if (i == 0 && j != 0) {
                    edittext[i].setKeyListener(null);
                    edittext[i].setClickable(false);
                    edittext[i].setEnabled(false);
                    edittext[i].setText(colarray[j]);
                } else {
                    edittext[i].setInputType(InputType.TYPE_CLASS_NUMBER);
                    edittext[i].setLayoutParams(llp);
                    InputFilter[] filterArray = new InputFilter[1];
                    filterArray[0] = new InputFilter.LengthFilter(2);
                    edittext[i].setFilters(filterArray);
                }
                tr.addView(edittext[i]);
            }
            tbl.addView(tr);
        }
        return v9;
    }

    public boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
