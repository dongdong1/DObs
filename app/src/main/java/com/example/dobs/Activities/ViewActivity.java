package com.example.dobs.Activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.dobs.Adapters.BehaviorView;
import com.example.dobs.Classes.BehaviorRecord;
import com.example.dobs.R;

import java.util.List;

/**
 * Created by dade on 15/02/16.
 */
public class ViewActivity extends ListActivity {
    List<BehaviorRecord> allBehaviors;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetRecordsTask().execute();//Go fetch all records
    }

    private class GetRecordsTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... arguments) {
            allBehaviors = MainActivity.db.getAllBehaviorRecords();
            return null;
        }

        protected void onPostExecute(Void result) {
            BehaviorView adp = new BehaviorView(context, R.layout.activity_view_row, allBehaviors);
            setListAdapter(adp);
        }
    }
}
