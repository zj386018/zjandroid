package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	private int myServiceId;
	static int mIndex = 0;
	private MediaPlayer mediaPlayer;
	// �ӿڶ���.
	IBinder mMybBinder = new MyBinder();

	// ����IBinder�ӿ�ʵ��.
	class MyBinder extends Binder {

		public MyService getMyService() {
			return MyService.this;
		}

		public String helloWord(String name) {
			return "hello,your name is:" + name;
		}

	}

	public void hello() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				helloService();
			}
		}).start();
	}

	private void helloService() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				Log.i("zhoulog", i + "helloService");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// ��©���ǵ�IBinder�ӿڶ��塣
		Log.i("zhoulog", "onBind----Service");
		return mMybBinder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		myServiceId = startId;

		int musiccommand;
		if (intent != null) {
			musiccommand = intent.getExtras().getInt("mediacommand");
			switch (musiccommand) {
			case 1:
				mediaPlayer.start();
				Toast.makeText(this, "music started", 3000).show();
				break;
			case 2:
				mediaPlayer.pause();
				Toast.makeText(this, "music pause", 3000).show();
				break;
			case 3:
				mediaPlayer.stop();
				mediaPlayer.release();
				Toast.makeText(this, "music stop", 3000).show();
				mediaPlayer = null;
				break;

			default:
				break;
			}
		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onCreate() {
		Log.i("zhoulog", "onCreate--" + (++mIndex));
		mediaPlayer = MediaPlayer.create(this, R.raw.rawmusic);
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.i("zhoulog", "Destroy----Service");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("zhoulog", "onUnbind----Service");
		return super.onUnbind(intent);
	}

}
