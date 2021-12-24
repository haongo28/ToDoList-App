package com.example.todosapp.Fragment.Main;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//import anychart
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;

//end import anychart
import com.example.todosapp.Dialog.ExampleDialog;
import com.example.todosapp.R;
import com.example.todosapp.Service.DBToDoManager;
import com.example.todosapp.Service.DBUserManager;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    View view;
    ImageView imageView;

    TextView username, email, taskDone, taskPending;
    DBUserManager dbUserManager;
    DBToDoManager dbToDoManager;

    String userName;
    String mail;
    String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        dbUserManager = new DBUserManager(requireContext());
        dbToDoManager = new DBToDoManager(requireContext());
        dbUserManager.open();
        dbToDoManager.open();

        bindView();
        createUser();
        viewUser();
        createToDo();
        viewToDo();

        imageView =  view.findViewById(R.id.setting_button);
        imageView.setOnClickListener(view ->  openDialog());
        return view;

    }

    public void openDialog() {
        ExampleDialog dialog = new ExampleDialog(userName, mail, password);
        Log.d("check","create dialog");
        dialog.show(getActivity().getSupportFragmentManager(), "example dialog");
        Log.d("check","create dialog");
    }

    public void bindView(){
        username = view.findViewById(R.id.profile_fullname);
        email = view.findViewById(R.id.profile_email);
        taskDone = view.findViewById(R.id.task_done_number);
        taskPending = view.findViewById(R.id.task_pendding_number);
    }

    //TODO: nao nhap data thuc thi xoa ham nay di luon
    private void createUser(){
        dbUserManager.insertUser("1", "memine", "memine@gm.com", "1234");
        dbUserManager.insertUser("2", "meminee", "meminee@gm.com", "1234");
        dbUserManager.insertUser("3", "memineeee", "memineee@gm.com", "1234");
    }

    private void viewUser(){
        Cursor cursor = dbUserManager.getUser();
        if (cursor.getCount() == 0){
            System.out.println("no user available");
            return;
        }
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("id")).equals("2")){
                userName = cursor.getString(cursor.getColumnIndex("username"));
                mail = cursor.getString(cursor.getColumnIndex("email"));
                password = cursor.getString(cursor.getColumnIndex("password"));

                username.setText(userName);
                email.setText(mail);
                System.out.println(username.getText());
            }
        }
    }

    //TODO: nao nhap data thuc thi xoa ham nay di luon
    private void createToDo() {
        dbToDoManager.insertToDo("2", "task 1", "true", "Mon");
        dbToDoManager.insertToDo("2", "task 2", "false", "Tue");
        dbToDoManager.insertToDo("2", "task 3", "true", "Mon");
        dbToDoManager.insertToDo("3", "task 1", "true", "Mon");
        dbToDoManager.insertToDo("2", "task 4", "false", "Mon");
        dbToDoManager.insertToDo("3", "task 2", "true", "Mon");
        dbToDoManager.insertToDo("2", "task 5", "true", "Mon");
        dbToDoManager.insertToDo("2", "task 6", "true", "Mon");
        dbToDoManager.insertToDo("1", "task 1", "true", "Mon");
        dbToDoManager.insertToDo("2", "task 7", "false", "Sat");
    }

    private void viewToDo() {
        Cursor cursor = dbToDoManager.getToDo();
        int done , pending, mon, tue, wed, fri, thu, sat, sun;
        done = pending = mon = thu = tue = wed = fri = sat = sun = 0;
        List<DataEntry> data = new ArrayList<>();
        if (cursor.getCount() == 0){
            System.out.println("no user available");
            return ;
        }
        while (cursor.moveToNext()){
            //TODO: sau tu chinh lai gtri equal nho
            if (cursor.getString(cursor.getColumnIndex("user_id")).equals("2")){
                if (cursor.getString(cursor.getColumnIndex("complete")).equals("true")){
                    done++;
                    switch (cursor.getString(cursor.getColumnIndex("day"))){
                        case "Mon":
                            mon++;
                            break;
                        case "Tue":
                            tue++;
                            break;
                        case "Wed":
                            wed++;
                            break;
                        case "Fri":
                            fri++;
                            break;
                        case "Thu":
                            thu++;
                            break;
                        case "Sat":
                            sat++;
                            break;
                        case "Sun":
                            sun++;
                            break;
                    }
                }else {
                    pending++;
                }
            }
        }
        taskDone.setText(String.valueOf(done));
        taskPending.setText(String.valueOf(pending));
        data.add(new ValueDataEntry("Mo", mon));
        data.add(new ValueDataEntry("Tu", tue));
        data.add(new ValueDataEntry("We", wed));
        data.add(new ValueDataEntry("Th", thu));
        data.add(new ValueDataEntry("Fr", fri));
        data.add(new ValueDataEntry("Sa", sat));
        data.add(new ValueDataEntry("Su", sun));

        displayChart(data);
    }

    private void displayChart(List<DataEntry> data){
        Cartesian cartesian = AnyChart.column();
        //anychart
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(view.findViewById(R.id.progress_bar));

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
    }
}