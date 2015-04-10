package com.example.myapp;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.*;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE="com.example.myapp.MESSAGE";
    static String toSend;
    EditText editText;
    TextView textView1;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setMessage("it's a dialog")
		.setCancelable(true)
		.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {dialog.cancel();}
			})
			.setPositiveButton("getFromMarket", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					/*thisActivity.startActivity(new Intent(Intent.ACTION_VIEW,
							Uri.parse("https://market.android.com/details?id=la.droid.qr")));*/
				}
			});
			builder.create().show();        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        editText=(EditText) findViewById(R.id.edit_message);
        textView1=(TextView) findViewById(R.id.textView1);
        int id = item.getItemId();
        textView1.setText("action_search=" + R.id.action_search + " id=" + id +
        		"action_settings=" + R.id.action_settings);
		switch (id){
			case R.id.action_settings: editText.setText("no doubt"); break;
			case R.id.action_search: editText.setText("Hurrah!"); break;	
		}
        return super.onOptionsItemSelected(item);
    }
    
    public void sendMessage(View view){	
    	Intent intent=new Intent(this, DisplayMessageActivity.class);
    	editText=(EditText) findViewById(R.id.edit_message);
    	String message=editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	toSend=editText.getText().toString();
    	startActivity(intent);
    }
}
