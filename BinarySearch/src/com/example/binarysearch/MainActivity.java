package com.example.binarysearch;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements TextWatcher  {
	 
	WordDBHelper mHelper;
	SQLiteDatabase db;
	private EditText auto;
	private static ArrayList<String> list2;
	ListView list;
	String autoword, fir;
	ArrayAdapter<String> array;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			mHelper = new WordDBHelper(this);
			db = mHelper.getReadableDatabase();
			auto = (EditText) findViewById(R.id.autoedit);
			auto.addTextChangedListener(this);
			list2 = new ArrayList<String>();
			list = (ListView)findViewById(R.id.list);
			
	}
	
	public void onTextChanged(CharSequence text, int start, int before, int after) { //������ ������ ������ ����

		autoword = auto.getText().toString()+""; //�Էµ� �� �������
		if (autoword == "") {  //�Էµ� ù���ڰ� ������ fir�� �ֱ�
			fir = "word";   //������ ��� text�� backspace �ϸ� �����߻�
		}else{
			fir = autoword.substring(0,1);  // 'word'�� DB�� �÷���
		}
		Cursor cursor = null;
		try {
			String sql = "select rowid,word from word ";
			cursor = db.rawQuery(sql, null);
			Log.e("1", "count: " + cursor.getCount());
			
			while(cursor.moveToNext()){
				list2.add(Integer.toString(cursor.getInt(cursor.getColumnIndex("rowid")))+ "  " + cursor.getString(cursor.getColumnIndex("word")));//arraylist�� �ٽ� �迭
			}

			cursor.close();
			db.close();
		} catch (Exception e) {
			Log.e("ErrorMessage2 : ", e.getMessage());
		}

		array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
		list.setAdapter(array);
		list.setTextFilterEnabled(true);
		MainActivity.this.array.getFilter().filter(text);
}
	
	 public static void search(int key, int s, int e) {
		    int mid = 0;
		    Cursor cursor = null;
			mid = (s+e) / 2;

		    if( s > e) {
		      System.out.println("No searching result !!!");
		      return ;
		    }

		    if(cursor.getCount()/2 > key) {
		      System.out.println("Finding position under " + (mid+1));
		      search(key, s, mid-1);
		    } else if(cursor.getCount()/2 < key) {
		      System.out.println("Finding position upper " + (mid+1));
		      search(key, mid+1, e);
		    } else if(cursor.getCount()/2 == key) {
		      System.out.println("Find it At Position " + (mid+1) + " !!!!");
		      System.out.println("The value is "+cursor.getCount()/2);
		    }
		  }


@Override
public void afterTextChanged(Editable arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
		int arg3) {
	// TODO Auto-generated method stub
	
}
}