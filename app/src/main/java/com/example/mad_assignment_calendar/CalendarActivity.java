package com.example.mad_assignment_calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.calendarmad.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        // Get current time in GMT+8 timezone
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        long currentTime = calendar.getTimeInMillis();

        // Set CalendarView's date to the current time
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setDate(currentTime, true, true);

        // Format and display the current year, date, and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));  // Corrected the timezone here
        String currentDateTime = dateFormat.format(calendar.getTime());

        TextView dateTextView = findViewById(R.id.calendar_tv1);
        dateTextView.setText(currentDateTime);

        TextView currentDateTextView = findViewById(R.id.currentDateTextView);
        currentDateTextView.setText("Current Date: " + currentDateTime.substring(0, 10)); // To display just the date
    }
}
