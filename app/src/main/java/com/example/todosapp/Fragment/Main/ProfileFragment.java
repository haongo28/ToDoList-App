package com.example.todosapp.Fragment.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//import anychart
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.core.ui.table.Row;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

//end import anychart
import com.example.todosapp.Dialog.ExampleDialog;
import com.example.todosapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    View view;
    ImageView imageView;
    //dialog
    //private ExampleDialogListener listener;

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        //anychart
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(view.findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Mo", 10));
        data.add(new ValueDataEntry("Tu", 15));
        data.add(new ValueDataEntry("We", 20));
        data.add(new ValueDataEntry("Th", 10));
        data.add(new ValueDataEntry("Fr", 30));
        data.add(new ValueDataEntry("Sa", 30));
        data.add(new ValueDataEntry("Su", 40));

        Column column = cartesian.column(data);
        column.color("#0c3a2d");
        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Hoàn thành nhiệm vụ hằng ngày");

        cartesian.yAxis(true);

        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Nhiệm vụ");

        anyChartView.setChart(cartesian);
        //end of anychart

        imageView =  view.findViewById(R.id.setting_button);
        imageView.setOnClickListener(view ->  openDialog());
        return view;

    }
    public void openDialog() {
        ExampleDialog dialog = new ExampleDialog();
        Log.d("check","create dialog");
        dialog.show(getActivity().getSupportFragmentManager(), "example dialog");
        Log.d("check","create dialog");
    }

    //@Override
    // public void applyTexts(String username, String password) {
    //textViewUsername.setText(username);
    //textViewPassword.setText(password);
    // }

}