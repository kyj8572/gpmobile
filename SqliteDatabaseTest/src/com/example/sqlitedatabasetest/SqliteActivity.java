package com.example.sqlitedatabasetest;

import java.util.ArrayList;
import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SqliteActivity extends Activity implements OnClickListener{

 EditText nameet;
 Button inputbt, updatebt,deletebt, searchbt,allmemberbt;
 DataBaseTest db;
 
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_sqlite);
  //xml로부터 객체생성
  nameet = (EditText) findViewById(R.id.nameet);
  //이벤트 부역
  inputbt.setOnClickListener(this);
  //데이터 베이스 객체 선언
  db = new DataBaseTest(this);
  
  
 }
 
 @Override
 public void onClick(View v) {
  
  String word = "";
  
  if(v.getId()==R.id.inputbt){
   //각각의 텍스트 필드에서 데이터를 읽어드림
   word = nameet.getText().toString().trim(); 
   //읽어드린 데이터를 데이터 베이스에 저장
   db.insertword(word);
   ArrayList<String> namevec = null;
   //디비 실행후 결과가 백터에 저장
   namevec = db.allwordSearh();
   //새로운 액티비티 설정
   Intent intent = new Intent().setClass(this, AllMemberList.class);
   //데이터를 넘겨줘
   intent.putStringArrayListExtra("word", namevec);
   //새로운 화면을 띄우시오
   startActivity(intent);
   
  }
  
  
  
 }

 

} 

