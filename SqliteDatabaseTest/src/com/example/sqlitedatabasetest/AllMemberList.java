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
  //���� ȭ�� ��,���� ��Ƽ��Ƽ���� �����ϴ� �����͸� �޾ƿ�
  Intent intent = getIntent();
  
    ArrayList<String> array = intent.getExtras().getStringArrayList("wrod");
  //����Ʈ�� ���� �ƴ��� Ŭ������ ����
    ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, 
      android.R.layout.simple_list_item_1,array);
    //��͸� ����Ʈ�信 ����
    setListAdapter(adapter);
  
 }
} 

