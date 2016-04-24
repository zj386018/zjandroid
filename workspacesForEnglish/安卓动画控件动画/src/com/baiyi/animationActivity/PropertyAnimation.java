package com.baiyi.animationActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PropertyAnimation extends Activity {

	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_propertyanimation);
		button = (Button) findViewById(R.id.bt_property);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
	//------------���ƿؼ���͸���Ƚ���Ч��
//				ObjectAnimator oa=ObjectAnimator.ofFloat(arg0, "alpha", 0f, 1f);
	//------------���ƿռ��λ��
				ObjectAnimator oa=ObjectAnimator.ofFloat(arg0, "translationX", 0f,400f,400f);
				oa=ObjectAnimator.ofFloat(arg0, "translationY", 0f,400f,400f);
			oa.setDuration(3000);
			oa.start();
			}
		});
		
	}
	
}
