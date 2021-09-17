package com.example.android.loginapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }
    Context thiscontext;
    DBHelper DB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        assert container != null;
        thiscontext = container.getContext();

        View v = inflater.inflate(R.layout.fragment_register, container, false);
        Spinner spinner = v.findViewById(R.id.spinner);
        EditText user_name = v.findViewById(R.id.et_name);
        EditText email = v.findViewById(R.id.et_username);
        EditText password = v.findViewById(R.id.et_password);
        EditText repassword = v.findViewById(R.id.et_repassword);
        Button register = v.findViewById(R.id.btn_register);



        register.setOnClickListener(view -> {
            String name = user_name.getText().toString();
            String user = email.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            DB = new DBHelper(thiscontext);

            if (user.equals("")||pass.equals("")||repass.equals("")){
                Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_LONG).show();
            }
            else {

                if (pass.equals(repass)) {
                    Boolean checkuser = DB.checkUsername(user);
                    if (!checkuser) {

                        Boolean insert = DB.insertData(name, user, pass);

                        if (insert) {
                            Toast.makeText(getContext(), "Register Successful", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getContext(), "Register Unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "User already exist please try again!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Password Not matching", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.userlogin, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        return v;
    }

}