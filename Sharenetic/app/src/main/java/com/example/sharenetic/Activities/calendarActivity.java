package com.example.sharenetic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharenetic.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class calendarActivity extends AppCompatActivity {

    private ImageView icon;
    private TextView month;
    private TextView exp;
    private Button downloadButton;
    private ImageButton addN;
    private ImageView imageView;


    final private int REQUEST_INTERNET = 123;
    static String pictureURL = "http://2016.igem.org/wiki/images/e/e9/Schedule_Overview.png";
    //static String pictureURL = "https://image.slidesharecdn.com/8d5ce9b8-c700-4709-81b9-3c68d5246de0-151128045200-lva1-app6892/95/ms-in-mis-course-curriculum-1-638.jpg";

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        addN = (ImageButton) findViewById(R.id.addEvent);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);

        downloadButton = (Button)findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DownloadImageTask().execute(pictureURL);

            }
        });

        icon = (ImageView) findViewById(R.id.icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(calendarActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });

        month = (TextView) findViewById(R.id.monthTitle);
        exp = (TextView) findViewById(R.id.exp);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactCalendarView);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //Set an event for GUI final exam on 09.01.2020 10:00

        Event ev1 = new Event(Color.BLUE, 1578582000000L, "GUI Final Exam" );
        compactCalendar.addEvent(ev1);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                exp.setText(null);
                exp.setBackgroundColor(Color.TRANSPARENT);
                Context context = getApplicationContext();

                if (dateClicked.toString().compareTo("Thu Jan 09 00:00:00 GMT+03:00 2020") == 0){
                    int myColorValue = Color.parseColor("#80E4E4E4");

                    exp.setBackgroundColor(myColorValue);
                    exp.setText("GUI Final Exam\n10:00\n\nGrading Policy for Phase III:\n-Predemonstration (%10)" +
                                "\n-Demonstration (%20)\n-Implementation ");
                }
                else {
                    Toast.makeText(context, "No event!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                month.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection!");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK){
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex){
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }
    private Bitmap DownloadImage(String URL){
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();

        } catch (IOException e1) {
            Log.d("NetworkingActivity", e1.getLocalizedMessage());
        }
        return bitmap;
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            return DownloadImage(urls[0]);
        }
        protected void onPostExecute(Bitmap result) {
            compactCalendar.setVisibility(View.INVISIBLE);
            month.setVisibility(View.INVISIBLE);
            exp.setVisibility(View.INVISIBLE);
            downloadButton.setVisibility(View.INVISIBLE);
            addN.setVisibility(View.INVISIBLE);

            imageView.setVisibility(View.VISIBLE);

            imageView.setImageBitmap(result);
        }
    }


}
