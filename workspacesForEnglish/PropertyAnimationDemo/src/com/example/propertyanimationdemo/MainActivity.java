package com.example.propertyanimationdemo;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private final static String TAG = "com.example.propertyanimationdemo.MainActivity";
	
	private ObjectAnimator scaleXAnimator,translateXAnimator,alphaAnimator,translateAnimator;
	private AnimatorSet animatorScaleSet,animationSet;
	
	private ValueAnimator mValueAnimator;
	
	private Button buttonProValHolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		setupAnimator();
	}
	
	private void setupAnimator(){
		Button btnScaleX = (Button)findViewById(R.id.buttonScaleX);
		btnScaleX.setOnClickListener(this);
		scaleXAnimator = (ObjectAnimator)AnimatorInflater.loadAnimator(this, R.animator.scalex);
		scaleXAnimator.setTarget(btnScaleX);
		
		Button btnTranslateX = (Button)findViewById(R.id.buttonTranslateX);
		btnTranslateX.setOnClickListener(this);
		translateXAnimator = (ObjectAnimator)AnimatorInflater.loadAnimator(this, R.animator.translatex);
		translateXAnimator.setTarget(btnTranslateX);
		
		Button btnAlpha = (Button)findViewById(R.id.buttonAlpha);
		btnAlpha.setOnClickListener(this);
		alphaAnimator = (ObjectAnimator)AnimatorInflater.loadAnimator(this, R.animator.alpha);
		alphaAnimator.setTarget(btnAlpha);
		
		Button btnScale = (Button)findViewById(R.id.buttonScale);
		btnScale.setOnClickListener(this);
		animatorScaleSet = (AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.scale);
		animatorScaleSet.setTarget(btnScale);
	
		buttonProValHolder = (Button)findViewById(R.id.buttonProValHolder);
		buttonProValHolder.setOnClickListener(this);
		
		final Button buttonValueAnimator = (Button)findViewById(R.id.buttonValueAnimator);
		buttonValueAnimator.setOnClickListener(this);
		
		mValueAnimator = ValueAnimator.ofInt(1,100);
		mValueAnimator.addUpdateListener(new AnimatorUpdateListener() {			
			@Override
			public void onAnimationUpdate(ValueAnimator va) {
				float factor = va.getAnimatedFraction();
				MarginLayoutParams marginLayoutParams = (MarginLayoutParams) buttonValueAnimator.getLayoutParams(); 
				marginLayoutParams.leftMargin = (int)(factor * 100);
				buttonValueAnimator.setLayoutParams(marginLayoutParams);
			}
		});			
		mValueAnimator.setDuration(2000);
		mValueAnimator.setRepeatCount(1);
		mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
		mValueAnimator.setTarget(buttonValueAnimator);
		
		Button buttonSet = (Button) findViewById(R.id.buttonSet);
		buttonSet.setOnClickListener(this);
		animationSet = new AnimatorSet();
		animationSet.playTogether(
				ObjectAnimator.ofFloat(buttonSet, "alpha", 1,0,1),
				ObjectAnimator.ofFloat(buttonSet, "translationX", 0f,400f,0f),
				ObjectAnimator.ofFloat(buttonSet, "rotation", 0,180,360)
				);
		animationSet.setDuration(1000);
		animationSet.setTarget(buttonSet);
		animationSet.addListener(new AnimatorListener() {			
			@Override
			public void onAnimationStart(Animator arg0) {
				Log.v(TAG,"onAnimationStart");
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				Log.v(TAG,"onAnimationRepeat");
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				Log.v(TAG,"onAnimationEnd");
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				Log.v(TAG,"onAnimationCancel");
			}			
		});
		
		PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("translationX",0f,300f);
		PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("translationY",0f,300f);		
		translateAnimator = ObjectAnimator.ofPropertyValuesHolder(buttonProValHolder, pvhX,pvhY);
		translateAnimator.setDuration(2000);
		
		Button buttonViewPropAnimator = (Button) findViewById(R.id.buttonViewPropAnimator);
		buttonViewPropAnimator.setOnClickListener(this);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.buttonAlpha:
			alphaAnimator.start();
			break;
		case R.id.buttonScale:
			animatorScaleSet.start();
			break;
		case R.id.buttonScaleX:
			scaleXAnimator.start();
			break;
		case R.id.buttonTranslateX:
			translateXAnimator.start();
			break;
		case R.id.buttonProValHolder:							
			translateAnimator.start();								
			break;
		case R.id.buttonSet:
			animationSet.start();
			break;
		case R.id.buttonValueAnimator:
			mValueAnimator.start();
			break;
		case R.id.buttonViewPropAnimator:
			v.animate().translationX(100f).alpha(0).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animator){
					final Button button = (Button) findViewById(R.id.buttonViewPropAnimator);
					button.animate().alpha(1).translationX(0f).start();
				}
			}).start();
			break;			
		}	
	}
}
