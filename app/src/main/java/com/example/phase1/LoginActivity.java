package com.example.phase1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText nameInput;
    EditText checkPalindromeInput;
    Button nextBtn;
    Button checkBtn;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        nameInput = (EditText) findViewById(R.id.username_input);
        checkPalindromeInput = (EditText) findViewById(R.id.check_palindrome_input);

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

        checkBtn = (Button) findViewById(R.id.check_btn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String variable = checkPalindromeInput.getText().toString();
                variable = variable.replaceAll("\\s", "") ;//.equals(new StringBuilder(variable.replaceAll("\\s", "")).reverse().toString())+"";

                StringBuffer rev = new StringBuffer(variable).reverse();
                String strRev = rev.toString();

                Boolean isPalindrome;

                if(variable.equalsIgnoreCase(strRev)){
                    isPalindrome = true;
                    Toast.makeText(getApplicationContext(),
                            "True",
                            Toast.LENGTH_LONG)
                            .show();
                }else {
                    isPalindrome = false;
                    Toast.makeText(getApplicationContext(),
                            "False",
                            Toast.LENGTH_LONG)
                            .show();
                }

                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                alertDialog.setTitle("");
                alertDialog.setMessage("isPalindrome : "+isPalindrome.toString());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }
}
