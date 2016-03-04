package com.example.dobs.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dobs.R;
import com.example.dobs.Tasks.InitializeOAuthTask;

public class ViewActivity extends AppCompatActivity {
    private AppCompatActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        this.context = this;

        Button btnBehavior = (Button) findViewById(R.id.btnBehavior);
        btnBehavior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ViewBehaviors.class));
            }
        });

        Button btnSleep = (Button) findViewById(R.id.btnSleep);
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InitializeOAuthTask(context).execute();
            }
        });
    }
}
