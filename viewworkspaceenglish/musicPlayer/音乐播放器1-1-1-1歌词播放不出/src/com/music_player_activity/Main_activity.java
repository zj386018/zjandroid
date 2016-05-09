package com.music_player_activity;

import java.util.ArrayList;
import java.util.List;

import com.music_adapter.Music_listAdapter;
import com.music_class.Music;
import com.music_class.MusicGet;
import com.music_interface.AppConstant;
import com.music_player.R;
import com.music_services.Playerservices;
import com.music_viewholder.ViewHolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main_activity extends Activity implements Runnable {

	// private MediaPlayer mediaPlayer;

	int aaaposition = 0;
	MusicGet musicGet = new MusicGet(); // 用于获取数据库中的音乐数据
	List<Music> musiclists = new ArrayList<Music>(); // 用来保存获得的音乐数据
	private Music_listAdapter adapter;
	private ListView main_listview; // 显示音乐条目
	private TextView mp_message, time_message;
	ViewHolder viewHolder = new ViewHolder();

	private int listposition = 0; // 用来标示播放的音乐
	boolean isplaying = false;
	private SeekBar playBar;
	
	
	private Thread thread;
	private Handler mHandler;
	private int tottime,alltime;
	private final int msg_key = 0x1234;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏

		setContentView(R.layout.activity_main);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏显示

		musiclists = musicGet.getMusicList(getApplicationContext());

		// String aaa = musiclists.get(0).toString();
		// Toast.makeText(Main_activity.this, aaa, Toast.LENGTH_LONG).show();

		adapter = new Music_listAdapter(Main_activity.this, musiclists);
		main_listview = (ListView) findViewById(R.id.main_listview);
		main_listview.setAdapter(adapter);
		mp_message = (TextView) findViewById(R.id.mian_txtv);
		time_message = (TextView) findViewById(R.id.main_time);
		playBar = (SeekBar) findViewById(R.id.main_seekbar);
		viewHolder.music_front = (ImageButton) findViewById(R.id.main_ibt_front);
		viewHolder.music_start = (ImageButton) findViewById(R.id.main_ibt_start);
		viewHolder.music_next = (ImageButton) findViewById(R.id.main_ibt_next);

		main_listview.setOnItemClickListener(new MusicListItemClickListener());
		viewHolder.music_front.setOnClickListener(new frontClickListener());
		viewHolder.music_next.setOnClickListener(new nextClickListener());
		viewHolder.music_start.setOnClickListener(new startClickListener());
		playBar.setOnSeekBarChangeListener(new SeekbarChangeListener());

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				/* 信息处理 */
				super.handleMessage(msg);
				switch (msg.what) {
				case msg_key:
					/* 处理时间事件 */
					alltime = Playerservices.mediaPlayer.getDuration();
					tottime = Playerservices.mediaPlayer.getCurrentPosition();
					if (Playerservices.mediaPlayer.isPlaying()) {
						int minute = tottime / 60000;  
				        int second = (tottime/1000) % 60; 
						String sTime= String.format("%02d:%02d", minute, second); 
						time_message.setText(sTime+"/"+musiclists.get(listposition).getMusic_timelength());
						playBar.setMax(alltime);
						playBar.setProgress(tottime);
					}

					break;
				default:
					break;
				}
			}
		};
		thread = new Thread(this);
		thread.start();

	}

	/**
	 * 点击音乐播放
	 * 
	 * @author 周健
	 * 
	 */
	private class MusicListItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (musiclists != null) {
				listposition = position;
				Music mp3Info = musiclists.get(position);
				mp_message.setText(mp3Info.getMusic_name());
				time_message.setText(mp3Info.getMusic_timelength());
				viewHolder.music_start
						.setBackgroundResource(R.drawable.music_stop);
				isplaying = true;
				Intent intent = new Intent();
				Intent intent2 = new Intent();
				intent.putExtra("url", mp3Info.getMusic_url());
				intent2.putExtra("url", mp3Info.getMusic_url());
				intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
				intent.setClass(Main_activity.this, Playerservices.class);
				startService(intent); // 启动服务
				intent2.setClass(Main_activity.this,lrc_activity.class);
				startActivity(intent2);
			}
		}
	}

	/**
	 * 上一首点击事件
	 * 
	 * @author 周健
	 * 
	 */
	private class frontClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (listposition == 0) {
				Toast.makeText(Main_activity.this, "已经是第一首歌曲",
						Toast.LENGTH_SHORT).show();
			} else {
				listposition -= 1;
				Music mp3Info = musiclists.get(listposition);
				mp_message.setText(mp3Info.getMusic_name());
				time_message.setText(mp3Info.getMusic_timelength());
				viewHolder.music_start
						.setBackgroundResource(R.drawable.music_stop);
				isplaying = true;
				Intent intent = new Intent();
				intent.putExtra("url", mp3Info.getMusic_url());
				intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
				intent.setClass(Main_activity.this, Playerservices.class);
				startService(intent); // 启动服务
			}

		}
	}

	/**
	 * 下一首点击事件
	 * 
	 * @author 周健
	 * 
	 */
	private class nextClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (listposition == musiclists.size() - 1) {
				Toast.makeText(Main_activity.this, "已经是最后一首歌曲",
						Toast.LENGTH_SHORT).show();
			} else {
				listposition += 1;
				Music mp3Info = musiclists.get(listposition);
				mp_message.setText(mp3Info.getMusic_name());
				time_message.setText(mp3Info.getMusic_timelength());
				viewHolder.music_start
						.setBackgroundResource(R.drawable.music_stop);
				isplaying = true;
				Intent intent = new Intent();
				intent.putExtra("url", mp3Info.getMusic_url());
				intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
				intent.setClass(Main_activity.this, Playerservices.class);
				startService(intent); // 启动服务
				
			}

		}
	}

	/**
	 * 播放与停止音乐
	 * 
	 * @author 周健
	 * 
	 */
	private class startClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Music mp3Info = musiclists.get(listposition);
			mp_message.setText(mp3Info.getMusic_name());
			time_message.setText(mp3Info.getMusic_timelength());
			if (isplaying == true) {
				viewHolder.music_start
						.setBackgroundResource(R.drawable.music_play);
				isplaying = false;
				Intent intent = new Intent();
				// intent.putExtra("url", mp3Info.getMusic_url());
				intent.putExtra("MSG", AppConstant.PlayerMsg.PAUSE_MSG);
				intent.setClass(Main_activity.this, Playerservices.class);
				startService(intent); // 启动服务
			} else if (isplaying == false) {

				viewHolder.music_start
						.setBackgroundResource(R.drawable.music_stop);
				isplaying = true;
				Intent intent = new Intent();
				intent.putExtra("url", mp3Info.getMusic_url());
				if (listposition == 0 && aaaposition == 0) {
					intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
					aaaposition++;
				} else {
					intent.putExtra("MSG", AppConstant.PlayerMsg.CONTINUE_MSG);
				}
				intent.setClass(Main_activity.this, Playerservices.class);
				startService(intent); // 启动服务
			}
		}
	}

	private class SeekbarChangeListener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (fromUser) {
				if (Playerservices.mediaPlayer != null) {
					playBar.setMax(Playerservices.mediaPlayer.getDuration());
					Playerservices.mediaPlayer.seekTo(progress);
				} else {
					Toast.makeText(Main_activity.this, "当前没有播放歌曲",
							Toast.LENGTH_LONG).show();
				}
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	}

	public void run() {
		try {
			do {
				Thread.sleep(100);
				Message msg = new Message();
				msg.what = msg_key;
				mHandler.sendMessage(msg);
			} while (Thread.interrupted() == false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("退出")
					.setMessage("您确定要退出？")
					.setNegativeButton("取消", null)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									try {
										thread.interrupt();
										Main_activity.this.finish();
										Intent intent = new Intent(
												Main_activity.this,
												Playerservices.class);
										// stopService(intent);

									} catch (Exception e) {
									} // 停止后台服务
								}
							}).show();

		}
		return super.onKeyDown(keyCode, event);
	}

}
