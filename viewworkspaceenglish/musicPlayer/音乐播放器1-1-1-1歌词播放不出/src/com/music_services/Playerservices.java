package com.music_services;

import com.music_interface.AppConstant;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;

public class Playerservices extends Service {
	public static MediaPlayer mediaPlayer = new MediaPlayer();
	private String path;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public int onStartCommand(Intent intent,int flags,int startId){
		
        path = intent.getStringExtra("url");          
        int msg = intent.getIntExtra("MSG",0);  
        if(msg == AppConstant.PlayerMsg.PLAY_MSG) {  
            play(0);  //从头开始播放
        } else if(msg == AppConstant.PlayerMsg.PAUSE_MSG) {  
            pause();  //暂停播放
        } else if(msg == AppConstant.PlayerMsg.CONTINUE_MSG){
        	play_continue();//继续播放
        }
		
        return super.onStartCommand(intent, flags, startId); 
		}
	
	//播放音乐
	private void play(int position) {  
        try {  
            mediaPlayer.reset();//把各项参数恢复到初始状态  
            mediaPlayer.setDataSource(path);  
            
            mediaPlayer.prepare();  //进行缓冲  
            mediaPlayer.setOnPreparedListener(new PreparedListener(position));//注册一个监听器  
        }  
        catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	private void play_continue()
	{
		mediaPlayer.start();
	}
	
	/** 
     * 暂停音乐 
     */  
    private void pause() {  
    	
    	mediaPlayer.pause(); 
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {  
            mediaPlayer.pause();  
        }  
    }  
    @Override  
    public void onDestroy() {  
        if(mediaPlayer != null){  
            mediaPlayer.stop();  
            mediaPlayer.release();  
        }  
    }  
    /** 
     *  
     * 实现一个OnPrepareLister接口,当音乐准备好的时候开始播放 
     * 
     */  
    private final class PreparedListener implements OnPreparedListener {  
        private int positon;  
          
        public PreparedListener(int positon) {  
            this.positon = positon;  
        }  
          
        @Override  
        public void onPrepared(MediaPlayer mp) {  
            mediaPlayer.start();    //开始播放  
            if(positon > 0) {    //如果音乐不是从头播放  
                mediaPlayer.seekTo(positon);  
            }  
        }  
    }
    
}
