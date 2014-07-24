package com.example.autocomplete;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

public class MainActivity extends Activity implements TextWatcher  {
	 
	WordDBHelper mHelper;
	SQLiteDatabase db;
	private AutoCompleteTextView auto;
	private ArrayList<String> list2;
	ListView list;
	String autoword, fir;
	ArrayAdapter<String> array;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			mHelper = new WordDBHelper(this);
			db = mHelper.getReadableDatabase();
			auto = (AutoCompleteTextView) findViewById(R.id.autoedit);
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
				String sql = "select word from word where rowid='1'";
				cursor = db.rawQuery(sql, null);
				
				while(cursor.moveToNext()){
					list2.add(cursor.getString(cursor.getColumnIndex("word")) );//arraylist�� �ٽ� �迭
				}

				cursor.close();
				db.close();
			} catch (Exception e) {
				Log.e("ErrorMessage2 : ", e.getMessage());
			}


			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list2);   // adapter�� �ڵ��ϼ� ����
			auto.setAdapter(adapter);
			array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
			list.setAdapter(array);
			list.setTextFilterEnabled(true);
			MainActivity.this.array.getFilter().filter(text);
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
 