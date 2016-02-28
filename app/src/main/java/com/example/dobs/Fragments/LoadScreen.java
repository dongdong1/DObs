package com.example.dobs.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dobs.R;

/**
 * Created by dade on 15/02/16.
 */
public class LoadScreen extends Fragment {
    private static final String TAG = "LoadScreen";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View resultView = inflater.inflate(R.layout.frag_load_screen, container, false);
        Button start = (Button) resultView.findViewById(R.id.Start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainFragment(v);
            }
        });
        return (resultView);
    }

    private void startMainFragment(View v) {
        getFragmentManager().beginTransaction().replace(R.id.fragMain, new MainFragment()).commit();
    }
}
