package com.example.dobs.Tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.dobs.Activities.MainActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.temboo.Library.Fitbit.Statistics.GetIntradayTimeSeries;
import com.temboo.Library.Fitbit.Statistics.GetIntradayTimeSeries.GetIntradayTimeSeriesInputSet;
import com.temboo.Library.Fitbit.Statistics.GetIntradayTimeSeries.GetIntradayTimeSeriesResultSet;
import com.temboo.core.TembooSession;

/**
 * Created by Dade on 03/03/2016.
 */
public class FetchMotionTask extends AsyncTask<Void, Void, String> {

    private TextView textView;
    private BarChart chart;
    //private SleepLog sleepLog;

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
            GetIntradayTimeSeries getIntradayTimeSeriesChoreo = new GetIntradayTimeSeries(session);
            Log.i(this.getClass().toString(), "getIntradayTimeSeriesChoreo created");
            // Get an InputSet object for the choreo
            GetIntradayTimeSeriesInputSet getIntradayTimeSeriesInputs = getIntradayTimeSeriesChoreo.newInputSet();
            Log.i(this.getClass().toString(), "getIntradayTimeSeriesInputs created");
            // Set inputs
            getIntradayTimeSeriesInputs.set_StartDate(MainActivity.datePicked);
            Log.e(this.getClass().toString(), "MainActivity.datePicked: " + MainActivity.datePicked);
            getIntradayTimeSeriesInputs.set_ResourcePath("activities/calories");
            getIntradayTimeSeriesInputs.set_DetailLevel("15min");
            getIntradayTimeSeriesInputs.set_AccessToken(MainActivity.patient.accessToken);
            getIntradayTimeSeriesInputs.set_EndDate("1d");
            Log.i(this.getClass().toString(), "getIntradayTimeSeriesInputs set ready");
            // Execute Choreo
            GetIntradayTimeSeriesResultSet getIntradayTimeSeriesResults = getIntradayTimeSeriesChoreo.execute(getIntradayTimeSeriesInputs);
            Log.i(this.getClass().toString(), "getIntradayTimeSeriesResults created");
            //-----------------------------------------------------------------------------------------------------------------------
            String resultResponse = getIntradayTimeSeriesResults.get_Response();
//            sleepLog = new SleepLog(resultResponse);
//            return (sleepLog.getSleepSummary());
            return (resultResponse);
        } catch (Exception e) {
            // if an exception occurred, log it
            Log.e(this.getClass().toString(), e.getMessage());
        }
        return null;
    }

    protected void onPostExecute(String resultResponse) {
        try {
            dialog.dismiss();
//            new DrawSleepTask(chart, sleepLog.getLabels(), sleepLog.getEntries()).execute();
            textView.setText(resultResponse);
            Log.e(this.getClass().toString(), "Fetch Text Success!");
        } catch (Exception e) {
            // if an exception occurred, show an error message
            Log.i(this.getClass().toString(), e.getMessage());
        }
    }
}
