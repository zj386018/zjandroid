package com.music_player_activity;

import java.util.ArrayList;
import java.util.List;

import com.music_class.LrcProcess;
import com.music_class.LrcView;
import com.music_class.lrcContent;
import com.music_player.R;
import com.music_services.Playerservices;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

public class lrc_activity extends Activity implements Runnable {
	private static  LrcView lrcView;
	private LrcProcess mLrcProcess; //歌词处理  
	private List<lrcContent> lrcList = new ArrayList<lrcContent>(); //存放歌词列表对象  
	private int index = 0;          //歌词检索值 
	private String url;
	private int currentTime;
	private int duration;
	private Thread thread;
	private Handler mHandler;
	private final int msg_key = 0x1234;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏

		setContentView(R.layout.music_lrc);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏显示
		
		lrcView = (LrcView) findViewById(R.id.lrcShowView);
		url = getIntent().getStringExtra("url");
		
		initLrc();
		
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				/* 信息处理 */
				super.handleMessage(msg);
				switch (msg.what) {
				case msg_key:
					/* 处理时间事件 */
					lrcView.setIndex(lrcIndex());
					lrcView.invalidate();

					break;
				default:
					break;
				}
			}
		};
		thread = new Thread(this);
		thread.start();
	}
	
	
	
	
	
	public void initLrc(){
		mLrcProcess = new LrcProcess();
		//读取歌词文件
		mLrcProcess.readLRC(url);
		//传回处理后的歌词文件
		lrcList = mLrcProcess.getLrcList();
		lrcView.setmLrcList(lrcList);
		//切换带动画显示歌词
		lrcView.setAnimation(AnimationUtils.loadAnimation(lrc_activity.this,R.anim.alpha_z));
	}
	
	/**
	 * 根据时间获取歌词显示的索引值
	 * @return
	 */
	public int lrcIndex() {
		if(Playerservices.mediaPlayer.isPlaying()) {
			currentTime =Playerservices. mediaPlayer.getCurrentPosition();
			duration = Playerservices.mediaPlayer.getDuration();
		}
		if(currentTime < duration) {
			for (int i = 0; i < lrcList.size(); i++) {
				if (i < lrcList.size() - 1) {
					if (currentTime < lrcList.get(i).getLrcTime() && i == 0) {
						index = i;
					}
					if (currentTime > lrcList.get(i).getLrcTime()
							&& currentTime < lrcList.get(i + 1).getLrcTime()) {
						index = i;
					}
				}
				if (i == lrcList.size() - 1
						&& currentTime > lrcList.get(i).getLrcTime()) {
					index = i;
				}
			}
		}
		return index;
	}





	@Override
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
	
	
}
