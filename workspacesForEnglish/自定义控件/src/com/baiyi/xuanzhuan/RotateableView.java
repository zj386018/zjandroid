package com.baiyi.xuanzhuan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.baiyi.test.R;

public class RotateableView extends View{  
    /** 
     * �������� 
     */  
    private final String namespace = "http://com.bill.cn";   
    /** 
     * ���洴����ת�Ƕ� 
     */  
    private float mRotateDegrees;   
    /** 
     * ���洴������ͼƬ��ID 
     */  
    private int mBackGroudDrawableId;  
    /** 
     * ����ͼƬID����ͼƬ 
     */  
    private Drawable mBackGroudDrawable;  
    /** 
     * ԭʼͼƬ������� 
     */  
    private int mBackGroundWidth;  
    private int mBackGroundHeight;  
    public RotateableView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        // TODO Auto-generated constructor stub  
        mBackGroudDrawableId=attrs.getAttributeResourceValue(namespace, "background", R.drawable.protractor_landscape_background);  
        mBackGroudDrawable = context.getResources().getDrawable(  
                mBackGroudDrawableId);        
        mRotateDegrees=attrs.getAttributeFloatValue(namespace, "rotateDegrees",0.0f);         
    }  
    @Override  
    protected void onDraw(Canvas canvas) {  
        // TODO Auto-generated method stub  
        super.onDraw(canvas);  
        /** 
         * ��ת���� 
         */  
        if (mRotateDegrees==90.0f) {  
            canvas.rotate(mRotateDegrees, 0, 0);  
            canvas.translate(0, -mBackGroundHeight);  
        } else {  
            canvas.rotate(mRotateDegrees, mBackGroundWidth/2, mBackGroundHeight/2);  
        }  
        /** 
         * ִ��draw 
         */  
        mBackGroudDrawable.setBounds(0, 0, mBackGroundWidth, mBackGroundHeight);  
        mBackGroudDrawable.draw(canvas);  
    }  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO Auto-generated method stub  
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
        /** 
         * �趨View��ʾ���� 
         */  
        mBackGroundHeight=mBackGroudDrawable.getMinimumHeight();  
        mBackGroundWidth=mBackGroudDrawable.getMinimumWidth();  
        if (mRotateDegrees==90.0f) {  
            setMeasuredDimension(mBackGroundHeight, mBackGroundWidth);  
        } else {  
            setMeasuredDimension(mBackGroundWidth, mBackGroundHeight);  
        }         
    }     
}  