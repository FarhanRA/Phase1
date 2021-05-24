package com.example.phase1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<EventModel> eventlist;

    public EventRecyclerViewAdapter(ArrayList<EventModel> Data) {
        eventlist = Data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtEventName;
        TextView txtEventDetails;
        TextView  txtDate;
        ImageView imgEvent;
        public MyViewHolder(View view) {
            super(view);
           txtEventName = (TextView) view.findViewById(R.id.event_txt);
           txtEventDetails = (TextView) view.findViewById(R.id.detail_txt);
           txtDate = (TextView) view.findViewById(R.id.date_txt);
           imgEvent = (ImageView) view.findViewById(R.id.img);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtEventName.setText(eventlist.get(position).getName());
        holder.txtEventDetails.setText(eventlist.get(position).getDetail());
        holder.txtDate.setText(eventlist.get(position).getDate());
        holder.imgEvent.setImageResource(eventlist.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return eventlist.size();
    }
}
