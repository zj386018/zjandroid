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
            play(0);  //��ͷ��ʼ����
        } else if(msg == AppConstant.PlayerMsg.PAUSE_MSG) {  
            pause();  //��ͣ����
        } else if(msg == AppConstant.PlayerMsg.CONTINUE_MSG){
        	play_continue();//��������
        }
		
        return super.onStartCommand(intent, flags, startId); 
		}
	
	//��������
	private void play(int position) {  
        try {  
            mediaPlayer.reset();//�Ѹ�������ָ�����ʼ״̬  
            mediaPlayer.setDataSource(path);  
            
            mediaPlayer.prepare();  //���л���  
            mediaPlayer.setOnPreparedListener(new PreparedListener(position));//ע��һ��������  
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
     * ��ͣ���� 
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
     * ʵ��һ��OnPrepareLister�ӿ�,������׼���õ�ʱ��ʼ���� 
     * 
     */  
    private final class PreparedListener implements OnPreparedListener {  
        private int positon;  
          
        public PreparedListener(int positon) {  
            this.positon = positon;  
        }  
          
        @Override  
        public void onPrepared(MediaPlayer mp) {  
            mediaPlayer.start();    //��ʼ����  
            if(positon > 0) {    //������ֲ��Ǵ�ͷ����  
                mediaPlayer.seekTo(positon);  
            }  
        }  
    }
    
}
