package com.example.photoalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements onButtonPressListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onButtonPressed(Integer index)
    {
        //Get the index from option fragment (decrement or increment)
        //Pass it to image fragment to display corresponding image stored in array
    }
}