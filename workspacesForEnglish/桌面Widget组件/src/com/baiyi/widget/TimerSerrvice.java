package com.baiyi.widget;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class TimerSerrvice extends Service {

	private Timer timer;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updateViews();
			}
		}, 0, 1000);// 第一个参数是更新时执行的任务，第二个参数是更新的延迟，第三个是更新的频率
	}

	private void updateViews() {

		String time = sdf.format(new Date());
		RemoteViews rv = new RemoteViews(getPackageName(), R.layout.widget);
		rv.setTextViewText(R.id.tv, time);
		AppWidgetManager manager = AppWidgetManager
				.getInstance(getApplicationContext());
		ComponentName cn = new ComponentName(getApplicationContext(),
				WidgetProvider.class);
		manager.updateAppWidget(cn, rv);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		timer = null;

	}

}
