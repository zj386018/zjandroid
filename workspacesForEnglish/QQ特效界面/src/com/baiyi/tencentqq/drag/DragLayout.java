package com.baiyi.tencentqq.drag;

import com.baiyi.tencentqq.MainActivity;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * 
 * �໬���
 * 
 * @author Administrator
 *
 */
public class DragLayout extends FrameLayout {

	private ViewDragHelper mDragHelper;
	private ViewGroup mLeftContent;
	private ViewGroup mMainContent;
	
	public DragLayout(Context context) {
		super(context,null);
	}

	public DragLayout(Context context, AttributeSet attrs) {
		super(context, attrs,0);
	}

	public DragLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//a.��ʼ��������ͨ����̬������
		mDragHelper = ViewDragHelper.create(this,mCallback );
		
	}
	
	ViewDragHelper.Callback mCallback = new Callback() {

		//1.���ݷ��ؽ����������ǰchild�Ƿ������ק
		//child �ǵ�ǰ����ק��view
		//pointerId ���ֶ�㴥����id
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			return child == mMainContent;
		}
		

		//2.���ݽ���ֵ������Ҫ�ƶ����ĺ����λ��
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			return left;
		}
		
	};

	
	
	//b.���ݴ����¼�

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		//���ݸ�mDragHelper �Ƿ�����ev
		return mDragHelper.shouldInterceptTouchEvent(ev);

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		try {
			mDragHelper.processTouchEvent(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����true�����������¼�
		return true;
	}
	
	@Override
	protected void onFinishInflate() {
		//дע�ͣ������Ӣ�ģ�
		//�ݴ��Լ�飨������������view����view������ViewGroup�����ࣩ
		if(getChildCount()<2){
			throw new IllegalStateException("��������Ҫ������view��Your ViewGroup must have 2 children at least.");
			
		}
		if(!(getChildAt(0)instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)){
			throw new IllegalArgumentException("��view������ViewGroup������.Your children must be an instanceof ViewGroup");
		}
		
		mLeftContent = (ViewGroup) getChildAt(0);
		mMainContent = (ViewGroup) getChildAt(1);
		
	}
	
	
}
