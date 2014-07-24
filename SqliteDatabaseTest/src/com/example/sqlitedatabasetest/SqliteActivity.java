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
  //xml�κ��� ��ü����
  nameet = (EditText) findViewById(R.id.nameet);
  //�̺�Ʈ �ο�
  inputbt.setOnClickListener(this);
  //������ ���̽� ��ü ����
  db = new DataBaseTest(this);
  
  
 }
 
 @Override
 public void onClick(View v) {
  
  String word = "";
  
  if(v.getId()==R.id.inputbt){
   //������ �ؽ�Ʈ �ʵ忡�� �����͸� �о�帲
   word = nameet.getText().toString().trim(); 
   //�о�帰 �����͸� ������ ���̽��� ����
   db.insertword(word);
   ArrayList<String> namevec = null;
   //��� ������ ����� ���Ϳ� ����
   namevec = db.allwordSearh();
   //���ο� ��Ƽ��Ƽ ����
   Intent intent = new Intent().setClass(this, AllMemberList.class);
   //�����͸� �Ѱ���
   intent.putStringArrayListExtra("word", namevec);
   //���ο� ȭ���� ���ÿ�
   startActivity(intent);
   
  }
  
  
  
 }

 

} 

