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
	 
	 
	 public DB(Context context) { //������
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
	 
	 public void openDataBase() throws SQLException{ //DB����
	  //Open the database
	  String myPath = DB_PATH + DB_NAME;
	  database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	 }
	 
	 @Override
	 public synchronized void close() { //db �ݱ�
	  if(database != null)
	   database.close();
	  super.close();
	 }
	 
	 public void createDataBase() throws IOException{ //db �����

	  boolean dbExist = checkDataBase(); //db ���� Ȯ��
	  if(!dbExist) //db ���ٸ�
	  {
	   this.getReadableDatabase(); //�б�����

	   try {
	    copyDataBase(); // db ���� �޼ҵ� ȣ��
	   } catch (IOException e) {
	    throw new Error("Error copying database");
	   }
	  }
	 }


	 private boolean checkDataBase(){ //db �ִ��� Ȯ��
	  SQLiteDatabase checkDB = null;
	  try{
	   String myPath = DB_PATH + DB_NAME; //db ��ġ
	   checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY); //���� path ����
	  }catch(SQLiteException e){ 
	  }
	  if(checkDB != null){ // db ������ ������
	   checkDB.close(); // ����
	  }

	  return checkDB != null ? true : false; //db ���� ������  true ��ȯ, ������ false ��ȯ
	 }


	 private void copyDataBase() throws IOException{ //db ����
	  InputStream myInput = context.getAssets().open(DB_NAME); //assets �׿��� �ҷ���

	  String outFileName = DB_PATH + DB_NAME; //���� ��ġ

	  OutputStream myOutput = new FileOutputStream(outFileName); // ����
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
