package com.elimei.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.elimei.elimei.ELiMeiActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getBaseContext(), ELiMeiActivity.class);
        intent.putExtra("token","6S6P01,C1EEA7B1-E302-4D59-A5B1-5A9EC89B6A04,12abe194bf936c8693a4e819799732a1,851160,0,9997");
        startActivity(intent);
    }
}
