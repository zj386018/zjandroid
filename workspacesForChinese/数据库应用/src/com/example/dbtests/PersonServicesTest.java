package com.example.dbtests;

import java.util.List;

import com.example.domain.Person;
import com.example.services.DBOpenHelper;
import com.example.services.PersonService;

import android.test.AndroidTestCase;

public class PersonServicesTest extends AndroidTestCase {

	public void testCreateDB() throws Exception{
		
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();
	}
	
	//测试增添（保存）id自动增长
	public void testsave() throws Exception{
		PersonService service = new PersonService(this.getContext());
		for(int i=0;i<20;i++){
		Person person = new Person("张三"+i,"11111111"+i);
		service.save(person);
		}
	}

	public void testdelete() throws Exception{
		PersonService service = new PersonService(this.getContext());
		service.delete(11);
	}
	
//	更新数据（修改） 通过id更改
	public void testupdata() throws Exception{
		PersonService service = new PersonService(this.getContext());
		Person person = service.find(1);
		person.setName("李四");
		service.update(person);
		
	}
	
	//查找（按id）
	public void testfind() throws Exception{
		PersonService service = new PersonService(this.getContext());
		Person person = service.find(0);
	} 
	
	public void testscrolldata() throws Exception{
		PersonService service = new PersonService(this.getContext());
		List<Person> persons = service.getScrollData(3,5);
		for(Person person:persons)//迭代
		{
			//......
		}
		
	}

	public void testcount() throws Exception{
		PersonService service = new PersonService(this.getContext());
		long result = service.getCount();
		
	}
	
	
	
	
}
