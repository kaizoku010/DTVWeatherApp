package com.hava.dvtweatherapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.hava.dvtweatherapp.Country;
import com.hava.dvtweatherapp.MainActivity;
import com.hava.dvtweatherapp.ui.holders.ScreenItem;
import java.util.ArrayList;
import java.util.List;
import dvtweatherapp.R;


public class OnBoardingActivity extends AppCompatActivity {

    ViewPager viewPager2;
    OnBoardingAdapter pageAdapter;
    TabLayout tablayout;
    Animation animation_;
    Button getStarted_btn, next;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //check if weather data is already available
        checkIfDataAvailable();
        viewPager2 = findViewById(R.id.intropager);
        //the screenItem is represents wats currently on the screen.
        List<ScreenItem> itemList = new ArrayList<>();
        next = findViewById(R.id.btn_next);

        pageAdapter = new OnBoardingAdapter(this, itemList);
        tablayout = findViewById(R.id.tabLayout_into);

        getStarted_btn = findViewById(R.id.getStarted_btn);
        getStarted_btn.setVisibility(View.INVISIBLE);

        tablayout.setupWithViewPager(viewPager2);
        getStarted_btn.setAnimation(animation_);
        itemList.add(new ScreenItem("Hi There",
                "Welcome to the DVT WeatherApp, all about your weather in one spot...swipe left to proceed." +
                        "Also we don't collect user information",
                R.drawable.cloudy_image, "",View.VISIBLE));
        itemList.add(new ScreenItem("How It Works",
                "To get started, Please add your current location by City name or Country name from the locations activity. ",
                R.drawable.google_maps_pin, "", View.VISIBLE));
        viewPager2.setAdapter(pageAdapter);

        next.setOnClickListener(view -> {
            //get the position of the Viewpager.
            //check if its less than itemList.length
            position = viewPager2.getCurrentItem();
            if (position < itemList.size()) {
                position++;
                viewPager2.setCurrentItem(position);
            }
            if (position == itemList.size()-1){
                //TO-DO:  make save button appear here
                loadLastSession();
            }
        });

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == itemList.size()- 1){
                    loadLastSession();
                }
                else {
                    getStarted_btn.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.GONE);
                    tablayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    private void checkIfDataAvailable() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String testString = sharedPrefs.getString("key",null);
        //open home_screen if user data available
        if (testString != null) {
            openHomeScreen();
        }
    }


    private void openHomeScreen() {
        Intent intent = new Intent(OnBoardingActivity.this, Country.class);
        startActivity(intent);
        finish();

    }

    //open Settings Screen
    private void getStarted_btnOnclick() {
        getStarted_btn.setOnClickListener(view -> openHomeScreen());
    }

    private void loadLastSession() {
        next.setVisibility(View.GONE);
        getStarted_btn.setVisibility(View.VISIBLE);
        tablayout.setVisibility(View.INVISIBLE);
        getStarted_btnOnclick();

    }

}
