package com.example.mediacatalogue;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends ActionBarActivity {

	VideoView videoView;
	MediaController mediaController;
	ProgressDialog progressDialog;
	private int position=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mediaController=new MediaController(this);
		videoView=(VideoView)findViewById(R.id.video_view);
		videoView.setMediaController(mediaController);
		try{
			//String s="android.resource://" + getPackageName() + "/" + R.raw.sky;
			String s="http://testapi.qix.sx/video/sky.mp4";
			videoView.setVideoURI(Uri.parse(s));
		} catch(Exception e){
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		videoView.requestFocus();
		videoView.setOnPreparedListener(new OnPreparedListener(){
			public void onPrepared(MediaPlayer mediaPlayer){
				progressDialog.dismiss();
				videoView.seekTo(position);
				if (position==0) videoView.start();
				else videoView.pause();
			}
		});
		
		progressDialog=new ProgressDialog(this);
		progressDialog.setTitle("Please wait till we load a file.");
		progressDialog.setMessage("Loading...");
		//progressDialog.setCancelable(false);
		progressDialog.show();
		
	}

	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("Position", videoView.getCurrentPosition());
		videoView.pause();
	}
	
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		position=savedInstanceState.getInt("Position");
		videoView.seekTo(position);
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
}
