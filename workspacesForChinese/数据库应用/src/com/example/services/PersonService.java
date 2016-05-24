package com.example.services;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.domain.Person;

public class PersonService {
	private DBOpenHelper dbOpenHelper;

	public PersonService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	// 增
	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase(); // 取得数据库操作实例
		db.execSQL("insert into person(name,phone) values(?,?)", new Object[] {
				person.getName(), person.getPhone() });
		db.close();
		
		
//		另外一种添加方法（SQLiteDatabase里封装的方法）
//		ContentValues values = new ContentValues();
//		values.put("name", person.getName());
//		values.put("phone", person.getPhone());
//							空值字段（values值传入为null时或者为空集合时就用到 ）  
//					  表                 ↓                 传入要存储的数据
//		db.insert("person",   null,                 values);			
//		
//
		
	}

	// 删
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase(); // 取得数据库操作实例
		db.execSQL("delete from person where personid=?", new Object[] { id });
		db.close();
	}

	// 改
	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase(); // 取得数据库操作实例
		db.execSQL(
				"updata person set name=?,phone=? where personid=?",
				new Object[] { person.getName(), person.getPhone(),
						person.getId() });
		db.close();
		
//		另外一种修改方法
//		ContentValues values = new ContentValues();
//		values.put("name", person.getName());
//		values.put("phone", person.getPhone());
//		
//		db.update("person", values, "personid=?", new String[]{person.getId().toString()});
	}

	// 查
	public Person find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where personid=?",
				new String[] { id.toString() });
		if(cursor.moveToFirst()){//若能查询到数据，最少只有一条，则能移动到第一条
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String personname = cursor.getString(cursor.getColumnIndex("name"));
			String personphone = cursor.getString(cursor.getColumnIndex("phone"));
			return new Person(personid,personname,personphone);
		}
		cursor.close();
		return null;
		
		//另一种查找方法                                                                                                     
//		                                                                                     如果传入的为null，则得到所有的字段                                                                                                                                  排序
//		Cursor cursor = db.query("person",   new String[]{"personid","name","phone"}   , "personid=?", new String[]{id.toString()}, null, null, null);
	}

	/**
	 *分页查找 
	 * @param offset 跳过前面offset条记录
	 * @param maxResult 所获取的记录条数
	 * @return
	 */
	public List<Person> getScrollData(int offset, int maxResult) {
		List<Person> personlist = new ArrayList<Person>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person order by personid asc limit ?,?",
				new String[] {String.valueOf(offset),String.valueOf(maxResult)});
		while(cursor.moveToNext()){
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String personname = cursor.getString(cursor.getColumnIndex("name"));
			String personphone = cursor.getString(cursor.getColumnIndex("phone"));
			personlist.add(new Person(personid,personname,personphone));
		}
		cursor.close();
		return personlist;
		
//		分页获取
//		Cursor cursor = db.query("person", null, null,null, null, null, "personid asc", offset+","+maxResult);
		
 	}

	//统计
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person",null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
		
//		另一种统计方法
//        db.query("person", new String[]{"count(*)"}, null, null, null, null, null);
	}
}
