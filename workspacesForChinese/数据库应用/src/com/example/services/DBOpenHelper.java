package com.example.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		//             ���ݿ�����(����Ŀ����Ҫ���������ݿ�����)
		//                ��         �α깤��(�����α��������������ѯ�ķ���)null��ʾ��ϵͳĬ�ϵ�
		//				  ��            ��      ���ݿ��ļ��İ汾�ţ�Ҫ��ҪΪ0���������ı�ʱ�����onUpgrade����
		//                ��            ��           ��
		super(context, "mysqlite.db",   null,        1  );//Ĭ�ϱ����ڵ�ǰӦ��<��>/databases/
		// TODO Auto-generated constructor stub
	}

	//         ��װ�����ݿ�����в�������������ɾ���ģ��飩
	@Override//               ��
	public void onCreate(SQLiteDatabase db) {//���ݿⱻ������ʱ����õ�
//                                                          ����������                           
		db.execSQL("CREATE TABLE person(personid integer primary key autoincrement,name varchar(20))");
		
		
		
	}
//              �汾�ŷ�����������
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
	}

}
