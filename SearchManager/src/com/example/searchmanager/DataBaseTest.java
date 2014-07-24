package com.example.searchmanager;

import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//데이터 베이스 전용 클래스
public class DataBaseTest extends SQLiteOpenHelper {

 //실제 sqlite에 쿼리를 실행시켜주는 객체선언 =java의 statement객체와 유사 사용
 SQLiteDatabase msqlite;
 
 //데이터베이스설정
 public DataBaseTest(Context context) {
  
  super(context, "wordlist.db", null , 1);
  //SQLiteDatabase 객체 사용 선언
  msqlite = this.getWritableDatabase();
  //테이블을 만들기위한 메소드 선언
  createTable();
 }//sqlite를 사용하기위해서
 
  
 private void createTable() {
  //쿼리 준비=ㅡmember테이블이 존재하는지 쿼리 준비
  String sql="select count(*) from sqlite_master where name='member'";
  //쿼리 실행=z쿼리를 실행하면 결과가 Cursor에 저장
  Cursor c = msqlite.rawQuery(sql, null);
  //결과를 저장할 변수 선언
  int result =0;
  //커서 포인터의 위치를 검색된 레코드의 처음으로 즉, 커서객체의 데이터 처음으로
  c.moveToFirst();
  //데이터 추출
  //컬럼인덱스 처음의 값을 정수형으로 리턴.. 
  result = c.getInt(0);  
  
  //결과에 따라 테이블을 생성
  if(result==0){
   //쿼리준비
   sql ="create table member(name text , pass text , " +
     "address text , email text);";
   //쿼리 실행
   msqlite.execSQL(sql);
  }
  
 }

 //맴버 입력 메소드
 public void insertMember(String name, String pass,
   String address, String email){
  //쿼리 준비
  String sql="insert into member values('"+name+"','"+pass+"'," +
    "'"+address+"','"+email+"');";
  //쿼리 실행하시오
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
  //쿼리준비
  String sql="select * from member";
  //쿼리실행후 결과저장
  Cursor c = msqlite.rawQuery(sql, null);
  //커서의 포인터를 처음으로 이동
  c.moveToFirst();
  //반복문을 돌면서 데이터를 백터에 저장
  while(c.isAfterLast() == false){
   vec.add(c.getString(0));
   //다음레코드로 이동
   c.moveToNext();
  }
  c.close();
  
  return vec;
 }

} 

