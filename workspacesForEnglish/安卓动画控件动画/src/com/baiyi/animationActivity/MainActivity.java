package com.baiyi.animationActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button bt_animations;// 第一种动画界面的Button按钮
	Button bt_propertyAnimation;// 第一种动画界面的Button按钮
	Button bt_property;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initAnimationsButtonView();
		initPropertyAnimationButtonView();
	}
	
	
	public void initAnimationsButtonView() {
		bt_animations = (Button) findViewById(R.id.main_bt_animations);
		bt_animations.setOnClickListener(this);
	}
	//属性动画
	public void initPropertyAnimationButtonView(){
		bt_propertyAnimation = (Button) findViewById(R.id.main_bt_propertyAnimation);
		bt_propertyAnimation.setOnClickListener(this);
	
		bt_property = (Button) findViewById(R.id.main_bt_property);
		bt_property.setOnClickListener(this);
	}

	/**
	 * button的点击事件监听
	 */
	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.main_bt_animations:
			Intent intent1 = new Intent(MainActivity.this,
					Animation1Activity.class);
			startActivity(intent1);
			break;
		case R.id.main_bt_propertyAnimation:
			Intent intent2 = new Intent(MainActivity.this,
					PropertyAnimation.class);
			startActivity(intent2);
			break;
		case R.id.main_bt_property:
			Intent intent3 = new Intent(MainActivity.this,
					Property.class);
			startActivity(intent3);
			break;

		default:
			break;
		}

	}

}
