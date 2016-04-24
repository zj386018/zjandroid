package com.baiyi.util;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimUtil {
	
	//����ͳ�ƶ����Ƿ���ִ�У�����Ϊ0�����ʾ�ж�����ִ�У�Ϊ0����ʾû�ж�����ִ�С�
	public static int animCount = 0;
	

	/************************
	 *      �رղ˵�����
	 *      ������ϼ��˵��ĵ����ťʱ����һ����Ĳ˵���ת��ʧ�Ķ�����       
	 ************************/
	public static void closeMenu(RelativeLayout rl,int startOffset){
		
		//����layout�������ӿؼ������������ǵĿɵ��Ϊfalse��
		for(int i = 0;i<rl.getChildCount();i++){
			rl.getChildAt(i).setEnabled(false);
		}
		
		//���û����������ת��
		RotateAnimation animation = new RotateAnimation(0, -180, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,//���ں���X�ϻ���������λ�õİٷֱ�
				RotateAnimation.RELATIVE_TO_SELF, 1);//��������Y�ϻ���������λ�õİٷֱ�
		//����ʱ��
		animation.setDuration(500);
		animation.setFillAfter(true);//�ö��������󣬿ؼ����ֽ������״̬
		animation.setStartOffset(startOffset);//���ö������ӳ�ִ�У����ⲿ����startOffset
		animation.setAnimationListener(new MyAnimationListener());
		
		rl.startAnimation(animation);
	}

	
	/************************
	 *      �򿪲˵�����      * 
	 *      ������ϼ��˵��ĵ����ťʱ����һ����Ĳ˵���ת�����Ķ�����
	 ************************/
	public static void showMenu(RelativeLayout rl,int startOffset){
		
		//����layout�������ӿؼ������������ǵĿɵ��Ϊtrue��
		for(int i = 0; i<rl.getChildCount();i++){
			rl.getChildAt(i).setEnabled(true);
		}
		
		//���û����������ת��
		RotateAnimation animation = new RotateAnimation(-180, 0, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,//���ں���X�ϻ���������λ�õİٷֱ�
				RotateAnimation.RELATIVE_TO_SELF, 1);//��������Y�ϻ���������λ�õİٷֱ�
		//����ʱ��
		animation.setDuration(500);
		animation.setFillAfter(true);//�ö��������󣬿ؼ����ֽ������״̬
		animation.setStartOffset(startOffset);
		animation.setAnimationListener(new MyAnimationListener());

		rl.startAnimation(animation);
	}
	
	static class MyAnimationListener implements AnimationListener{

		/**
		 * ������ʼʱ�Զ����ø÷�����
		 */
		@Override
		public void onAnimationStart(Animation arg0) {
			animCount++;
			
		}

		/**
		 * ��������ʱ�Զ����ø÷�����
		 */
		@Override
		public void onAnimationEnd(Animation arg0) {
			animCount--;
		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
		}
		
	}
	

}
