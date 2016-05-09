package com.baiyi.animationActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyProperty extends Activity {

	Button button_propertyXY;
	int count_translation = 0;

	Button button_propertyRotationXY;
	int count_Roatation = 0;

	// int bt_width = 0;
	// int bt_left = 0;
	// int bt_height = 0;
	// int bt_top = 0;
	// int win_width = 0;
	// int win_height = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_propertyanimation);
		// 获取屏幕的 宽 width 和 高 height
		// WindowManager wm = this.getWindowManager();
		// win_width = wm.getDefaultDisplay().getWidth();
		// win_height = wm.getDefaultDisplay().getHeight();

		button_propertyXY = (Button) findViewById(R.id.bt_propertyTranslationXY);
		button_propertyXY.setOnClickListener(new Bt_OnClickListener());

		button_propertyRotationXY = (Button) findViewById(R.id.bt_propertyRotationXY);
		button_propertyRotationXY.setOnClickListener(new Bt_OnClickListener());

	}

	class Bt_OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// ------------控制控件的透明度渐变效果
			// ObjectAnimator oa=ObjectAnimator.ofFloat(arg0, "alpha", 0f, 1f);
			switch (arg0.getId()) {
			case R.id.bt_propertyTranslationXY:// 属性动画平移
				animation_bt_propertyTranslationXY(arg0);
				break;
			case R.id.bt_propertyRotationXY:// 属性动画旋转
				animation_bt_propertyRotationXY(arg0);
				break;

			default:
				break;
			}

		}

	}

	
	/**
	 * 属性动画控件旋转
	 * @param arg0
	 */
	private void animation_bt_propertyRotationXY(View arg0) {
		ObjectAnimator oa = null;
		switch (count_Roatation % 3) {
		case 0:
			oa = ObjectAnimator.ofFloat(arg0, "rotation", 0f, 360f);
			break;
		case 1:
			oa = ObjectAnimator.ofFloat(arg0, "rotationX", 0f, 400f, 400f);
			break;
		case 2:
			oa = ObjectAnimator.ofFloat(arg0, "rotationY", 0f, 400f, 400f);
			break;
		default:
			break;
		}
		count_Roatation++;
		oa.setDuration(3000);
		oa.setRepeatCount(Integer.MAX_VALUE);
		oa.start();
		
	}

	/**
	 * 属性动画控件平行移动
	 */
	private void animation_bt_propertyTranslationXY(View arg0) {
		// ------------控制空间的位移
		ObjectAnimator oa = null;
		switch (count_translation % 4) {
		case 0:
			oa = ObjectAnimator.ofFloat(arg0, "translationX", 0,350,250,320,290,300);
			break;
		case 1:
			oa = ObjectAnimator.ofFloat(arg0, "translationY", 0f, 400f, 400f);
			break;
		case 2:
			oa = ObjectAnimator.ofFloat(arg0, "translationX", 400f, 0f, 0f);
			break;
		case 3:
			oa = ObjectAnimator.ofFloat(arg0, "translationY", 400f, 0f, 0f);
			break;
		default:
			break;
		}
		count_translation++;
		oa.setDuration(3000);
	
		oa.start();
	}

}
