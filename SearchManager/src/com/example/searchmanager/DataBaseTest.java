package com.example.searchmanager;

import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//������ ���̽� ���� Ŭ����
public class DataBaseTest extends SQLiteOpenHelper {

 //���� sqlite�� ������ ��������ִ� ��ü���� =java�� statement��ü�� ���� ���
 SQLiteDatabase msqlite;
 
 //�����ͺ��̽�����
 public DataBaseTest(Context context) {
  
  super(context, "wordlist.db", null , 1);
  //SQLiteDatabase ��ü ��� ����
  msqlite = this.getWritableDatabase();
  //���̺��� ��������� �޼ҵ� ����
  createTable();
 }//sqlite�� ����ϱ����ؼ�
 
  
 private void createTable() {
  //���� �غ�=��member���̺��� �����ϴ��� ���� �غ�
  String sql="select count(*) from sqlite_master where name='member'";
  //���� ����=z������ �����ϸ� ����� Cursor�� ����
  Cursor c = msqlite.rawQuery(sql, null);
  //����� ������ ���� ����
  int result =0;
  //Ŀ�� �������� ��ġ�� �˻��� ���ڵ��� ó������ ��, Ŀ����ü�� ������ ó������
  c.moveToFirst();
  //������ ����
  //�÷��ε��� ó���� ���� ���������� ����.. 
  result = c.getInt(0);  
  
  //����� ���� ���̺��� ����
  if(result==0){
   //�����غ�
   sql ="create table member(name text , pass text , " +
     "address text , email text);";
   //���� ����
   msqlite.execSQL(sql);
  }
  
 }

 //�ɹ� �Է� �޼ҵ�
 public void insertMember(String name, String pass,
   String address, String email){
  //���� �غ�
  String sql="insert into member values('"+name+"','"+pass+"'," +
    "'"+address+"','"+email+"');";
  //���� �����Ͻÿ�
  msqlite.execSQL(sql);  
 }
 
 
 

 

 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  
 }


 public ArrayList<String> allMemberSearh() {
  
  ArrayList<String> vec = new ArrayList<String>();
  //�����غ�
  String sql="select * from member";
  //���������� �������
  Cursor c = msqlite.rawQuery(sql, null);
  //Ŀ���� �����͸� ó������ �̵�
  c.moveToFirst();
  //�ݺ����� ���鼭 �����͸� ���Ϳ� ����
  while(c.isAfterLast() == false){
   vec.add(c.getString(0));
   //�������ڵ�� �̵�
   c.moveToNext();
  }
  c.close();
  
  return vec;
 }

} 

