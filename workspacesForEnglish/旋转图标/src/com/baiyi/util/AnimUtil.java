package com.baiyi.util;

import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimUtil {

	/**
	 * 
	 * @param rl
	 */
	public static void closeMenu(RelativeLayout rl){
		
		//���û����������ת��
		RotateAnimation animation = new RotateAnimation(0, -180, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,//���ں���X�ϻ���������λ�õİٷֱ�
				RotateAnimation.RELATIVE_TO_SELF, 1);//��������Y�ϻ���������λ�õİٷֱ�
		//����ʱ��
		animation.setDuration(500);
		rl.startAnimation(animation);
		
		
	}

}
