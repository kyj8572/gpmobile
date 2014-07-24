package com.example.binarysearch;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class WordDBHelper extends SQLiteOpenHelper {


	 public WordDBHelper(Context context) {
	  super(context, "wordlist.db", null, 1);
	  
	  try {
	  boolean bResult = isCheckDB(context);  // DB�� �ִ���?
	  if(!bResult){   // DB�� ������ ����
	  copyDB(context);
	  }else{
	   
	     }
	  } catch (Exception e) {
	  }
	 }


	 @Override
	 public void onCreate(SQLiteDatabase db) {
	  // TODO Auto-generated method stub
	 }


	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  // TODO Auto-generated method stub
	}
	 
	 
	 // DB�� �ֳ� üũ�ϱ�
	 public boolean isCheckDB(Context mContext){
	      String filePath = "/data/data/com.example.binarysearch/databases/wordlist.db";
	      File file = new File(filePath);
	       
	       if (file.exists()) {
	           return false; // ������ DB����
	       }
	       return false;
	 }
	 
	 // DB�� �����ϱ�
	 // assets�� /db/xxxx.db ������ ��ġ�� ���α׷��� ���� DB�������� �����ϱ�
	 public void copyDB(Context mContext){
	     
	      AssetManager manager = mContext.getAssets();   //assets�� ���� ����
	      String folderPath = "/data/data/com.example.binarysearch/databases";
	      String filePath = "/data/data/com.example.binarysearch/databases/wordlist.db";
	      File folder = new File(folderPath);
	      File file = new File(filePath);
	       
	      FileOutputStream fos = null;
	      BufferedOutputStream bos = null;
	      try {
	InputStream is = manager.open("wordlist.db");   //������ assets�� db �ҷ�����
	BufferedInputStream bis = new BufferedInputStream(is);
	  
	           if (folder.exists()) {
	           }else{
	               folder.mkdirs();
	           }
	            
	        
	           if (file.exists()) {
	               file.delete();
	               file.createNewFile();
	           }
	            
	           fos = new FileOutputStream(file);
	           bos = new BufferedOutputStream(fos);
	           int read = -1;
	           byte[] buffer = new byte[1024];
	           while ((read = bis.read(buffer, 0, 1024)) != -1) {
	            bos.write(buffer, 0, read);
	           }
	  
	           bos.flush();
	          
	           bos.close();
	           fos.close();
	           bis.close();
	           is.close();
	  
	       } catch (IOException e) {
	       Log.e("ErrorMessage : ", e.getMessage());
	      } 
	 }


	}
	 