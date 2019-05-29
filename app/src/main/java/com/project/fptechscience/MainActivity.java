package com.project.fptechscience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.fptechscience.remote.AppRepository;

public class MainActivity extends AppCompatActivity {

    AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appRepository = new AppRepository(this);

        appRepository.getNetworkCall();
    }
}
