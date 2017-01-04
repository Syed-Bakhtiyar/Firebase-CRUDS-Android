package com.example.bakhtiyar.simplecrudsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 12/30/2016.
 */
public class CustomAdapter extends BaseAdapter {

    TextView name, age, classs, roll;

    Context context;

    ArrayList<Data> data;

    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.from(context).inflate(R.layout.for_custom_list,viewGroup,false);

        name = (TextView) view.findViewById(R.id.readname);
        age = (TextView) view.findViewById(R.id.readage);
        classs = (TextView) view.findViewById(R.id.readclass);
        roll = (TextView) view.findViewById(R.id.readroll);



        name.setText(name.getText()+data.get(i).getName());

        age.setText(age.getText()+""+ data.get(i).getAge());

        classs.setText(classs.getText()+data.get(i).getClasss());

        roll.setText(roll.getText()+""+data.get(i).getRoll());



        return view;
    }
}
