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

	// ��
	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase(); // ȡ�����ݿ����ʵ��
		db.execSQL("insert into person(name,phone) values(?,?)", new Object[] {
				person.getName(), person.getPhone() });
		db.close();
		
		
//		����һ����ӷ�����SQLiteDatabase���װ�ķ�����
//		ContentValues values = new ContentValues();
//		values.put("name", person.getName());
//		values.put("phone", person.getPhone());
//							��ֵ�ֶΣ�valuesֵ����Ϊnullʱ����Ϊ�ռ���ʱ���õ� ��  
//					  ��                 ��                 ����Ҫ�洢������
//		db.insert("person",   null,                 values);			
//		
//
		
	}

	// ɾ
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase(); // ȡ�����ݿ����ʵ��
		db.execSQL("delete from person where personid=?", new Object[] { id });
		db.close();
	}

	// ��
	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase(); // ȡ�����ݿ����ʵ��
		db.execSQL(
				"updata person set name=?,phone=? where personid=?",
				new Object[] { person.getName(), person.getPhone(),
						person.getId() });
		db.close();
		
//		����һ���޸ķ���
//		ContentValues values = new ContentValues();
//		values.put("name", person.getName());
//		values.put("phone", person.getPhone());
//		
//		db.update("person", values, "personid=?", new String[]{person.getId().toString()});
	}

	// ��
	public Person find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where personid=?",
				new String[] { id.toString() });
		if(cursor.moveToFirst()){//���ܲ�ѯ�����ݣ�����ֻ��һ���������ƶ�����һ��
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String personname = cursor.getString(cursor.getColumnIndex("name"));
			String personphone = cursor.getString(cursor.getColumnIndex("phone"));
			return new Person(personid,personname,personphone);
		}
		cursor.close();
		return null;
		
		//��һ�ֲ��ҷ���                                                                                                     
//		                                                                                     ��������Ϊnull����õ����е��ֶ�                                                                                                                                  ����
//		Cursor cursor = db.query("person",   new String[]{"personid","name","phone"}   , "personid=?", new String[]{id.toString()}, null, null, null);
	}

	/**
	 *��ҳ���� 
	 * @param offset ����ǰ��offset����¼
	 * @param maxResult ����ȡ�ļ�¼����
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
		
//		��ҳ��ȡ
//		Cursor cursor = db.query("person", null, null,null, null, null, "personid asc", offset+","+maxResult);
		
 	}

	//ͳ��
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person",null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
		
//		��һ��ͳ�Ʒ���
//        db.query("person", new String[]{"count(*)"}, null, null, null, null, null);
	}
}
