package com.baiyi.mainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Timer timer = new Timer();
	private String dtStr;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView = (TextView) findViewById(R.id.tv_timer);
        timer.schedule(timerTask,1000,1000);
    }
    
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			if(msg.what==1){
				textView.setText(dtStr);
			}
		};
	};
	
	
	TimerTask timerTask = new TimerTask() {
		
		@Override
		public void run() {
			dtStr = dtf.format(new Date());
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};

}
