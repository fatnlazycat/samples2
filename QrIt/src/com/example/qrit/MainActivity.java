package com.example.qrit;

import android.support.v7.app.ActionBarActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.app.AlertDialog;
import android.content.*;
import android.graphics.drawable.Drawable;
import android.widget.*;


public class MainActivity extends ActionBarActivity {

	TextView textView_result;
	EditText editText_Encode;
	ImageView imgResult;
	final ActionBarActivity thisActivity=MainActivity.this;
	
    void qrDroidRequired(){
		AlertDialog.Builder builder=new AlertDialog.Builder(thisActivity);
		builder.setMessage(thisActivity.getString(R.string.qrdroidMissing))
		.setCancelable(true)
		.setNegativeButton(thisActivity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {dialog.cancel();}
			})
		.setPositiveButton(thisActivity.getString(R.string.getFromMarket), new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					thisActivity.startActivity(new Intent(Intent.ACTION_VIEW,
							Uri.parse("https://market.android.com/details?id=la.droid.qr")));
				}
		});
		builder.create().show();
		//or https://market.android.com/details?id=la.droid.qrdroid.la/apk/qr/
    }
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_result=(TextView) findViewById(R.id.textView_result);
        imgResult=(ImageView) findViewById(R.id.imgResult);
        Toast.makeText(thisActivity, "This app uses QR Droid (http://qrdroid.com/) as a scanning engine."
				+ "QR Droid is free for downloading & distribution."
				+ "If you haven't got one istalled on your device,"
				+ "you'll be able to do this after tapping either Scan or Encode buttons"
				, Toast.LENGTH_LONG).show();
		editText_Encode=(EditText) findViewById(R.id.editText_Encode);
		/*Drawable d=getResources().getDrawable(R.drawable.m9);
        getActionBar().setBackgroundDrawable(d);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void startScan(View view){
    	Intent qrDroid=new Intent("la.droid.qr.scan");
    	try{
    		startActivityForResult(qrDroid,0);
    	}
    	catch(ActivityNotFoundException e){
    		qrDroidRequired();
    	}
    }
    
    public void startEncoding(View view){
    	String code=editText_Encode.getText().toString();
    	if (code.trim().length()==0) {
    		Toast.makeText(thisActivity, R.string.noText, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	Intent qrDroid=new Intent("la.droid.qr.encode");
    	qrDroid.putExtra("la.droid.qr.code", code);
    	qrDroid.putExtra("la.droid.qr.image", true);
    	try{
    		startActivityForResult(qrDroid,1);
    	}
    	catch (ActivityNotFoundException activity){
    		qrDroidRequired();
    	}
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	String result;
    	if (data!=null && data.getExtras()!=null){
    		switch (requestCode){
    		case 0:{
        		result=data.getExtras().getString("la.droid.qr.result");
        		textView_result.setText(result);    			
    		} break;
    		case 1:{
    			result=data.getExtras().getString("la.droid.qr.result");
    			if (result==null || result.trim().length()==0) {
    				Toast.makeText(thisActivity, getString(R.string.notSaved), Toast.LENGTH_LONG).show();
    				return;
    			}
    			imgResult.setImageURI(Uri.parse(result));
    			imgResult.setVisibility(View.VISIBLE);
    			imgResult.requestFocus();
    		}}
    	}
    }
}
