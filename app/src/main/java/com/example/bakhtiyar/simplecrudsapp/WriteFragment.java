package com.example.bakhtiyar.simplecrudsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteFragment extends Fragment {

    View view;

    int roll, age;

    String name, classs;

    EditText txtname, txtage, txtclass, txtroll;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    Data data;


    public WriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_write, container, false);

        // For offline capabilities



        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Students");


        txtname = (EditText) view.findViewById(R.id.writename);

        txtage = (EditText) view.findViewById(R.id.writeage);

        txtclass = (EditText) view.findViewById(R.id.writeclass);

        txtroll = (EditText) view.findViewById(R.id.writeroll);

        view.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {

                    name = txtname.getText().toString().trim();

                    classs = txtclass.getText().toString().trim();

                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(classs)) {

                        Toast.makeText(getContext(), "Please Enter Name And Class", Toast.LENGTH_SHORT).show();

                    } else {

                        age = Integer.parseInt(txtage.getText().toString().trim());

                        roll = Integer.parseInt(txtroll.getText().toString().trim());

                        data = new Data(databaseReference.push().getKey(), name, classs, roll, age);

                        databaseReference.child(data.getKey()).setValue(data);

                        Toast.makeText(getContext(), "Submitted", Toast.LENGTH_SHORT).show();

                        txtname.setText("");

                        txtclass.setText("");

                        txtroll.setText("");

                        txtage.setText("");

                    }
                } catch (Exception e) {

                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                }


            }
        });


        return view;
    }

}
