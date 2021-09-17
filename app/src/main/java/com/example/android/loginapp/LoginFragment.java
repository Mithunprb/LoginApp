package com.example.android.loginapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;



public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor

    }
    Context thiscontext;
    DBHelper DB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        assert container != null;
        thiscontext = container.getContext();

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Spinner spinner = v.findViewById(R.id.spinner);



        EditText email = v.findViewById(R.id.username);
        EditText password = v.findViewById(R.id.password);
        Button login = v.findViewById(R.id.btn_login);

        login.setOnClickListener(view -> {
            String user = email.getText().toString();
            String pass = password.getText().toString();

           DB = new DBHelper(thiscontext);

            if (user.equals("")||pass.equals("")){

                Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_LONG).show();
            }
            else {
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if (checkuserpass){

                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        intent.putExtra("key", user);
                        startActivity(intent);

                }
                else {
                    Toast.makeText(getContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
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

        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                // On selecting a spinner item
                String item = parent.getItemAtPosition(pos).toString();
                if(String.valueOf(spinner.getSelectedItem()).equals("Admin")) {
                    Intent intent = new Intent(getContext(), TrackActivity.class);
                    startActivity(intent);
                }
                String.valueOf(spinner.getSelectedItem());

                Toast.makeText(parent.getContext(), "You are in : " + item, Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        return v;
    }

}
