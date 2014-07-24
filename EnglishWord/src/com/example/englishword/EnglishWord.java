package com.example.englishword;

 

import android.app.ListActivity;

import android.os.Bundle;

import android.text.Editable;

import android.text.TextWatcher;

import android.view.View;

import android.widget.AdapterView;

import android.widget.AdapterView.OnItemClickListener;

import android.widget.ArrayAdapter;

import android.widget.EditText;

import android.widget.ListView;

import android.widget.TextView;

import android.widget.Toast;

 

public class LVTest extends ListActivity {

 static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",

   "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",

   "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

 

 @Override

 public void onCreate(Bundle savedInstanceState) {

  super.onCreate(savedInstanceState);

 

  setContentView(R.layout.activity_lvtest);

 

  setListAdapter(new ArrayAdapter<String>(this,

    android.R.layout.simple_list_item_1, FRUITS));

 

  final ListView lv = getListView();

  //리스트뷰에 텍스트필터링 기능이 가능하게 한다.

  lv.setTextFilterEnabled(true);

 

  lv.setOnItemClickListener(new OnItemClickListener() {

   public void onItemClick(AdapterView<?> parent, View view,

     int position, long id) {

    Toast.makeText(getApplicationContext(),

      ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

   }

  });

 

  final EditText et = (EditText) findViewById(R.id.et);

  et.addTextChangedListener(new TextWatcher() {

   @Override

   public void onTextChanged(CharSequence s, int start, int before,

     int count) {

    //변화되는 문자열을 필터링에 적용한다

    lv.setFilterText(s.toString());

   }

 

   @Override

   public void afterTextChanged(Editable arg0) {

   }

 

   @Override

   public void beforeTextChanged(CharSequence s, int start, int count,

     int after) {

   }

  });

 

 }

 

}
