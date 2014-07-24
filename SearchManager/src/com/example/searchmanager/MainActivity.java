package com.example.searchmanager;

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

public class MainActivity extends Activity implements OnClickListener{

 EditText nameet, passet,addresset, emailet;
 Button inputbt, updatebt,deletebt, searchbt,allmemberbt;
 DataBaseTest db;
 
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  //xml�κ��� ��ü����
  nameet = (EditText) findViewById(R.id.nameet);
  passet = (EditText) findViewById(R.id.passet);
  addresset = (EditText) findViewById(R.id.addresset);
  emailet = (EditText) findViewById(R.id.emailet);
  inputbt = (Button) findViewById(R.id.inputbt);
  updatebt = (Button) findViewById(R.id.updatebt);
  deletebt = (Button) findViewById(R.id.deletebt);
  searchbt = (Button) findViewById(R.id.searchbt);
  allmemberbt = (Button) findViewById(R.id.allmemberbt);
  //�̺�Ʈ �ο�
  inputbt.setOnClickListener(this);
  updatebt.setOnClickListener(this);
  deletebt.setOnClickListener(this);
  searchbt.setOnClickListener(this);
  allmemberbt.setOnClickListener(this);
  //������ ���̽� ��ü ����
  db = new DataBaseTest(this);
  
  
 }
 
 @Override
 public void onClick(View v) {
  
  String name = "";  String pass = "";
  String address = "";  String email = "";
  
  if(v.getId()==R.id.inputbt){
   //������ �ؽ�Ʈ �ʵ忡�� �����͸� �о�帲
   name = nameet.getText().toString().trim(); 
   pass = passet.getText().toString().trim();
   address = addresset.getText().toString().trim();
   email = emailet.getText().toString().trim();
   //�о�帰 �����͸� ������ ���̽��� ����
   db.insertMember(name, pass, address, email);
   
  }else if(v.getId()==R.id.updatebt){
   
  }else if(v.getId()==R.id.deletebt){
   
  }else if(v.getId()==R.id.searchbt){
   
  }else if(v.getId()==R.id.allmemberbt){
   ArrayList<String> namevec = null;
   //��� ������ ����� ���Ϳ� ����
   namevec = db.allMemberSearh();
   //���ο� ��Ƽ��Ƽ ����
   Intent intent = new Intent().setClass(this, AllMemberList.class);
   //�����͸� �Ѱ���
   intent.putStringArrayListExtra("name", namevec);
   //���ο� ȭ���� ���ÿ�
   startActivity(intent);
   
  }
  
  
  
 }

 

} 

