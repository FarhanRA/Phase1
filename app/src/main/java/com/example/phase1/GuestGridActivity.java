package com.example.phase1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GuestGridActivity extends AppCompatActivity {

    private String TAG = GuestGridActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    GuestGridAdapter adapter;
    ImageView backBtn;
    GridView guestGrid;

    private SwipeRefreshLayout swipeContainer;

//    private static String url ="https://reqres.in/api/users";

    private static String url ="https://reqres.in/api/users?page=1&per_page=8";



    ArrayList<GuestModel> guestList;

    int mPage;
    int mPerPage;
    int mTotal;
    int mTotalPages;

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
                Boolean idPrimeNumb = isIdPrimeNumber(guestModel.getId());
                returnIntent.putExtra("GUEST",nameGuest);
                returnIntent.putExtra("ID",guestModel.getId());
                returnIntent.putExtra("PRIME",idPrimeNumb);
                setResult(RESULT_OK,returnIntent);
                finish();

            }
        });
        guestGrid.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if(mPerPage + visibleItemCount >= mTotal){
//                    // End has been reached
//                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                {
                    if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                        // End has been reached
                        Log.e(TAG, "End Scroll");
                        Log.e(TAG, "firstVisibleItem"+firstVisibleItem);
                        Log.e(TAG, "visibleItemCount"+visibleItemCount);
                        Log.e(TAG, "totalItemCount"+totalItemCount);
                        if(firstVisibleItem + visibleItemCount <= mTotal){
//                            String sPerpage = String.valueOf(mPerPage + 1);
//                            url ="https://reqres.in/api/users?page=1&per_page="+sPerpage;
                        }
                        String sPerpage = String.valueOf(mPerPage + 1);
                        String sPage = String.valueOf(mPage + 1);
//                        url ="https://reqres.in/api/users?page="+sPage+"&per_page="+sPerpage;
//                        new GetGuest().execute();

                    }
                }
            }
        });
        



//        GuestGridAdapter adapter = new GuestGridAdapter(this, guestList);
//        guestGrid.setAdapter(adapter);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeContainer.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });
    }

    public boolean isIdPrimeNumber(int number) {

        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
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

                    mPage = jsonObj.getInt("page");
                    mPerPage = jsonObj.getInt("per_page");
                    mTotal = jsonObj.getInt("total");
                    mTotalPages  = jsonObj.getInt("total_pages");
                    Log.i(TAG, "page: " + mPage);
                    Log.i(TAG, "perpage: " + mPerPage);
                    Log.i(TAG, "mTotal: " + mTotal);
                    Log.i(TAG, "mTotalPages: " + mTotalPages);

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
            adapter = new GuestGridAdapter(GuestGridActivity.this, guestList);
            guestGrid.setAdapter(adapter);

            //cache
            ((ThisApp)getApplication()).setGuestModels(guestList);

            Log.i(TAG, "guestList: " + guestList.toString());
        }

    }

    public void fetchTimelineAsync(int page) {
        guestList.clear();
        adapter.notifyDataSetChanged();
        new GetGuest().execute();
    }
}
