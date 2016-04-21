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

	//头部图片循环播放
	private ViewPager viewPager;
	private ArrayList<Ad> adList = new ArrayList<Ad>();
	private TextView tv_intro;
	private LinearLayout dot_layout;
	
	//底部菜单按钮
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

	// 初始化控件
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
	 * 更新文本框的文本内容和点dot的状态
	 */
	private void updataIntroAndDot() {
		int currentPage = viewPager.getCurrentItem();// 获取当前viewPager的页面编号
		tv_intro.setText(adList.get(currentPage).getIntro());

		for (int i = 0; i < dot_layout.getChildCount(); i++) {
			dot_layout.getChildAt(i).setEnabled(i==currentPage);
		}

	}

	// 初始化数据
	private void initData() {
		adList.add(new Ad(R.drawable.a, "我是第一张图片"));
		adList.add(new Ad(R.drawable.b, "朴树又回来了，再唱经典老歌,并测试一下文字超过最大显示范围时的显示"));
		adList.add(new Ad(R.drawable.c, "揭秘北京电影如何升级"));
		adList.add(new Ad(R.drawable.d, "乐视网TV版大放送"));
		adList.add(new Ad(R.drawable.e, "热血屌丝的反杀额。。。"));

		initDots();
		viewPager.setAdapter(new MyPagerAdapter());
		updataIntroAndDot();
	}

	/**
	 * 初始化点 dot，
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
	 * viewPager 预加载机制：只能缓存3个page，多余的会销毁掉
	 * 
	 * @author Administrator
	 * 
	 */
	class MyPagerAdapter extends PagerAdapter {

		/**
		 * 返回该viewpager有多少page
		 */
		@Override
		public int getCount() {
			return adList.size();
		}

		/**
		 * true：表示不去创建，使用缓存 false：表示需要重新创建
		 * object：将要进入的新创建的view，由instantiateItem（）方法创建
		 */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		/**
		 * 类似于BaseAdapter的getView方法，将属性设置给view 用來將数据设置给viewPager的
		 * 由于它最多就3个界面，不需要viewHolder
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(MainActivity.this, R.layout.adapter_ad,
					null);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);

			imageView.setImageResource(adList.get(position).getIconResId());
			container.addView(view);// 一定不能少：将view加入到viewpager当中

			return view;
		}

		/**
		 * 销毁page position：当前需要销毁的第几个page object：当前需要销毁的page
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position,
			// object);//该方法只是抛出一个“destroyItem方法未重写”的异常
			container.removeView((View) object);
		}

	}

}
