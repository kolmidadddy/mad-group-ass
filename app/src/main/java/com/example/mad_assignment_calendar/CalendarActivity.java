package com.example.mad_assignment_calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.calendarmad.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Locale;

import android.app.AlertDialog;
import android.widget.EditText;


public class CalendarActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> showEventDialog(year, month, dayOfMonth));
    }
    private void saveEvent(int year, int month, int dayOfMonth, String eventDescription) {
        // Dummy implementation
        // TODO: Save the event to your storage solution (e.g. Database, SharedPreferences, etc.)
        String eventDate = dayOfMonth + "/" + (month+1) + "/" + year;  // month is 0-based
        System.out.println("Event on " + eventDate + ": " + eventDescription);
    }

    private void showEventDialog(int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Event");

        final EditText input = new EditText(this);
        input.setHint("Event description");
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String eventDescription = input.getText().toString();
            saveEvent(year, month, dayOfMonth, eventDescription);
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

}
