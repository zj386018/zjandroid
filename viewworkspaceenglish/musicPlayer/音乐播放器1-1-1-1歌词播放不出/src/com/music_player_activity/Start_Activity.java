package com.music_player_activity;



import com.music_player.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

public class Start_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        
        setContentView(R.layout.activity_start);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示
        
    	new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent();
				i.setClass(Start_Activity.this,Main_activity.class);
				startActivity(i);
//				overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
				Start_Activity.this.finish();
			}
		},1000);
       
    	
    }
}
