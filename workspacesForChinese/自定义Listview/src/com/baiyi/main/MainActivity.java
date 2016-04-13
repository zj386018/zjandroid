package com.baiyi.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listView;
	private MylistviewAdapter adapter;
	List<Person> persons;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		listView = (ListView) findViewById(R.id.list_view);
		persons = getPersons();
		adapter = new MylistviewAdapter(MainActivity.this, persons);
		listView.setAdapter(adapter);
		
        
    }
    
	private List<Person> getPersons(){
		List<Person> persons = new ArrayList<Person>();
		for(int i=0;i<30;i++){
			Person person = new Person("ÖÜ½¡"+(i+1), "15388052293");
			persons.add(person);
		}
		return persons;
	}
    
}
