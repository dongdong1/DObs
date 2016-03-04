package com.example.dobs.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.dobs.R;
import com.example.dobs.Tasks.FetchSleepTask;
import com.github.mikephil.charting.charts.BarChart;

public class SleepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        Log.e(this.getClass().toString(), "TestActivity onCreate");
        TextView log = (TextView) findViewById(R.id.log);
        BarChart chart = (BarChart) findViewById(R.id.chart);
        // may raise bugs in case FinalizeOAuthTask is not completed
        new FetchSleepTask(log, chart).execute();
    }

    protected void onDestroy() {
        Log.e(this.getClass().toString(), "TestActivity onDestroy");
        super.onDestroy();
    }
}