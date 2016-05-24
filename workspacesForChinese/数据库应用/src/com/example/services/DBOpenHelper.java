package com.example.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		//             数据库名称(你项目所需要创建的数据库名称)
		//                ↓         游标工厂(产生游标对象，用来迭代查询的访问)null表示用系统默认的
		//				  ↓            ↓      数据库文件的版本号（要求不要为0）：发生改变时会调用onUpgrade方法
		//                ↓            ↓           ↓
		super(context, "mysqlite.db",   null,        1  );//默认保存在当前应用<包>/databases/
		// TODO Auto-generated constructor stub
	}

	//         封装了数据库的所有操作方法（增，删，改，查）
	@Override//               ↓
	public void onCreate(SQLiteDatabase db) {//数据库被创建的时候调用的
//                                                          主键自增长                           
		db.execSQL("CREATE TABLE person(personid integer primary key autoincrement,name varchar(20))");
		
		
		
	}
//              版本号发生变更后调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
	}

}
