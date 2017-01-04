package com.example.bakhtiyar.simplecrudsapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {

    LayoutInflater inflater1;

    int temp;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    Data data;

    ListView listView;

    ArrayList<Data> dataArrayList;

    CustomAdapter customAdapter;

//    View v;

    View view;

    LayoutInflater infl;


    public ReadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_read, container, false);


        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Students");


        listView = (ListView) view.findViewById(R.id.readlist);

        dataArrayList = new ArrayList<>();

        customAdapter = new CustomAdapter(getContext(), dataArrayList);

        listView.setAdapter(customAdapter);


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Data datam = dataSnapshot.getValue(Data.class);

                dataArrayList.add(datam);

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                final View v = inflater1.from(getContext()).inflate(R.layout.custom_alert, null);
                temp = i;

                final Button b = (Button) v.findViewById(R.id.update);


                final EditText updtname, updtage, updtclass, updtroll;

                updtname = (EditText) v.findViewById(R.id.updtname);

                updtage = (EditText) v.findViewById(R.id.updtage);

                updtclass = (EditText) v.findViewById(R.id.updtclass);

                updtroll = (EditText) v.findViewById(R.id.updtrolls);


                AlertDialog.Builder alert;

                v.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Data tempData = new Data(dataArrayList.get(temp).getKey(), updtname.getText().toString().trim(), updtclass.getText().toString().trim(),
                                Integer.parseInt(updtroll.getText().toString().trim()),
                                Integer.parseInt(updtage.getText().toString().trim()));

                        databaseReference.child(dataArrayList.get(temp).getKey()).setValue(tempData);

                        dataArrayList.remove(temp);

                        dataArrayList.add(temp,tempData);

                        customAdapter.notifyDataSetChanged();
                    }
                });

                v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(temp == -1){

                            Toast.makeText(getContext(), "There is no data to delete", Toast.LENGTH_SHORT).show();

                        }else {

                            databaseReference.child(dataArrayList.get(temp).getKey()).removeValue();

                            dataArrayList.remove(temp);

                            customAdapter.notifyDataSetChanged();

                            updtname.setVisibility(View.GONE);

                            updtage.setVisibility(View.GONE);

                            updtclass.setVisibility(View.GONE);

                            updtroll.setVisibility(View.GONE);

                            b.setVisibility(View.GONE);

                            temp = -1;
                        }
                    }
                });
                alert = new AlertDialog.Builder(getContext()).setView(v);


                updtname.setText(dataArrayList.get(temp).getName());

                updtage.setText("" + dataArrayList.get(temp).getAge());

                updtclass.setText(dataArrayList.get(temp).getClasss());

                updtroll.setText("" + dataArrayList.get(temp).getRoll());


                try {


                    alert.show();

                } catch (Exception e) {

                    Log.d("show", "onItemClick: " + e);

                }




                return;


            }
        });


        return view;
    }


    public void show(AlertDialog.Builder alert) {

        alert.show();

    }

}
