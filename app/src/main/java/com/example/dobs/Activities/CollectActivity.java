package com.example.dobs.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.dobs.R;

public class CollectActivity extends AppCompatActivity {
    public final static String tagBehavior = "timeBehavior";
    public final static String tagEvent = "timeEvent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        final TimePicker timeBehavior = (TimePicker) findViewById(R.id.timeBehavior);
        final TimePicker timeEvent = (TimePicker) findViewById(R.id.timeEvent);
        ImageButton imageBehavior = (ImageButton) findViewById(R.id.imageBehavior);
        ImageButton imageEvent = (ImageButton) findViewById(R.id.imageEvent);

        timeBehavior.setAddStatesFromChildren(true);
        timeEvent.setAddStatesFromChildren(true);

        imageBehavior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent behaviorRecord = new Intent(v.getContext(), BehaviorActivity.class);
                timeBehavior.clearFocus();
                int iHour = timeBehavior.getCurrentHour();
                int iMinute = timeBehavior.getCurrentMinute();
                behaviorRecord.putExtra(tagBehavior, new int[]{iHour, iMinute});
                startActivity(behaviorRecord);
            }
        });

        imageEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventRecord = new Intent(v.getContext(), EventActivity.class);
                timeEvent.clearFocus();
                int iHour = timeEvent.getCurrentHour();
                int iMinute = timeEvent.getCurrentMinute();
                eventRecord.putExtra(tagEvent, new int[]{iHour, iMinute});
                startActivity(eventRecord);
            }
        });
    }
}
