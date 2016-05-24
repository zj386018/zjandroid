package com.baiyi.mysqlite.activity;

import java.util.List;

import com.baiyi.mysqlite.bean.Person;
import com.baiyi.mysqlite.db.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private List<Person> persons;

	public MyAdapter(Context context,List<Person> persons) {
		this.context = context;
		this.persons = persons;
		inflater = LayoutInflater.from(context);
		
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		View view = null;
		if(null == convertView){
			view = inflater.inflate(R.layout.listview_item, null);
		}else{
			view = convertView;
		}
		Person person = persons.get(arg0);
		
		
		
		return null;

	}

}
