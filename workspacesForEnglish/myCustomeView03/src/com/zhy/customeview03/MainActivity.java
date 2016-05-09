package com.zhy.customeview03;

import com.zhy.customeview03.view.CustomProgressBar;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
	private Button start;
	private Button pause;
	private CustomProgressBar bar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initListener();
	}
	
	private void init(){
		start = (Button) findViewById(R.id.bt_start);
		pause = (Button) findViewById(R.id.bt_pause);
		bar = (CustomProgressBar) findViewById(R.id.myprogressbar);
	}
	
	private void initListener(){
		start.setOnClickListener(this);
		pause.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		
		switch (arg0.getId()) {
		case R.id.bt_start:
			bar.start();
			break;
		case R.id.bt_pause:
			bar.pause();
			break;

		default:
			break;
		}
		
	}
	
}
