package com.example.bakhtiyar.simplecrudsapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    TabLayout tabLayout;

    MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        myFragment = new MyFragment(getSupportFragmentManager());

        viewPager.setAdapter(myFragment);

        tabLayout.setupWithViewPager(viewPager);


    }
}
