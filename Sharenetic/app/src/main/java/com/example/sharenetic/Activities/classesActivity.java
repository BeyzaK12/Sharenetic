package com.example.sharenetic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.sharenetic.R;

public class classesActivity extends AppCompatActivity {

    private TextView classNames;
    private ImageView icon;
    private Button joinClassBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        classNames = (TextView) findViewById(R.id.classNames);

        classNames.setText("# GUI Programming\n# Artificial Intelligence\n# Computer Networks\n# Design and Analysis\n    of Algorithms");

        icon = (ImageView) findViewById(R.id.icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(classesActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });

        joinClassBtn = (Button) findViewById(R.id.joinClass);
        joinClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joinIntent = new Intent(classesActivity.this, joinClassActivity.class);
                startActivity(joinIntent);
                finish();
            }
        });
    }
}
