package com.baiyi.animationActivity;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

public class ValueAnimator_Activity extends Activity implements OnTouchListener{

	private int width;
	private int height;
	private Button bt_valueanimator;
	private float touchX;
	private float touchY;
	private float upX;
	private float upY;
	private float x;
	private float y;
	private int btleft;
	private int bttop;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_valueanimator);
		// 获取屏幕的 宽 width 和 高 height
		WindowManager wm = this.getWindowManager();
		width = wm.getDefaultDisplay().getWidth();
		height = wm.getDefaultDisplay().getHeight();
		

		bt_valueanimator = (Button) findViewById(R.id.bt_valueanimator);
		bt_valueanimator.setOnTouchListener(this);
		btleft = bt_valueanimator.getLeft();
		bttop = bt_valueanimator.getTop();
	}
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchX = event.getX();
			touchY = event.getY();
			
			x = touchX - btleft;
			y = touchY - bttop;
			
			break;
		case MotionEvent.ACTION_MOVE:
			moveViewByLayout(bt_valueanimator, (int) event.getRawX(),
					(int) event.getRawY());
			break;
		case MotionEvent.ACTION_UP:
			upX = event.getX();
			upY = event.getY();
			if((upX-touchX)*(upX-touchX)<2&&(upY - touchY)*(upY - touchY)<2){
			moveCirclePath();}
			break;
		}
		return true;
	}

	/**
	 * 圆形运行
	 */
	protected void moveCirclePath() {
		final int R = width / 4;
		ValueAnimator tValue = ValueAnimator.ofFloat(0,
				(float) (2.0f * Math.PI));
		tValue.setDuration(1000);
		tValue.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// 圆的参数方程 x = R*sin(t) y = R*cos(t)
				float t = (Float) animation.getAnimatedValue();
				int x = (int) (R * Math.sin(t) + width / 2);
				int y = (int) (R * Math.cos(t) + height / 2);
				System.out.println("debug:(x,y) = " + x + "," + y);
				moveViewByLayout(bt_valueanimator, x, y);
			}
		});
		tValue.setInterpolator(new DecelerateInterpolator());
		tValue.start();
	}

	/**
	 * 修改view的位置
	 * 
	 */
	private void moveViewByLayout(View view, int rawX, int rawY) {

//		int left = rawX - bt_valueanimator.getWidth() / 2;
//		int top = rawY - bt_valueanimator.getHeight()*2;
	
		int left = (int) (rawX - x);
		int top = (int) (rawY - y);
		
		int width = left + view.getWidth();
		int height = top + view.getHeight();
		view.layout(left, top, width, height);
	}

}
