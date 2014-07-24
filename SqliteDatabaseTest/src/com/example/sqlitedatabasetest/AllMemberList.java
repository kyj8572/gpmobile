package com.example.sqlitedatabasetest;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class AllMemberList extends ListActivity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  // TODO Auto-generated method stub
  super.onCreate(savedInstanceState);
  //이전 화면 즉,이전 액티비티에서 제공하는 데이터를 받아옴
  Intent intent = getIntent();
  
    ArrayList<String> array = intent.getExtras().getStringArrayList("wrod");
  //리스트에 붙일 아답터 클래스를 선언
    ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, 
      android.R.layout.simple_list_item_1,array);
    //어뎁터를 리스트뷰에 부착
    setListAdapter(adapter);
  
 }
} 

