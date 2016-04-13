package com.baiyi.main;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MylistviewAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private List<Person> persons;

	public MylistviewAdapter(Context context,List<Person> persons) {
		super();
		this.context = context;
		this.persons = persons;
		inflater = LayoutInflater.from(context);// ��ʼ��
	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int arg0) {
		return persons.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View view = inflater.inflate(R.layout.listview_item, null);
		switch (arg0 % 3) {
		case 0:
			view.setBackgroundResource(R.drawable.selector);
			break;
		case 1:
			view.setBackgroundResource(R.drawable.selector1);
			break;
		case 2:
			view.setBackgroundResource(R.drawable.selector2);
			break;

		}
		TextView tv_name = (TextView) view.findViewById(R.id.txt_name);
		TextView tv_tel = (TextView) view.findViewById(R.id.txt_phone);
		// 3.�ҵ�����
		Person person = persons.get(arg0);
		// 4.�����ݵ��ؼ�
		tv_name.setText(person.getName());
		tv_tel.setText(person.getPhone());

		// 5.�����µ�view
		return view;
		
	}

}
