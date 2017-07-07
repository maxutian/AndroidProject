package com.demo.mrma.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton recovery,frontCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        allClick();
    }

    public void init () {
//        recovery = (FloatingActionButton) findViewById(R.id.recovery_button);
//        frontCamera = (FloatingActionButton) findViewById(R.id.front_camera_button);
    }

    public void allClick () {

//      recovery clickEvent
//        frontCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
