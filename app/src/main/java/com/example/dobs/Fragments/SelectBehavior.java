package com.example.dobs.Fragments;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dobs.Activities.BehaviorActivity;
import com.example.dobs.Activities.CollectActivity;
import com.example.dobs.Activities.MainActivity;
import com.example.dobs.Adapters.BehaviorSelect;
import com.example.dobs.Classes.Patient;
import com.example.dobs.R;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by dade on 16/02/16.
 */
public class SelectBehavior extends ListFragment {
    private static final String TAG = "SelectBehavior";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        MainActivity.patient = readPatient();
        getActivity().setTitle("ID # " + MainActivity.patient.ID);
        BehaviorSelect adp = new BehaviorSelect(getActivity(), R.layout.activity_choose_row, MainActivity.patient.trackingBehaviors);
        setListAdapter(adp);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private Patient readPatient() {
        Patient patient = null;
        try {
            FileInputStream fis = getActivity().openFileInput(MainActivity.patientFilename);
            ObjectInputStream is = new ObjectInputStream(fis);
            patient = (Patient) is.readObject();
            is.close();
            fis.close();
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return patient;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_okay:
                storeTime();
                storeBehavior();
                break;
            case R.id.menu_add:
                //addToBackStack is very important, used to remember how ChooseBehaviors is loaded
                getFragmentManager().beginTransaction().replace(R.id.fragBehavior, new ChooseBehaviors()).addToBackStack(null).commit();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void storeTime() {
        int[] hourMinutes = getActivity().getIntent().getIntArrayExtra(CollectActivity.tagBehavior);
        Calendar currentTime = GregorianCalendar.getInstance();
        currentTime.set(Calendar.HOUR_OF_DAY, hourMinutes[0]);
        currentTime.set(Calendar.MINUTE, hourMinutes[1]);
        currentTime.set(Calendar.SECOND, 0);
        BehaviorActivity.behaviorRecord.time = currentTime;
    }

    private void storeBehavior() {
        int position = getListView().getCheckedItemPosition();
        if (position < 0) {
            Toast.makeText(getActivity(), "You haven't select a behavior", Toast.LENGTH_SHORT).show();
        } else {
            BehaviorActivity.behaviorRecord.behavior = MainActivity.patient.trackingBehaviors.get(position);
            getFragmentManager().beginTransaction().replace(R.id.fragBehavior, new SelectContext()).addToBackStack(null).commit();
        }
    }

    public View row;

    public void onListItemClick(ListView parent, View v, int position, long id) {
        if (row != null) {
            row.setBackgroundColor(Color.TRANSPARENT);
        }
        row = v;
        v.setBackgroundColor(Color.DKGRAY);
    }
}

