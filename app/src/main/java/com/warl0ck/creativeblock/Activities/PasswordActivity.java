package com.warl0ck.creativeblock.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.warl0ck.creativeblock.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PasswordActivity extends AppCompatActivity {

    TextView forgotPwd;
    Button btnLogin;
    EditText Username, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        forgotPwd = findViewById(R.id.forgotPwd);
        Username = findViewById(R.id.edUsername);
        Password = findViewById(R.id.edPassword);

        final String username = Username.getText().toString();
        final String password = Password.getText().toString();

        //final int isLedgit = check();

        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordActivity.this, ProfileActivity.class);
                startActivity(intent);
                intent.putExtra("PasswordActivity", "this is intentionally left blank");
            }
        });

        //ArrayList<PwdHandler> mArray = new ArrayList<>();
        final String fileName =("password.ser");
        try {
            FileOutputStream fos = this.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);


            //mArray.add(new PwdHandler("bashir",new Random(1000)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(PasswordActivity.this,NoteListActivity.class);
                    startActivity(intent);


            }
        });
    }

    /*private int check() {
        int result = getLocalClassName().codePointBefore(0);
        String Test = "this is for the test method";

        Toast.makeText(getApplicationContext(),Test,Toast.LENGTH_SHORT).show();
        return result;
    }*/
}
