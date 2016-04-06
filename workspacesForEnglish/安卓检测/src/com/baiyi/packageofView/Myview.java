package com.baiyi.packageofView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Myview extends View {

	/**
	 * 若该view需要在Java代码中new出来，需要通过该构造方法来实现
	 * @param context
	 */
	public Myview(Context context) {
		super(context);
		
	}
	
	/** 
	 * 如果该view只是在布局文件中使用，只要重写这个构造方法
	 * @param context
	 * @param attrs
	 */
	public Myview(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	/**
	 * 绘制布局
	 */
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
	}


	/**
	 * 测量屏幕
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}
	
	
	
}

