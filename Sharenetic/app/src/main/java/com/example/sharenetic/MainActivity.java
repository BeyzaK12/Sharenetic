package com.example.sharenetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ImageButton sharePostBtn;
    private ImageButton commentsBtn;
    private Button calendarBtn;
    private ImageButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharePostBtn = (ImageButton) findViewById(R.id.addPostBtn);
        sharePostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharePostIntent = new Intent(MainActivity.this,sharePostActivity.class);
                startActivity(sharePostIntent);
                finish();
            }
        });

        commentsBtn = (ImageButton) findViewById(R.id.commentsBtn);
        commentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showCommentsIntent = new Intent(MainActivity.this,postActivity.class);
                startActivity(showCommentsIntent);
                finish();
            }
        });

        calendarBtn = (Button) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCalendar = new Intent(MainActivity.this,calendarActivity.class);
                startActivity(goCalendar);
                finish();
            }
        });

        logout = (ImageButton) findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();

                Intent homeIntent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(homeIntent);
                finish();

            }
        });
    }
}
