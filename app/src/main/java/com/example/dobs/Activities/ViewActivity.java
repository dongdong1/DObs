package com.example.dobs.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.dobs.R;
import com.example.dobs.Tasks.InitializeOAuthTask;

public class ViewActivity extends AppCompatActivity {
    private AppCompatActivity context;
    private DatePicker dataPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        this.context = this;
        dataPicker = (DatePicker) findViewById(R.id.datePicker);

        Button btnBehavior = (Button) findViewById(R.id.btnBehavior);
        btnBehavior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewBehaviors.class);
                intent.putExtra("DatePicked", getPickedDate());
                startActivity(intent);
            }
        });

        Button btnSleep = (Button) findViewById(R.id.btnSleep);
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.datePicked = getPickedDateString();
            }
        });
    }

    private int[] getPickedDate() {
        return (new int[]{dataPicker.getYear(), dataPicker.getMonth() + 1, dataPicker.getDayOfMonth()});
    }

    private String getPickedDateString() {
        return (String.valueOf(dataPicker.getYear()) + "-" + String.valueOf(dataPicker.getMonth() + 1) + "-" + String.valueOf(dataPicker.getDayOfMonth()));
    }
}