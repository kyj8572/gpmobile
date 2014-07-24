package com.example.searchview;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper{
	 
	 private SQLiteDatabase database; 
	 private Context context;
	 private static String DB_PATH = "/data/data/com.example.searchview/databases/";
	 private static String DB_NAME = "wordlist.db";
	 private String sql;
	 
	 
	 public DB(Context context) { //생성자
	  super(context, DB_NAME, null, 1);
	  this.context = context;
	 } 
	 
	 public DB(Context context, String name, CursorFactory factory, int version) { 
	  super(context, name, factory, version);
	  this.context = context;
	 }
	 
	 @Override
	 public void onCreate(SQLiteDatabase db) {
	 }
	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	 }
	 
	 public void openDataBase() throws SQLException{ //DB열기
	  //Open the database
	  String myPath = DB_PATH + DB_NAME;
	  database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	 }
	 
	 @Override
	 public synchronized void close() { //db 닫기
	  if(database != null)
	   database.close();
	  super.close();
	 }
	 
	 public void createDataBase() throws IOException{ //db 만들기

	  boolean dbExist = checkDataBase(); //db 유무 확인
	  if(!dbExist) //db 없다면
	  {
	   this.getReadableDatabase(); //읽기전용

	   try {
	    copyDataBase(); // db 복사 메소드 호출
	   } catch (IOException e) {
	    throw new Error("Error copying database");
	   }
	  }
	 }


	 private boolean checkDataBase(){ //db 있는지 확인
	  SQLiteDatabase checkDB = null;
	  try{
	   String myPath = DB_PATH + DB_NAME; //db 위치
	   checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY); //설정 path 오픈
	  }catch(SQLiteException e){ 
	  }
	  if(checkDB != null){ // db 내용이 있으면
	   checkDB.close(); // 종료
	  }

	  return checkDB != null ? true : false; //db 내용 있으면  true 반환, 없으면 false 반환
	 }


	 private void copyDataBase() throws IOException{ //db 복사
	  InputStream myInput = context.getAssets().open(DB_NAME); //assets 및에서 불러옴

	  String outFileName = DB_PATH + DB_NAME; //저장 위치

	  OutputStream myOutput = new FileOutputStream(outFileName); // 저장
	  byte[] buffer = new byte[1024];
	  int length;
	  while ((length = myInput.read(buffer))>0){
	   myOutput.write(buffer, 0, length);
	  }

	  myOutput.flush();
	  myOutput.close();
	  myInput.close();
	 }
	
}
