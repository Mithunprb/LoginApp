package com.example.android.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Button submit;
    EditText name;
    TextView text;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.form_name);
        submit = findViewById(R.id.btn_login);
        text = findViewById(R.id.text);

        DB = new DBHelper(this);

        Bundle extras = getIntent().getExtras();

        String value = extras.getString("key");
            //The key argument here must match that used in the other activity


        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(HomeActivity.this, form.class));
            Toast.makeText(HomeActivity.this, "First Run", Toast.LENGTH_LONG)
                    .show();
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

        submit.setOnClickListener(view -> {
            //String username = name.getText().toString();
            DB.getRecords(value);
            text.setText(DB.getRecords(value));
            Toast.makeText(getApplicationContext(),value, Toast.LENGTH_LONG).show();
        });
    }

}