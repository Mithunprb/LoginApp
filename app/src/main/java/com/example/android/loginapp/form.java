package com.example.android.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        DBHelper DB;
        EditText name, dob, email, address, clgname, percentage, extraca, bloodgrp,
                last_qual, vaccination, ref_id, date;
        Button submit;
        name = findViewById(R.id.form_name);
        dob = findViewById(R.id.form_dob);
        email = findViewById(R.id.form_email);
        address = findViewById(R.id.form_addr);
        clgname = findViewById(R.id.formclgname);
        percentage = findViewById(R.id.form_perc);
        extraca = findViewById(R.id.form_eca);
        bloodgrp = findViewById(R.id.form_bloodgroup);
        last_qual = findViewById(R.id.last_qul);
        vaccination = findViewById(R.id.vaccine);
        ref_id = findViewById(R.id.refid);
        date = findViewById(R.id.refid_date);
        submit  = findViewById(R.id.btn_formregister);

        DB = new DBHelper(this);
        submit.setOnClickListener(view -> {
            String fname = name.getText().toString();
            String femail = email.getText().toString();
            String fdob = dob.getText().toString();
            String faddr = address.getText().toString();
            String fclg = clgname.getText().toString();
            String fperc = percentage.getText().toString();
            String feca = extraca.getText().toString();
            String fbloodgrp = bloodgrp.getText().toString();
            String fqual = last_qual.getText().toString();
            String fvaccin = vaccination.getText().toString();
            String fref_id = ref_id.getText().toString();
            String fdate = date.getText().toString();

            Boolean insert = DB.insertForm(fname, fdob, femail, faddr, fclg, fperc, feca, fbloodgrp, fqual,fvaccin, fref_id, fdate);

            if (insert){
                Toast.makeText(getApplicationContext(), "Form submitted", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Something is wrong", Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });
    }
}