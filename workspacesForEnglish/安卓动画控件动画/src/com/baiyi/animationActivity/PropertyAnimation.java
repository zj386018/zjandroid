package com.baiyi.animationActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * Demo����:
 * <a href="http://www.it165.net/pro/ydad/" target="_blank" class="keylink">Android</a>�����Զ���Property Animationʹ��ʾ��(һ)
 */
public class PropertyAnimation extends Activity{

	
		private Button mScaleXButton;
		private Button mScaleXYButton;
		private Button mTranslateXButton;
		private Button mAlphaButton;
		private Button mAnimatorSetByCodesButton;
		private Button mPropertyValuesHolderButton;
		private Button mViewPropertyAnimatorButton;
		private ObjectAnimator mObjectAnimator1;
		private ObjectAnimator mObjectAnimator2;
		private ObjectAnimator mObjectAnimator3;
		private AnimatorSet mAnimatorSet1;
		private AnimatorSet mAnimatorSet2;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_property);
	        init();
	    }
	    
	    private void init(){
	    	//--------->����Ϊ��X�����Ͻ������ŵ����Զ���
	    	mScaleXButton=(Button) findViewById(R.id.scaleXButton);
	    	mScaleXButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mObjectAnimator1.start();
					
				}
			});
	    	mObjectAnimator1=(ObjectAnimator)AnimatorInflater.loadAnimator(this, R.animator.scalexanimator); 
	    	mObjectAnimator1.setTarget(mScaleXButton);
	    	
	    	
	    	//--------->����Ϊ��X��Y�����Ͻ������ŵ����Զ���
	    	mScaleXYButton=(Button) findViewById(R.id.scaleXYButton);
	    	mScaleXYButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mAnimatorSet1.start();
					
				}
			});
	    	mAnimatorSet1=(AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.setanimator); 
	    	mAnimatorSet1.setTarget(mScaleXYButton);
	    	
	    	
	    	//--------->����Ϊ��X�����Ͻ���λ�Ƶ����Զ���
	    	mTranslateXButton=(Button) findViewById(R.id.translateXButton);
	    	mTranslateXButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mObjectAnimator2.start();
					
				}
			});
	    	mObjectAnimator2=(ObjectAnimator)AnimatorInflater.loadAnimator(this, R.animator.translatexanimator); 
	    	mObjectAnimator2.setTarget(mTranslateXButton);
	    	
	    	
	    	//--------->����Ϊ͸���ȱ仯�����Զ���
	    	mAlphaButton=(Button) findViewById(R.id.alphaButton);
	    	mAlphaButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mObjectAnimator3.start();
					
				}
			});
	    	mObjectAnimator3=(ObjectAnimator)AnimatorInflater.loadAnimator(this, R.animator.alphaanimator); 
	    	mObjectAnimator3.setTarget(mAlphaButton);
	    	
	    	
	    	//--------->����Ϊ�ڴ�����ʵ��AnimatorSet
	    	mAnimatorSetByCodesButton=(Button) findViewById(R.id.animatorSetByCodesButton);
	    	mAnimatorSetByCodesButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mAnimatorSet2.start();
					
				}
			});
	    	mAnimatorSet2=new AnimatorSet();
	    	//���ö���
	    	mAnimatorSet2.playTogether(
	    			ObjectAnimator.ofFloat(mAnimatorSetByCodesButton, "alpha", 1,0,1),
	    			ObjectAnimator.ofFloat(mAnimatorSetByCodesButton, "translationX", 0f,400f,0f),
	    			ObjectAnimator.ofFloat(mAnimatorSetByCodesButton, "translationY", 0f,400f,0f),
	    			ObjectAnimator.ofFloat(mAnimatorSetByCodesButton, "rotation", 0,180,360)
	    	);
	    	//���ö���ʱ��
	    	mAnimatorSet2.setDuration(2000);
	    	//���ö�������
	    	mAnimatorSet2.addListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animator) {
					System.out.println("---> onAnimationStart");
				}
				
				@Override
				public void onAnimationRepeat(Animator animator) {
					System.out.println("---> onAnimationRepeat");
				}
				
				@Override
				public void onAnimationEnd(Animator animator) {
					System.out.println("---> onAnimationEnd");
				}
				
				@Override
				public void onAnimationCancel(Animator animator) {
					System.out.println("---> onAnimationCancel");
				}
			});
	    	
	    	
	    	//�ڴ�����Ҫʵ��һ������ͬ���ԵĶ���Ч��,����AnimatorSet��
	    	//��������PropertyValuesHolder��ViewPropertyAnimator������ʵ��
	    	//--------->����Ϊ�ڴ���������PropertyValuesHolderʵ������AnimatorSet��Ч��
	    	mPropertyValuesHolderButton=(Button) findViewById(R.id.propertyValuesHolderButton);
	    	PropertyValuesHolder propertyValuesHolderX=PropertyValuesHolder.ofFloat("translationX", 0f,200f);
	    	PropertyValuesHolder propertyValuesHolderY=PropertyValuesHolder.ofFloat("translationY", 0f,200f);
	    	final ObjectAnimator objectAnimator
	    	=ObjectAnimator.ofPropertyValuesHolder(mPropertyValuesHolderButton, propertyValuesHolderX, propertyValuesHolderY);
	    	objectAnimator.setDuration(2000);
	    	mPropertyValuesHolderButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					objectAnimator.start();
				}
			});
	    	
	    	//�ڴ�����Ҫʵ��һ������ͬ���ԵĶ���Ч��,����AnimatorSet��
	    	//��������PropertyValuesHolder��ViewPropertyAnimator������ʵ��
	    	//--------->����Ϊ�ڴ���������ViewPropertyAnimatorʵ������AnimatorSet��Ч��
	    	mViewPropertyAnimatorButton=(Button) findViewById(R.id.viewPropertyAnimatorButton);
	    	mViewPropertyAnimatorButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(final View v) {
					ViewPropertyAnimator viewPropertyAnimator=mViewPropertyAnimatorButton.animate();
					//λ��
			    	viewPropertyAnimator.translationX(100f);
			    	//͸����
			    	viewPropertyAnimator.alpha(0.5f);
			    	//����
			    	viewPropertyAnimator.setListener(new AnimatorListenerAdapter() {
			    		@Override
						public void onAnimationStart(Animator animation) {
							super.onAnimationStart(animation);
						}
			    		@Override
						public void onAnimationCancel(Animator animation) {
							super.onAnimationCancel(animation);
						}

						@Override
						public void onAnimationRepeat(Animator animation) {
							super.onAnimationRepeat(animation);
						}
						@Override
			    		public void onAnimationEnd(Animator animator){
							//������������͸���Ⱥ�λ�û�ԭ
							v.animate().alpha(1).translationX(0);
			    		}
			    	});
					viewPropertyAnimator.start();
					
				}
			});
	    	
	    }
	}