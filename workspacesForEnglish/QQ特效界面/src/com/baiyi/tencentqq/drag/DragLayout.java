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
	private int mHeight;
	private int mWidth;
	private int mRange;

	public DragLayout(Context context) {
		super(context, null);
	}

	public DragLayout(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public DragLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		// a.初始化操作（通过静态方法）
		mDragHelper = ViewDragHelper.create(this, mCallback);

	}

	ViewDragHelper.Callback mCallback = new Callback() {
		// c. 重写事件

		// -----1.根据返回结果来决定当前child是否可以拖拽
		// child 是当前被拖拽的view
		// pointerId 区分多点触摸的id
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			Log.d(TAG, "tryCaptureView：" + child);

			return true;
		}

		@Override
		public void onViewCaptured(View capturedChild, int activePointerId) {
			Log.d(TAG, "onViewCaptured：" + capturedChild);
			// 当View--capturedChild被捕获的时候调用
			super.onViewCaptured(capturedChild, activePointerId);
		}

		@Override
		public int getViewHorizontalDragRange(View child) {
			// 返回拖拽的范围，不对拖拽进行真正的限制，仅仅决定了动画执行速度。
			return mRange;
		}

		// ------2.根据建议值修正将view左顶点要移动到的横向的位置 （重要）
		// 此时还没有发生真正的移动
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			// child：当前拖拽的view
			// left：view左顶点的新的位置的建议值，dx：左顶点位置的变化量（与之前位置的差值）
			if (child == mMainContent) {
				left = fixLeft(left);
			}

			return left;
		}


		// @Override
		// public int clampViewPositionVertical(View child, int top, int dy) {
		// // 根据建议值修正将view左顶点要移动到的纵向的位置
		// //child：当前拖拽的view
		// //top：新的左顶点纵轴位置的建议值，dy：左顶点位置的变化量（与之前位置的差值）
		// return top;
		// }

		
		
		
		//3. 当view位置改变的时候，处理要做的事情（更新状态，伴随动画，重绘界面）
		//此时，view的位置已经发生了改变
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			//changedView：改变位置的view控件
			//left：新的左边值
			//dx：水平方向变化量
			
			super.onViewPositionChanged(changedView, left, top, dx, dy);
			
			int newLeft = left;
			if(changedView == mMainContent){
				//把当前变化量传递给mMainContent
				newLeft = mMainContent.getLeft() + dx;
			}
			
			//对newleft进行修正
			newLeft = fixLeft(newLeft);
			
			if(changedView == mLeftContent){
				//当左面板移动之后，再强制放回去
				mLeftContent.layout(0, 0, 0+mWidth,0+mHeight);
				mMainContent.layout(newLeft, 0, newLeft + mWidth, 0+mHeight);
			}
			
			
			// 为了兼容低版本，每次修改值之后，进行重绘
			invalidate();
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
	
	/**
	 * 根据范围修左边的值
	 * @param left
	 * @return
	 */
	private int fixLeft(int left) {
		if (left < 0) {
			return 0;
		} else if (left > mRange) {
			return mRange;
		}
		return left;
	}


	// b.传递触摸事件
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		System.out.println(ev);
		// 传递给mDragHelper 是否拦截ev
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
		// 返回true，持续接受事件
		return true;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		// 写注释（最好是英文）
		// 容错性检查（至少有两个子view，子view必须是ViewGroup的子类）
		if (getChildCount() < 2) {
			throw new IllegalStateException(
					"布局至少要两个子view。Your ViewGroup must have 2 children at least.");

		}
		if (!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)) {
			throw new IllegalArgumentException(
					"子view必须是ViewGroup的子类.Your children must be an instanceof ViewGroup");
		}

		mLeftContent = (ViewGroup) getChildAt(0);
		mMainContent = (ViewGroup) getChildAt(1);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		mHeight = getMeasuredHeight();
		mWidth = getMeasuredWidth();

		mRange = (int) (mWidth * 0.6f);

	}

}
