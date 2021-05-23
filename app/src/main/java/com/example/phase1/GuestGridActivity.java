package com.example.phase1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GuestGridActivity extends AppCompatActivity {

    private String TAG = GuestGridActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    ImageView backBtn;
    GridView guestGrid;

    private static String url ="https://reqres.in/api/users";



    ArrayList<GuestModel> guestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_guest);

        backBtn = (ImageView) findViewById(R.id.back_eventguest_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guestGrid = (GridView) findViewById(R.id.guest_grid);

        guestList = new ArrayList<>();
        Log.i(TAG, "Start get guest: ");
        new GetGuest().execute();

        guestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GuestModel guestModel = (GuestModel)parent.getItemAtPosition(position);
                Intent returnIntent = new Intent();
                String nameGuest = guestModel.getFirst_name()+" "+guestModel.getLast_name();
                returnIntent.putExtra("GUEST",nameGuest);
                returnIntent.putExtra("ID",guestModel.getId());
                setResult(RESULT_OK,returnIntent);
                finish();

            }
        });

//        GuestGridAdapter adapter = new GuestGridAdapter(this, guestList);
//        guestGrid.setAdapter(adapter);
    }

    ///////
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetGuest extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(GuestGridActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);

                        int id = c.getInt("id");
                        String first_name = c.getString("first_name");
                        String last_name = c.getString("last_name");
                        String email = c.getString("email");
                        String avatar = c.getString("avatar");
                        GuestModel guest = new GuestModel(id,email,first_name,last_name,avatar);



                        Log.i(TAG, "guest id: " + guest.getId());
                        Log.i(TAG, "guest first_name: " + guest.getFirst_name());



                        // adding contact to contact list
                        guestList.add(guest);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            GuestGridAdapter adapter = new GuestGridAdapter(GuestGridActivity.this, guestList);
            guestGrid.setAdapter(adapter);

            Log.i(TAG, "guestList: " + guestList.toString());
        }

    }
}
