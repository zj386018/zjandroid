package com.baiyi.util;

import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimUtil {

	/**
	 * 
	 * @param rl
	 */
	public static void closeMenu(RelativeLayout rl){
		
		//设置基于自身的旋转点
		RotateAnimation animation = new RotateAnimation(0, -180, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,//点在横轴X上基于自身长度位置的百分比
				RotateAnimation.RELATIVE_TO_SELF, 1);//点在纵轴Y上基于自身长度位置的百分比
		//设置时间
		animation.setDuration(500);
		rl.startAnimation(animation);
		
		
	}

}
