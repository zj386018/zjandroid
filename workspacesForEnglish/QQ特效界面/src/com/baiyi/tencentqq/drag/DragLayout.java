package com.baiyi.tencentqq.drag;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 
 * 侧滑面板
 * 
 * @author Administrator
 *
 */
public class DragLayout extends FrameLayout {

	protected static final String TAG = "TAG";
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
		
		//a.初始化操作（通过静态方法）
		mDragHelper = ViewDragHelper.create(this,mCallback );
		
	}
	
	ViewDragHelper.Callback mCallback = new Callback() {
		//c. 重写事件
		

		//1.根据返回结果来决定当前child是否可以拖拽
		//child 是当前被拖拽的view
		//pointerId 区分多点触摸的id
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			Log.d(TAG,"tryCaptureView："+child);
			
			return true;
		}
		
		@Override
		public void onViewCaptured(View capturedChild, int activePointerId) {
			Log.d(TAG,"onViewCaptured："+capturedChild);
			//当View--capturedChild被捕获的时候调用
			super.onViewCaptured(capturedChild, activePointerId);
		}
		
		@Override
		public int getViewHorizontalDragRange(View child) {
			// TODO Auto-generated method stub
			return super.getViewHorizontalDragRange(child);
		}

		//2.根据建议值修正将要移动到的横向的位置
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			return left;
		}
		
		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			// TODO Auto-generated method stub
			return super.clampViewPositionVertical(child, top, dy);
		}

		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			// TODO Auto-generated method stub
			super.onViewPositionChanged(changedView, left, top, dx, dy);
		}



		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			// TODO Auto-generated method stub
			super.onViewReleased(releasedChild, xvel, yvel);
		}


		@Override
		public void onViewDragStateChanged(int state) {
			// TODO Auto-generated method stub
			super.onViewDragStateChanged(state);
		}
		
		
		
		
		
	};

	
	
	//b.传递触摸事件

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		//传递给mDragHelper 是否拦截ev
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
		//返回true，持续接受事件
		return true;
	}
	
	@Override
	protected void onFinishInflate() {
		//写注释（最好是英文）
		//容错性检查（至少有两个子view，子view必须是ViewGroup的子类）
		if(getChildCount()<2){
			throw new IllegalStateException("布局至少要两个子view。Your ViewGroup must have 2 children at least.");
			
		}
		if(!(getChildAt(0)instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)){
			throw new IllegalArgumentException("子view必须是ViewGroup的子类.Your children must be an instanceof ViewGroup");
		}
		
		mLeftContent = (ViewGroup) getChildAt(0);
		mMainContent = (ViewGroup) getChildAt(1);
		
	}
	
	
}
