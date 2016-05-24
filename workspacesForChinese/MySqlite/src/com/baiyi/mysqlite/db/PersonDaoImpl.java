package com.baiyi.mysqlite.db;

import java.util.ArrayList;
import java.util.List;

import com.baiyi.mysqlite.bean.Person;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDaoImpl {

	private DBOpenHelper helper;

	public PersonDaoImpl(Context context) {
		helper = new DBOpenHelper(context);
	}

	// ��
	public void save(Person person) {
		SQLiteDatabase db = helper.getWritableDatabase(); // ȡ�����ݿ����ʵ��
		db.execSQL("insert into person(name,phone) values(?,?)", new Object[] {
				person.getName(), person.getPhone() });
		db.close();
	}

	// ɾ
	public void delete(Integer id) {
		SQLiteDatabase db = helper.getWritableDatabase(); // ȡ�����ݿ����ʵ��
		db.execSQL("delete from person where personid=?", new Object[] { id });
		db.close();
	}

	// ��
	public void update(Person person) {
		SQLiteDatabase db = helper.getWritableDatabase(); // ȡ�����ݿ����ʵ��
		db.execSQL(
				"updata person set name=?,phone=? where personid=?",
				new Object[] { person.getName(), person.getPhone(),
						person.getId() });
		db.close();
	}

	//��
	public List<Person> getAllPerson(){
		SQLiteDatabase db = helper.getWritableDatabase(); // ȡ�����ݿ����ʵ��
		Cursor cursor = db.rawQuery("select * from person", null);
		List<Person> persons = new ArrayList<Person>();
		if(cursor.moveToFirst()){//���ܲ�ѯ�����ݣ�����ֻ��һ���������ƶ�����һ��
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String personname = cursor.getString(cursor.getColumnIndex("name"));
			String personphone = cursor.getString(cursor.getColumnIndex("phone"));
			persons.add(new Person(personid, personname, personphone));
		}
		db.close();
		return persons;
	}
	
	public Person getPerson(String name){
		SQLiteDatabase db = helper.getWritableDatabase(); // ȡ�����ݿ����ʵ��
		Cursor cursor = db.rawQuery("select * from person where name=?",
				new String[] { name });
		Person person = null;
		if(cursor.moveToFirst()){//���ܲ�ѯ�����ݣ�����ֻ��һ���������ƶ�����һ��
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String personname = cursor.getString(cursor.getColumnIndex("name"));
			String personphone = cursor.getString(cursor.getColumnIndex("phone"));
			person =new Person(personid, personname, personphone);
		}
		db.close();
		return person;
	}
	
	//ͳ����������
		public long getCount() {
			SQLiteDatabase db = helper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select count(*) from person",null);
			cursor.moveToFirst();
			long result = cursor.getLong(0);
			cursor.close();
			return result;
		}
	
	
}
