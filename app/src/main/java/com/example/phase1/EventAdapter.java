package com.example.phase1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<EventModel>{

    public EventAdapter(ArrayList<EventModel> data, Context context) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventModel eventModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
        }
        TextView txtEventName = (TextView) convertView.findViewById(R.id.event_txt);
        TextView txtEventDetails = (TextView) convertView.findViewById(R.id.detail_txt);
        TextView  txtDate = (TextView) convertView.findViewById(R.id.date_txt);
        ImageView imgEvent = (ImageView) convertView.findViewById(R.id.img);

        txtEventName.setText(eventModel.getName());
        txtEventDetails.setText(eventModel.getDetail());
        txtDate.setText(eventModel.getDate());
        imgEvent.setImageResource(eventModel.getImage());
        // Return the completed view to render on screen
        return convertView;
    }
}