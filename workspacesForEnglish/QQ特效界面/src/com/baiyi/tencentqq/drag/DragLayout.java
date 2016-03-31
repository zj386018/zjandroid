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
 * �໬���
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

		// a.��ʼ��������ͨ����̬������
		mDragHelper = ViewDragHelper.create(this, mCallback);

	}

	ViewDragHelper.Callback mCallback = new Callback() {
		// c. ��д�¼�

		// -----1.���ݷ��ؽ����������ǰchild�Ƿ������ק
		// child �ǵ�ǰ����ק��view
		// pointerId ���ֶ�㴥����id
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			Log.d(TAG, "tryCaptureView��" + child);

			return true;
		}

		@Override
		public void onViewCaptured(View capturedChild, int activePointerId) {
			Log.d(TAG, "onViewCaptured��" + capturedChild);
			// ��View--capturedChild�������ʱ�����
			super.onViewCaptured(capturedChild, activePointerId);
		}

		@Override
		public int getViewHorizontalDragRange(View child) {
			// ������ק�ķ�Χ��������ק�������������ƣ����������˶���ִ���ٶȡ�
			return mRange;
		}

		// ------2.���ݽ���ֵ������view�󶥵�Ҫ�ƶ����ĺ����λ�� ����Ҫ��
		// ��ʱ��û�з����������ƶ�
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			// child����ǰ��ק��view
			// left��view�󶥵���µ�λ�õĽ���ֵ��dx���󶥵�λ�õı仯������֮ǰλ�õĲ�ֵ��
			if (child == mMainContent) {
				left = fixLeft(left);
			}

			return left;
		}


		// @Override
		// public int clampViewPositionVertical(View child, int top, int dy) {
		// // ���ݽ���ֵ������view�󶥵�Ҫ�ƶ����������λ��
		// //child����ǰ��ק��view
		// //top���µ��󶥵�����λ�õĽ���ֵ��dy���󶥵�λ�õı仯������֮ǰλ�õĲ�ֵ��
		// return top;
		// }

		
		
		
		//3. ��viewλ�øı��ʱ�򣬴���Ҫ�������飨����״̬�����涯�����ػ���棩
		//��ʱ��view��λ���Ѿ������˸ı�
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			//changedView���ı�λ�õ�view�ؼ�
			//left���µ����ֵ
			//dx��ˮƽ����仯��
			
			super.onViewPositionChanged(changedView, left, top, dx, dy);
			
			int newLeft = left;
			if(changedView == mMainContent){
				//�ѵ�ǰ�仯�����ݸ�mMainContent
				newLeft = mMainContent.getLeft() + dx;
			}
			
			//��newleft��������
			newLeft = fixLeft(newLeft);
			
			if(changedView == mLeftContent){
				//��������ƶ�֮����ǿ�ƷŻ�ȥ
				mLeftContent.layout(0, 0, 0+mWidth,0+mHeight);
				mMainContent.layout(newLeft, 0, newLeft + mWidth, 0+mHeight);
			}
			
			
			// Ϊ�˼��ݵͰ汾��ÿ���޸�ֵ֮�󣬽����ػ�
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
	 * ���ݷ�Χ����ߵ�ֵ
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


	// b.���ݴ����¼�
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		System.out.println(ev);
		// ���ݸ�mDragHelper �Ƿ�����ev
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
		// ����true�����������¼�
		return true;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		// дע�ͣ������Ӣ�ģ�
		// �ݴ��Լ�飨������������view����view������ViewGroup�����ࣩ
		if (getChildCount() < 2) {
			throw new IllegalStateException(
					"��������Ҫ������view��Your ViewGroup must have 2 children at least.");

		}
		if (!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)) {
			throw new IllegalArgumentException(
					"��view������ViewGroup������.Your children must be an instanceof ViewGroup");
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
