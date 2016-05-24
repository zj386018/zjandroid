package com.baiyi.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	private ViewPager viewPager;
	private PagerTitleStrip pagerTitleStrip;// 滑动的每一页的标题。
	private List<View> list;
	private List<String> titleList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		pagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagertitle);
		// 动态加载布局
		View view1 = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.tab1, null);
		View view2 = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.tab2, null);
		View view3 = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.tab3, null);

		list = new ArrayList<View>();
		list.add(view1);
		list.add(view2);
		list.add(view3);

		titleList = new ArrayList<String>();
		titleList.add("title1");
		titleList.add("title2");
		titleList.add("title3");

		viewPager.setAdapter(new MyAdapter());

	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(list.get(position));
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titleList.get(position);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(list.get(position));
			return list.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

}
