package com.baiyi.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity implements OnClickListener {

	Button bt_start1;
	Button bt_start2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		initView();
	}

	private void initView() {
		bt_start1 = (Button) findViewById(R.id.bt_start1);
		bt_start1.setOnClickListener(this);
		bt_start2 = (Button) findViewById(R.id.bt_start2);
		bt_start2.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		Intent intent;
		switch (view.getId()) {
		case R.id.bt_start1:
			intent = new Intent(StartActivity.this,MainActivity.class);
			startActivity(intent);
			break;
		case R.id.bt_start2:
			intent = new Intent(StartActivity.this,ViewPager2Activity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
