package com.example.myapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class DisplayMessageActivity extends ActionBarActivity {

	TextView tw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Intent intent=getIntent();
		String message=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);*/
		setContentView(R.layout.activity_display_message);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		tw=(TextView)findViewById(R.id.a);
		tw.setText(MainActivity.toSend);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_message, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id){
			case R.id.action_gear: {
			tw.setTextColor(1);	
		}
			case R.id.action_settings: {
				tw.setTextColor(100);
			}
		}
		return super.onOptionsItemSelected(item);
	}
}
