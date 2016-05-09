package com.baiyi.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MytextView extends View {
	
	private static final String TAG = MytextView.class.getSimpleName();
	private String text;
	private int textAttr;
	
	public MytextView(Context context){
		super(context);
	}
	
	public MytextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MytextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MytextView,defStyle,0);
		int N = ta.getIndexCount();
		
		for(int i = 0;i<N;i++){
			int attr = ta.getIndex(i);
			switch (attr) {
			case R.styleable.MytextView_testAttr:
				text = ta.getString(R.styleable.MytextView_testAttr);
				break;
			case R.styleable.MytextView_text:
				textAttr = ta.getInteger(R.styleable.MytextView_text, -1);
			default:
				break;
			}
		}
       
        ta.recycle();
	}
	
	@Override    
	   protected void onDraw(Canvas canvas)   
	    {    
	        super.onDraw(canvas);    
	        Log.e(TAG, "text = " + text + " , textAttr = " + textAttr);
	   }    

}
