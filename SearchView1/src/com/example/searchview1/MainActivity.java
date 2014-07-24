package com.example.searchview1;

 

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

 

public class MainActivity extends Activity {

 

   DB mHelper;
   EditText mText;


   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mHelper = new DB(this);
      mText = (EditText)findViewById(R.id.edittext);
   }
 
   public void mOnClick(View v) {
      SQLiteDatabase db;
      switch (v.getId()) { 
      
   //데이터 조회

   case R.id.select:
      db = mHelper.getReadableDatabase();

      // 안드로이드의 Cursor 클래스는 ResultSet과 유사하다.
      Cursor cursor;

     // SQL 명령으로 읽기
     cursor = db.rawQuery("SELECT rowid, word", null);
  
     String Result = "";
     while (cursor.moveToNext()) {
     String rowid = cursor.getString(0);
      String word = cursor.getString(1);
      Result += (rowid + ", " + word + "\n");
      }

         if (Result.length() == 0) {
            mText.setText("데이터가 없습니다");
         } else {
            mText.setText(Result);
         }
         cursor.close();
         mHelper.close();
         break;
      }
   }
}



