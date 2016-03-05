package com.example.dobs.Adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dobs.Classes.Behavior;
import com.example.dobs.R;

import java.util.List;

/**
 * Created by dade on 15/02/16.
 */
public class BehaviorSelect extends ArrayAdapter<Behavior> {
    private static final String TAG = "BehaviorSelectAdapter";

    Context context;
    int layoutResourceId;
    List<Behavior> data = null;

    public BehaviorSelect(Context context, int layoutResourceId, List<Behavior> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BehaviorHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new BehaviorHolder();
            holder.txtBehavior = (TextView) row.findViewById(R.id.labelBehavior);
            row.setTag(holder);
        } else {
            holder = (BehaviorHolder) row.getTag();
        }

        Behavior behavior = data.get(position);
        holder.txtBehavior.setText(behavior.name);
        holder.txtBehavior.setTextColor(getContext().getResources().getColor(behavior.color));

        //row.setBackgroundColor(getContext().getResources().getColor(behavior.color));

        return (row);
    }

    static class BehaviorHolder {
        TextView txtBehavior;
    }
}