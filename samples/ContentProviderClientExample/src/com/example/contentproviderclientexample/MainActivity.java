package com.example.contentproviderclientexample;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor> {

	private static final String TAG = "CONTENT_PROVIDER";
	private ListView _lv;
	private Button _btn;
	private int _ix;
	private Uri _todos;
	private SimpleCursorAdapter _adapter;
	
	private static void d(String fmt, Object...args){
		Log.d(TAG,String.format(fmt,args));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver cr = getContentResolver();
		_todos = Uri.parse("content://com.example.contentproviderserverexample/todos");
				
		_ix = 0;
		_btn = (Button) findViewById(R.id.button1);
		_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				ContentResolver cr = getContentResolver();
				ContentValues values = new ContentValues();
				values.put("desc", "study "+_ix++);
				cr.insert(_todos, values);				
			}			
		});
					
		_lv = (ListView) findViewById(R.id.listView1);
		
					
		_adapter = 
				new SimpleCursorAdapter(
						this, R.layout.list_item, null,
						new String[]{"_id","desc"},
						new int[]{R.id.textView1, R.id.textView2},
						0);
		_lv.setAdapter(_adapter);
		getLoaderManager().initLoader(1, null, this);
		
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		d("onCreateLoader");
		return new CursorLoader(this, _todos, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		d("onLoadFinished");
		_adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		d("onLoaderReset");
		_adapter.swapCursor(null);		
	}
}
