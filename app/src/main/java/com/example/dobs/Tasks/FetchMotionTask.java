package com.example.dobs.Tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.dobs.Activities.MainActivity;
import com.example.dobs.Classes.SleepLog;
import com.github.mikephil.charting.charts.BarChart;
import com.temboo.Library.Fitbit.Sleep.GetSleep;
import com.temboo.core.TembooSession;

/**
 * Created by Dade on 03/03/2016.
 */
public class FetchMotionTask extends AsyncTask<Void, Void, String> {

    private TextView textView;
    private BarChart chart;
    private SleepLog sleepLog;

    public FetchMotionTask(TextView textView, BarChart chart) {
        this.textView = textView;
        this.chart = chart;
    }

    ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        if (!isCancelled()) {
            dialog = new ProgressDialog(chart.getContext());
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(Void... arg0) {

        try {
            // Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
            TembooSession session = new TembooSession("shengdade", "myFirstApp", "ArtiavNqi3yliseQZEAMX2QLTOGanVqF");
            //-----------------------------------------------------------------------------------------------------------------------
            GetSleep getSleepChoreo = new GetSleep(session);
            Log.i(this.getClass().toString(), "getSleepChoreo created");
            // Get an InputSet object for the choreo
            GetSleep.GetSleepInputSet getSleepInputs = getSleepChoreo.newInputSet();
            Log.i(this.getClass().toString(), "getSleepInputs created");
            // Set inputs
            getSleepInputs.set_AccessToken(MainActivity.patient.accessToken);
            getSleepInputs.set_Date(MainActivity.datePicked);
            Log.i(this.getClass().toString(), "getSleepInputs set ready");
            // Execute Choreo
            GetSleep.GetSleepResultSet getSleepResults = getSleepChoreo.execute(getSleepInputs);
            Log.i(this.getClass().toString(), "getSleepResults created");
            //-----------------------------------------------------------------------------------------------------------------------
            String resultResponse = getSleepResults.get_Response();
            sleepLog = new SleepLog(resultResponse);
            return (sleepLog.getSleepSummary());
        } catch (Exception e) {
            // if an exception occurred, log it
            Log.e(this.getClass().toString(), e.getMessage());
        }
        return null;
    }

    protected void onPostExecute(String sleepSummary) {
        try {
            dialog.dismiss();
            new DrawChartTask(chart, sleepLog.getLabels(), sleepLog.getEntries()).execute();
            textView.setText(sleepSummary);
            Log.e(this.getClass().toString(), "Fetch Text Success!");
        } catch (Exception e) {
            // if an exception occurred, show an error message
            Log.i(this.getClass().toString(), e.getMessage());
        }
    }
}