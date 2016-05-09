package com.example.myservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MusicPlayerActivity extends Activity implements OnClickListener {

	private Button bt_play;
	private Button bt_pause;
	private Button bt_stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_player);
		initview();

	}

	private void initview() {
		bt_play = (Button) findViewById(R.id.bt_play);
		bt_play.setOnClickListener(this);
		bt_pause = (Button) findViewById(R.id.bt_pause);
		bt_pause.setOnClickListener(this);
		bt_stop = (Button) findViewById(R.id.bt_stop);
		bt_stop.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.bt_play:
			bt_playClick();
			break;
		case R.id.bt_pause:
			bt_pauseClick();
			break;
		case R.id.bt_stop:
			bt_stopClick();
			break;
		default:
			break;
		}

	}

	private void bt_playClick() {
		Intent intent = new Intent(MusicPlayerActivity.this, MyService.class);
		// 1表示播放
		intent.putExtra("mediacommand", 1);
		startService(intent);
	}

	private void bt_pauseClick() {
		Intent intent = new Intent(MusicPlayerActivity.this, MyService.class);
		// 2表示暂停
		intent.putExtra("mediacommand", 2);
		startService(intent);
	}

	private void bt_stopClick() {
		Intent intent = new Intent(MusicPlayerActivity.this, MyService.class);
		// 3表示停止
		intent.putExtra("mediacommand", 3);
		startService(intent);
	}

}
