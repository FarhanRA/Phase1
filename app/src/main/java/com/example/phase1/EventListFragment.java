package com.example.phase1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;



import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class EventListFragment extends Fragment {

    ArrayList<EventModel> dataEventModels;
    ListView listView;
    private static EventAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.list_event, container, false);

        listView =(ListView)view.findViewById(R.id.list);
        dataEventModels = new ArrayList<>();
        dataEventModels.add(new EventModel(1,"Google I/O","Virtual, Online","May 18, 2021",R.drawable.event_image,-6.92982190125774, 107.60197482916824));
        dataEventModels.add(new EventModel(2,"Alteryx Global Inspire 2021","Online, Virtual","May 21, 2021",R.drawable.event_img2,-6.929982190125774, 107.60237482916824));
        dataEventModels.add(new EventModel(3,"Integrate Remote 2021","Virtual, Online","Jun 03, 2021",R.drawable.event_img3,-6.929982190125774, 107.60137482916824));
        dataEventModels.add(new EventModel(4,"VivaTechnology","Paris, France","Jun 19, 2021",R.drawable.event_image4,-6.929432190125774, 107.60196482916824));

        adapter= new EventAdapter(dataEventModels,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventModel dataModel = (EventModel)parent.getItemAtPosition(position);
//                Toast.makeText(EventListActivity.this, "Choosed Event " +dataModel.getName(), Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("EVENT",dataModel.getName());
                getActivity().setResult(RESULT_OK,returnIntent);
                getActivity().finish();
            }
        });

        return view;
    }
}