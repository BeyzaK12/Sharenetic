package com.example.sharenetic.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sharenetic.Fragments.homeFragment;
import com.example.sharenetic.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton sharePostBtn;
    private Button calendarBtn;
    private Button classesBtn;
    private ImageButton logout;

    private com.example.sharenetic.Fragments.homeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new homeFragment();
        replaceFragment(homeFragment);




        sharePostBtn = (ImageButton) findViewById(R.id.addPostBtn);
        sharePostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharePostIntent = new Intent(MainActivity.this, sharePostActivity.class);
                startActivity(sharePostIntent);
                finish();
            }
        });

        /*
        commentsBtn = (ImageButton) findViewById(R.id.commentsBtn);
        commentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showCommentsIntent = new Intent(MainActivity.this,postActivity.class);
                startActivity(showCommentsIntent);
                finish();
            }
        });
        */

        calendarBtn = (Button) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCalendar = new Intent(MainActivity.this, calendarActivity.class);
                startActivity(goCalendar);
                finish();
            }
        });

        classesBtn = (Button) findViewById(R.id.classesBtn);
        classesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goClasses = new Intent(MainActivity.this, classesActivity.class);
                startActivity(goClasses);
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

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }

}
