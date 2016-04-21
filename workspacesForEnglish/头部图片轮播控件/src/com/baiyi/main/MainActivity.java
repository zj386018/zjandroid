package com.baiyi.main;

import java.util.ArrayList;

import com.baiyi.bean.Ad;
import com.baiyi.utils.AnimationUtil;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	//ͷ��ͼƬѭ������
	private ViewPager viewPager;
	private ArrayList<Ad> adList = new ArrayList<Ad>();
	private TextView tv_intro;
	private LinearLayout dot_layout;
	
	//�ײ��˵���ť
	private RelativeLayout level1;
	private RelativeLayout level2;
	private RelativeLayout level3;
	private boolean isShowLevel1 = true;
	private boolean isShowLevel2 = true;
	private boolean isShowLevel3 = true;
	
	private ImageView home;
	private ImageView menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();
		initData();
		intListener();
	}

	// ��ʼ���ؼ�
	private void initView() {
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		tv_intro = (TextView) findViewById(R.id.tv_intro);
		dot_layout = (LinearLayout) findViewById(R.id.dot_layout);

		level1 = (RelativeLayout) findViewById(R.id.level1);
		level2 = (RelativeLayout) findViewById(R.id.level2);
		level3 = (RelativeLayout) findViewById(R.id.level3);
		
		home = (ImageView) findViewById(R.id.home);
		menu = (ImageView) findViewById(R.id.menu);
		
		
	}
	
	class MenuClickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
				switch (view.getId()) {
				case R.id.home:
					homeMenu();
					break;
				case R.id.menu:
					menuMenu();
					break;

				default:
					break;
				}
		}
	}
	
	private void homeMenu(){
		int startOffSet = 0;
		
		if(isShowLevel2){
			if(isShowLevel3){
				AnimationUtil.closeMenu(level3, startOffSet);
				startOffSet+=200;
			isShowLevel3 = !isShowLevel3;
			}
			AnimationUtil.closeMenu(level2, startOffSet);
		}else{
			AnimationUtil.showMenu(level2, startOffSet);
		}
		isShowLevel2 = !isShowLevel2;
	}

	private void menuMenu(){
		
		if(isShowLevel3){
			AnimationUtil.closeMenu(level3, 0);
		}else{
			AnimationUtil.showMenu(level3, 0);
		}
		
		isShowLevel3 = !isShowLevel3;
	}
	
	private void intListener() {

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				updataIntroAndDot();
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		home.setOnClickListener(new MenuClickListener());
		menu.setOnClickListener(new MenuClickListener());
		
	}

	/**
	 * �����ı�����ı����ݺ͵�dot��״̬
	 */
	private void updataIntroAndDot() {
		int currentPage = viewPager.getCurrentItem();// ��ȡ��ǰviewPager��ҳ����
		tv_intro.setText(adList.get(currentPage).getIntro());

		for (int i = 0; i < dot_layout.getChildCount(); i++) {
			dot_layout.getChildAt(i).setEnabled(i==currentPage);
		}

	}

	// ��ʼ������
	private void initData() {
		adList.add(new Ad(R.drawable.a, "���ǵ�һ��ͼƬ"));
		adList.add(new Ad(R.drawable.b, "�����ֻ����ˣ��ٳ������ϸ�,������һ�����ֳ��������ʾ��Χʱ����ʾ"));
		adList.add(new Ad(R.drawable.c, "���ر�����Ӱ�������"));
		adList.add(new Ad(R.drawable.d, "������TV������"));
		adList.add(new Ad(R.drawable.e, "��Ѫ��˿�ķ�ɱ�����"));

		initDots();
		viewPager.setAdapter(new MyPagerAdapter());
		updataIntroAndDot();
	}

	/**
	 * ��ʼ���� dot��
	 */
	private void initDots() {
		for (int i = 0; i < adList.size(); i++) {
			View view = new View(this);
			LayoutParams params = new LayoutParams(8, 8);
			if (i != 0) {
				params.leftMargin = 5;
			}
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.selector_dot);
			dot_layout.addView(view);
		}
	}

	/**
	 * viewPager Ԥ���ػ��ƣ�ֻ�ܻ���3��page������Ļ����ٵ�
	 * 
	 * @author Administrator
	 * 
	 */
	class MyPagerAdapter extends PagerAdapter {

		/**
		 * ���ظ�viewpager�ж���page
		 */
		@Override
		public int getCount() {
			return adList.size();
		}

		/**
		 * true����ʾ��ȥ������ʹ�û��� false����ʾ��Ҫ���´���
		 * object����Ҫ������´�����view����instantiateItem������������
		 */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		/**
		 * ������BaseAdapter��getView���������������ø�view �Á팢�������ø�viewPager��
		 * ����������3�����棬����ҪviewHolder
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(MainActivity.this, R.layout.adapter_ad,
					null);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);

			imageView.setImageResource(adList.get(position).getIconResId());
			container.addView(view);// һ�������٣���view���뵽viewpager����

			return view;
		}

		/**
		 * ����page position����ǰ��Ҫ���ٵĵڼ���page object����ǰ��Ҫ���ٵ�page
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position,
			// object);//�÷���ֻ���׳�һ����destroyItem����δ��д�����쳣
			container.removeView((View) object);
		}

	}

}
