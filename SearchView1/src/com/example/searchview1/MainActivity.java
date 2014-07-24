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
      
   //������ ��ȸ

   case R.id.select:
      db = mHelper.getReadableDatabase();

      // �ȵ���̵��� Cursor Ŭ������ ResultSet�� �����ϴ�.
      Cursor cursor;

     // SQL ������� �б�
     cursor = db.rawQuery("SELECT rowid, word", null);
  
     String Result = "";
     while (cursor.moveToNext()) {
     String rowid = cursor.getString(0);
      String word = cursor.getString(1);
      Result += (rowid + ", " + word + "\n");
      }

         if (Result.length() == 0) {
            mText.setText("�����Ͱ� �����ϴ�");
         } else {
            mText.setText(Result);
         }
         cursor.close();
         mHelper.close();
         break;
      }
   }
}



