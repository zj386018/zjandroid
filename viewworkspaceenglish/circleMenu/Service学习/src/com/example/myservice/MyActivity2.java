package com.example.myservice;

import com.example.myservice.MyService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity2 extends Activity implements OnClickListener{
	private Button bt_start;
	private Button bt_stop;
	private Button bt_startIntent;
	private Button bt_stopIntent;
	private Button bt_startBinder;
	private Button bt_unBinder;
	private Intent intent;
	private Intent intentService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}

	private void initView() {
		bt_start = (Button) findViewById(R.id.main_btStart);
		bt_start.setOnClickListener(this);
		bt_stop = (Button) findViewById(R.id.main_btStop);
		bt_stop.setOnClickListener(this);
		bt_startIntent = (Button) findViewById(R.id.main_btStartIntent);
		bt_startIntent.setOnClickListener(this);
		bt_stopIntent = (Button) findViewById(R.id.main_btStopIntent);
		bt_stopIntent.setOnClickListener(this);
		bt_startBinder = (Button) findViewById(R.id.main_btStartBinder);
		bt_startBinder.setOnClickListener(this);
		bt_unBinder = (Button) findViewById(R.id.main_btunBinder);
		bt_unBinder.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.main_btStart:
			btstartClickEvent();
			break;
		case R.id.main_btStop:
			btstopClickEvent();
			break;
		case R.id.main_btStartIntent:
			btstartIntentClickEvent();
			break;
		case R.id.main_btStopIntent:
			btstopIntentClickEvent();
			break;
		case R.id.main_btStartBinder:
			btstartBinderClickEvent();
			break;
		case R.id.main_btunBinder:
			btUnBinderClickEvent();
			break;
		default:
			break;
		}
	}

	private void btUnBinderClickEvent() {
		unbindService(conn);
	}

	private void btstartBinderClickEvent() {
		Intent intentBinder = new Intent(MyActivity2.this,MyService.class);
		bindService(intentBinder, conn, BIND_AUTO_CREATE);
	}
	//建立服务连接
	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Log.i("zhoulog", "onServiceDisconnected");
		}
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder service) {
//			String myNameString = ((MyBinder)service).helloWord("zhou");
//			Toast.makeText(MyActivity2.this,myNameString, Toast.LENGTH_SHORT).show();
			Log.i("zhoulog", "onServiceConnected");
			((MyBinder)service).getMyService().hello();
		}
	};

	
	private void btstopIntentClickEvent() {
		stopService(intentService);
	}

	private void btstartIntentClickEvent() {
		intentService = new Intent(MyActivity2.this,MyIntentSerrvice.class);
		startService(intentService);
	}

	public void btstartClickEvent() {
		intent = new Intent(MyActivity2.this, MyService.class);
		startService(intent);
	}
	
	public void btstopClickEvent(){
		stopService(intent);
	}
}
