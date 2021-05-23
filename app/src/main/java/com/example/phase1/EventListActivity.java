package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    ArrayList<EventModel> dataEventModels;
    ListView listView;
    private static EventAdapter adapter;

    Toolbar toolbar;
    ImageView backBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView =(ListView)findViewById(R.id.list);
        backBtn = (ImageView) findViewById(R.id.back_eventlist_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataEventModels = new ArrayList<>();
        dataEventModels.add(new EventModel(1,"Google I/O","Virtual, Online","May 18, 2021",R.drawable.event_image));
        dataEventModels.add(new EventModel(2,"Alteryx Global Inspire 2021","Online, Virtual","May 21, 2021",R.drawable.event_img2));
        dataEventModels.add(new EventModel(3,"Integrate Remote 2021","Virtual, Online","Jun 03, 2021",R.drawable.event_img3));
        dataEventModels.add(new EventModel(4,"VivaTechnology","Paris, France","Jun 19, 2021",R.drawable.event_image4));

        adapter= new EventAdapter(dataEventModels,getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventModel dataModel = (EventModel)parent.getItemAtPosition(position);
//                Toast.makeText(EventListActivity.this, "Choosed Event " +dataModel.getName(), Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("EVENT",dataModel.getName());
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
    }


}
