package com.example.hu.slackerv6;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hu.slackerv6.*;


public class startscreen extends Activity {
    DatabaseAdapter database;
    EditText date;
    EditText place;
    EditText duration;
    EditText distance;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);

        database = new DatabaseAdapter(this);
    }

    //code for buttons
    public void goHome (View v){
        setContentView(R.layout.activity_startscreen);
    }
    public void previousLogs(View v) {
        setContentView(R.layout.previousruns);
        TextView c = (TextView)findViewById(R.id.runs);
        c.setText(database.getAllData());
    }

    public void logRun (View v) {
        setContentView(R.layout.logrunner
        );
    }

    //saving data
    public void save (View view) {
        date = (EditText) findViewById(R.id.date);
        place = (EditText) findViewById(R.id.place);
        duration = (EditText) findViewById(R.id.duration);
        distance = (EditText) findViewById(R.id.distance);
        description = (EditText) findViewById(R.id.description);

        String date = this.date.getText().toString();
        String place = this.place.getText().toString();
        String duration = this.duration.getText().toString();
        String distance = this.distance.getText().toString();
        String description = this.description.getText().toString();
        long id = database.insertData(date, place, duration, distance, description);

        Context context = getApplicationContext();
        CharSequence text = "Data was Successfully Saved";
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
