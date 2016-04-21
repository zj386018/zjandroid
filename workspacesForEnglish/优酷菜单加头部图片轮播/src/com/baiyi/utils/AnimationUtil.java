package com.baiyi.utils;

import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimationUtil{
	
	public static int account = 0;
	
	public static void showMenu(RelativeLayout rl,int startOffset){
		
		RotateAnimation animation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF,0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 1);
		
		animation.setDuration(500);
		animation.setFillAfter(true);
		animation.setStartOffset(startOffset);
		rl.startAnimation(animation);
		
	}

	public static void closeMenu(RelativeLayout rl,int startOffset){
		RotateAnimation animation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF,0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 1);
		
		animation.setDuration(500);
		animation.setFillAfter(true);
		animation.setStartOffset(startOffset);
		rl.startAnimation(animation);
		
		
	}
}
