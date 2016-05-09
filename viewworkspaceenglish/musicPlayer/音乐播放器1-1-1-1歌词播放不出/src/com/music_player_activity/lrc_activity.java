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
	private LrcProcess mLrcProcess; //��ʴ���  
	private List<lrcContent> lrcList = new ArrayList<lrcContent>(); //��Ÿ���б����  
	private int index = 0;          //��ʼ���ֵ 
	private String url;
	private int currentTime;
	private int duration;
	private Thread thread;
	private Handler mHandler;
	private final int msg_key = 0x1234;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������

		setContentView(R.layout.music_lrc);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ȫ����ʾ
		
		lrcView = (LrcView) findViewById(R.id.lrcShowView);
		url = getIntent().getStringExtra("url");
		
		initLrc();
		
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				/* ��Ϣ���� */
				super.handleMessage(msg);
				switch (msg.what) {
				case msg_key:
					/* ����ʱ���¼� */
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
		//��ȡ����ļ�
		mLrcProcess.readLRC(url);
		//���ش����ĸ���ļ�
		lrcList = mLrcProcess.getLrcList();
		lrcView.setmLrcList(lrcList);
		//�л���������ʾ���
		lrcView.setAnimation(AnimationUtils.loadAnimation(lrc_activity.this,R.anim.alpha_z));
	}
	
	/**
	 * ����ʱ���ȡ�����ʾ������ֵ
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
