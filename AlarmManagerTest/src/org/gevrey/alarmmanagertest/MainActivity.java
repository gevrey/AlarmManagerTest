package org.gevrey.alarmmanagertest;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private PendingIntent pendingIntent;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button m_ButtonAlarm = (Button) findViewById(R.id.buttonAlarm);
        Button m_ButtonCancel = (Button)findViewById(R.id.buttonCancel);
	    View.OnClickListener handlerButtonAlarm = new View.OnClickListener() {
		    public void onClick(View v) {
				//alarm.SetAlarm(MainActivity.this);
		    	
	   		    Intent myIntent = new Intent(MainActivity.this, MyAlarmService.class);		
			    pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);
			    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTimeInMillis(System.currentTimeMillis());
			    calendar.add(Calendar.SECOND, 10);
			    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

			    Toast.makeText(MainActivity.this, "Alarm set", Toast.LENGTH_SHORT).show();
		    };
	    };
	    m_ButtonAlarm.setOnClickListener(handlerButtonAlarm);		

	    m_ButtonCancel.setOnClickListener(new Button.OnClickListener(){
	    	 @Override
	    	 public void onClick(View arg0) {
	    		 // TODO Auto-generated method stub
	    		 AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	    		 alarmManager.cancel(pendingIntent);
	    		 // Tell the user about what we did.
	    		 Toast.makeText(MainActivity.this, "Cancel!", Toast.LENGTH_LONG).show();
	    	 }
	    });	
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
