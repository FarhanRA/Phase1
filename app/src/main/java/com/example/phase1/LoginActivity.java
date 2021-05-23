package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText nameInput;
    Button nextBtn;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        nameInput = (EditText) findViewById(R.id.username_input);
        nextBtn = (Button) findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = nameInput.getText().toString();
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                i.putExtra("USERNAME", username);
                startActivity(i);
                nameInput.setText("");
            }
        });
    }
}
