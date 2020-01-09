package com.example.sharenetic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sharenetic.R;

public class joinClassActivity extends AppCompatActivity {

    private EditText className;
    private EditText key;
    private Button joinBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);

        className = (EditText) findViewById(R.id.className);
        key = (EditText) findViewById(R.id.key);

        joinBtn = (Button) findViewById(R.id.joinBtn);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goClassesIntent = new Intent(joinClassActivity.this, classesActivity.class);
                startActivity(goClassesIntent);
                finish();
            }
        });
    }
}
