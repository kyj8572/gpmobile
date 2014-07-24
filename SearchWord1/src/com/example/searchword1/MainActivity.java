package com.example.searchword1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity 

{
     TestHelper myHelper;    //헬퍼변수
     EditText mText;

      public void onCreate(Bundle savedInstanceState) 

     {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            myHelper = new TestHelper(this);
            mText = (EditText)findViewById(R.id.edittext);
        }
 
         public void mOnClick(View v) 
        {
               SQLiteDatabase db;
               ContentValues row;
               switch (v.getId()) 

               {
                      case R.id.insert:
                              // 쓰기모드로 DB를 오픈, DB가 존재하지 않으면 onCreate 호출됨
                              // 버전이 바뀐경우 onUpgrade가 호출됨
                             db = myHelper.getWritableDatabase();
                             // insert 메서드로 삽입
                             row = new ContentValues();
                             row.put("eng", "boy");
                             row.put("col2", "머스마");
                             db.insert("tbl_sample", null, row);
                             // DML문 실행 INSERT
                             db.execSQL("INSERT INTO tbl_sample VALUES (null, '080', '바가지');");
                             myHelper.close();
                             mText.setText("Insert Success");
                             break;
                    }
           }
}

 

 

//추상클래스 android.database.sqlite.SQLiteOpenHelper 상속

class TestHelper extends SQLiteOpenHelper 
{
       // 생성자
       public TestHelper(Context context) 
       {
              // 컨텍트스, DB파일, 커스텀커서(표준커서 null), 버전
              super(context, "Test.db", null, 1);
        }

        // DB가 처음 만들어 질때
        public void onCreate(SQLiteDatabase db) 
        {
              db.execSQL("CREATE TABLE tbl_sample " +
                            "(row_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "col1 TEXT, col2 TEXT);");
         }

         // 이미존재하는 DB를 업그레이드
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
              db.execSQL("DROP TABLE IF EXISTS tbl_sample");
              onCreate(db);
         }
}

