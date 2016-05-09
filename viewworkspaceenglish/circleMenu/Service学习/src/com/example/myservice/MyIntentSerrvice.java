package com.example.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentSerrvice extends IntentService {

	public MyIntentSerrvice(String name) {
		super(name);
	}
	
	public MyIntentSerrvice() {
		super("zhou");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.i("zhoulog", "onHandleIntent---helloIntentService---");
		for(int i=0;i<100;i++){
			try {
				Thread.sleep(1000);
				Log.i("zhoulog", i+"helloService");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("zhoulog", "onCreate---helloIntentService---");
	}

	@Override
	public void onDestroy() {
		Log.i("zhoulog", "onDestroy---helloService");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("zhoulog", "onStartCommand---helloService");
		return super.onStartCommand(intent, flags, startId);
//		return START_STICKY;//黏性的，可重新启动的
	}

}
