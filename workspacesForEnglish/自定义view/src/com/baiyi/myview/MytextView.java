package com.baiyi.myview;

import com.baiyi.mainactivity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MytextView extends View{

	private static final String TAG = MytextView.class.getSimpleName();

	public MytextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.mytextview);

        String text = ta.getString(R.styleable.mytextview_testAttr);
        int textAttr = ta.getInteger(R.styleable.mytextview_text, -1);
        Log.e(TAG, "text = " + text + " , textAttr = " + textAttr);
        ta.recycle();
		
	}
	
}
