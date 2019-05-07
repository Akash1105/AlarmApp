package com.example.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AlarmManager object;
    TimePicker timeobj;
    Button alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = findViewById(R.id.button);
        timeobj = findViewById(R.id.tp);
        alarm.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),
                timeobj.getCurrentHour(),timeobj.getCurrentMinute(),0);
        setAlarm(c.getTimeInMillis());

    }

    private void setAlarm(long timeInMillis){
        object = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this,MyReceiver.class);
        PendingIntent intobj = PendingIntent.getBroadcast(this,0,intent,0);
        object.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,intobj);
        Toast.makeText(this,"Alarm is set",Toast.LENGTH_LONG).show();
    }
}
