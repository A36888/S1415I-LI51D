package com.example.tasks1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

	protected static final String TAG = "TasksExample";
	private void d(String msg){
		Log.d(TAG,"SecondActivity:" + msg);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		d("onCreate");
		setContentView(R.layout.activity_main);		
		TextView tv = (TextView) findViewById(R.id.tv1);
		final int c = getInputParameter(getIntent());
		tv.setText(""+c+" - "+hashCode());
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(SecondActivity.this, MainActivity.class);
				i.putExtra("count", c+1);
				startActivity(i);				
			}			
		});
	}	
	
	@Override
	protected void onStart(){
		super.onStart();
		d("onStart");
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		d("onResume");
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		d("onPause");
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		d("onStop");
	}

	
	private int getInputParameter(Intent i) {
		return i.getIntExtra("count", 0);		 
	}
}

