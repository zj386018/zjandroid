package com.baiyi.packageofView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Myview extends View {

	/**
	 * ����view��Ҫ��Java������new��������Ҫͨ���ù��췽����ʵ��
	 * @param context
	 */
	public Myview(Context context) {
		super(context);
		
	}
	
	/** 
	 * �����viewֻ���ڲ����ļ���ʹ�ã�ֻҪ��д������췽��
	 * @param context
	 * @param attrs
	 */
	public Myview(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	/**
	 * ���Ʋ���
	 */
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
	}


	/**
	 * ������Ļ
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}
	
	
	
}

