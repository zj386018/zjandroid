package com.baiyi.util;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimUtil {
	
	//用于统计动画是否在执行，若不为0，则表示有动画在执行；为0，表示没有动画在执行。
	public static int animCount = 0;
	

	/************************
	 *      关闭菜单动画
	 *      当点击上级菜单的点击按钮时，下一级别的菜单旋转消失的动画。       
	 ************************/
	public static void closeMenu(RelativeLayout rl,int startOffset){
		
		//遍历layout中所有子控件，并设置它们的可点击为false。
		for(int i = 0;i<rl.getChildCount();i++){
			rl.getChildAt(i).setEnabled(false);
		}
		
		//设置基于自身的旋转点
		RotateAnimation animation = new RotateAnimation(0, -180, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,//点在横轴X上基于自身长度位置的百分比
				RotateAnimation.RELATIVE_TO_SELF, 1);//点在纵轴Y上基于自身长度位置的百分比
		//设置时间
		animation.setDuration(500);
		animation.setFillAfter(true);//让动画结束后，控件保持结束后的状态
		animation.setStartOffset(startOffset);//设置动画的延迟执行，由外部传入startOffset
		animation.setAnimationListener(new MyAnimationListener());
		
		rl.startAnimation(animation);
	}

	
	/************************
	 *      打开菜单动画      * 
	 *      当点击上级菜单的点击按钮时，下一级别的菜单旋转出来的动画。
	 ************************/
	public static void showMenu(RelativeLayout rl,int startOffset){
		
		//遍历layout中所有子控件，并设置它们的可点击为true。
		for(int i = 0; i<rl.getChildCount();i++){
			rl.getChildAt(i).setEnabled(true);
		}
		
		//设置基于自身的旋转点
		RotateAnimation animation = new RotateAnimation(-180, 0, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,//点在横轴X上基于自身长度位置的百分比
				RotateAnimation.RELATIVE_TO_SELF, 1);//点在纵轴Y上基于自身长度位置的百分比
		//设置时间
		animation.setDuration(500);
		animation.setFillAfter(true);//让动画结束后，控件保持结束后的状态
		animation.setStartOffset(startOffset);
		animation.setAnimationListener(new MyAnimationListener());

		rl.startAnimation(animation);
	}
	
	static class MyAnimationListener implements AnimationListener{

		/**
		 * 动画开始时自动调用该方法。
		 */
		@Override
		public void onAnimationStart(Animation arg0) {
			animCount++;
			
		}

		/**
		 * 动画结束时自动调用该方法。
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
