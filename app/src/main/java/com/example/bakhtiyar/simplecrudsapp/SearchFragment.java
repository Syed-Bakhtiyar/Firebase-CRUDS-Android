package com.example.bakhtiyar.simplecrudsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    ArrayList<Data> dataArrayList;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    Data data;

    EditText txt_name;

    String name;

    ListView listView;

    CustomAdapter customAdapter;

    View view;

    int count =0;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Students");

        dataArrayList = new ArrayList<>();

        txt_name = (EditText) view.findViewById(R.id.search);

        listView = (ListView) view.findViewById(R.id.searchList);



        view.findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = txt_name.getText().toString().trim();



//                Data datam = (Data) dataSnapshot.getValue(Data.class);
//
//                //     data = new Data(dataSnapshot.child("key").getValue().toString(),dataSnapshot.child("name").getValue().toString(),dataSnapshot.child("classs").getValue().toString(),Integer.parseInt( dataSnapshot.child("roll").getValue().toString()),Integer.parseInt( dataSnapshot.child("age").getValue().toString()) );
//
//                Log.d("mydata", "onDataChange: "+dataSnapshot.getKey());



                databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                ++count;



                           for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                               data = dataSnapshot1.getValue(Data.class);
                               dataArrayList.add(data);
                               Log.d("log", "onDataChange: "+dataSnapshot1.child("name").getValue());

                           }


                                func();






                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });








            }
        });




        return view;
    }

    public void func(){

        if(count!=0){

            customAdapter = new CustomAdapter(getContext(),dataArrayList);

            listView.setAdapter(customAdapter);

        }else {

            Toast.makeText(getContext(), "There is no data to show", Toast.LENGTH_SHORT).show();
            listView.setVisibility(View.GONE);
        }


    }

}
