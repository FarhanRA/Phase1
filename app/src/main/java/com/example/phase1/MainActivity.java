package com.example.phase1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView usernameTxt;
    Button chooseEventBtn;
    Button chooseGuestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        usernameTxt = (TextView) findViewById(R.id.main_username);
        chooseEventBtn = (Button) findViewById(R.id.choose_event_btn);
        chooseGuestBtn = (Button) findViewById(R.id.choose_guest_btn);

        String s_username = getIntent().getStringExtra("USERNAME");
        usernameTxt.setText(s_username);

        chooseEventBtn.setOnClickListener(onClickChooseEventBtn);
        chooseGuestBtn.setOnClickListener(onClickChooseGuestBtn);
    }

    private View.OnClickListener onClickChooseEventBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this,EventListActivity.class);
            startActivityForResult(i,1);
        }
    };

    private View.OnClickListener onClickChooseGuestBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this,GuestGridActivity.class);
            startActivityForResult(i,2);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("EVENT");
                chooseEventBtn.setText(result);
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        if (requestCode == 2) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("GUEST");
                chooseGuestBtn.setText(result);
                int resultId = data.getIntExtra("ID",0);
                String phoneType = "";
                if(resultId % 2 == 0 && resultId % 3 == 0 ){
                    phoneType = "iOS";
                }else if(resultId % 2 == 0){
                    phoneType = "BlackBerry";
                }else if(resultId % 3 == 0 ){
                    phoneType = "Android";
                }else {
                    phoneType = "feature phones";

                }

                Toast.makeText(getApplicationContext(),
                        phoneType,
                        Toast.LENGTH_LONG)
                        .show();
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
