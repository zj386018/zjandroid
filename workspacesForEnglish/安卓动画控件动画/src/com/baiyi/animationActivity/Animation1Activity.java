package com.baiyi.animationActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class Animation1Activity extends Activity {

	private Button rotateButton = null;
	private Button scaleButton = null;
	private Button alphaButton = null;
	private Button translateButton = null;
	private ImageView image = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animations);
		rotateButton = (Button) findViewById(R.id.rotateButton);
		scaleButton = (Button) findViewById(R.id.scaleButton);
		alphaButton = (Button) findViewById(R.id.alphaButton);
		translateButton = (Button) findViewById(R.id.translateButton);
		image = (ImageView) findViewById(R.id.image);

		rotateButton.setOnClickListener(new RotateButtonListener());
		scaleButton.setOnClickListener(new ScaleButtonListener());
		alphaButton.setOnClickListener(new AlphaButtonListener());
		translateButton.setOnClickListener(new TranslateButtonListener());

	}

	/**
	 *͸�����䶯�� 
	 */
	class AlphaButtonListener implements OnClickListener {
		public void onClick(View v) {
			// ����һ��AnimationSet���󣬲���ΪBoolean�ͣ�
			// true��ʾʹ��Animation��interpolator��false����ʹ���Լ���
			AnimationSet animationSet = new AnimationSet(true);
			// ����һ��AlphaAnimation���󣬲�������ȫ��͸���ȣ�����ȫ�Ĳ�͸��
			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
			// ���ö���ִ�е�ʱ��
			alphaAnimation.setDuration(1000);
			// ��alphaAnimation������ӵ�AnimationSet����
			animationSet.addAnimation(alphaAnimation);
			// ʹ��ImageView��startAnimation����ִ�ж���
			image.startAnimation(animationSet);
		}
	}

	/**
	 * ��ת����
	 */
	class RotateButtonListener implements OnClickListener {
		public void onClick(View v) {
			WindowManager manager = Animation1Activity.this.getWindowManager();
			DisplayMetrics outMetrics = new DisplayMetrics();
			manager.getDefaultDisplay().getMetrics(outMetrics);
			int width = outMetrics.widthPixels;
			int height = outMetrics.heightPixels;
			int imgWidth = image.getWidth();
			
			AnimationSet animationSet = new AnimationSet(true);

			//������������Ϊ�����ͼƬ�������Ͻ������X��ƫ������Y��ƫ����
			RotateAnimation rotateAnimation = new RotateAnimation(0, 360, width/2-imgWidth/2, 0);
			
			// ����1�����ĸ���ת�Ƕȿ�ʼ
			// ����2��ת��ʲô�Ƕ�
			// ��4��������������Χ������ת��Բ��Բ��������
			// ����3��ȷ��x����������ͣ���ABSOLUT�������ꡢRELATIVE_TO_SELF������������ꡢRELATIVE_TO_PARENT����ڸ��ؼ�������
			// ����4��x���ֵ��0.5f����������������ؼ���һ�볤��Ϊx��
			// ����5��ȷ��y�����������
			// ����6��y���ֵ��0.5f����������������ؼ���һ�볤��Ϊy��
//			RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
//					Animation.RELATIVE_TO_SELF, 0.5f,
//					Animation.RELATIVE_TO_SELF, 0.5f);
			rotateAnimation.setDuration(10000);
			animationSet.addAnimation(rotateAnimation);
			image.startAnimation(animationSet);
		}
	}

	/**
	 *	����Ч������ 
	 */
	class ScaleButtonListener implements OnClickListener {
		public void onClick(View v) {
			AnimationSet animationSet = new AnimationSet(true);
			// ����1��x��ĳ�ʼֵ
			// ����2��x���������ֵ
			// ����3��y��ĳ�ʼֵ
			// ����4��y���������ֵ
			// ����5��ȷ��x�����������
			// ����6��x���ֵ��0.5f����������������ؼ���һ�볤��Ϊx��
			// ����7��ȷ��y�����������
			// ����8��y���ֵ��0.5f����������������ؼ���һ�볤��Ϊy��
			ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0,
					1f, Animation.RELATIVE_TO_SELF, 1f,
					Animation.RELATIVE_TO_SELF, 1f);
			scaleAnimation.setDuration(1000);
			animationSet.addAnimation(scaleAnimation);
			image.startAnimation(animationSet);
		}
	}

	/**
	 * �ƶ�Ч������
	 */
	class TranslateButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			AnimationSet animationSet = new AnimationSet(true);
			// ����1��2��x��Ŀ�ʼλ��
			// ����3��4��y��Ŀ�ʼλ��
			// ����5��6��x��Ľ���λ��
			// ����7��8��x��Ľ���λ��
			TranslateAnimation translateAnimation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
					0.5f, Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			translateAnimation.setDuration(1000);
			animationSet.addAnimation(translateAnimation);
			image.startAnimation(animationSet);
		}

	}

}
