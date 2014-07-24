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
     TestHelper myHelper;    //���ۺ���
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
                              // ������� DB�� ����, DB�� �������� ������ onCreate ȣ���
                              // ������ �ٲ��� onUpgrade�� ȣ���
                             db = myHelper.getWritableDatabase();
                             // insert �޼���� ����
                             row = new ContentValues();
                             row.put("eng", "boy");
                             row.put("col2", "�ӽ���");
                             db.insert("tbl_sample", null, row);
                             // DML�� ���� INSERT
                             db.execSQL("INSERT INTO tbl_sample VALUES (null, '080', '�ٰ���');");
                             myHelper.close();
                             mText.setText("Insert Success");
                             break;
                    }
           }
}

 

 

//�߻�Ŭ���� android.database.sqlite.SQLiteOpenHelper ���

class TestHelper extends SQLiteOpenHelper 
{
       // ������
       public TestHelper(Context context) 
       {
              // ����Ʈ��, DB����, Ŀ����Ŀ��(ǥ��Ŀ�� null), ����
              super(context, "Test.db", null, 1);
        }

        // DB�� ó�� ����� ����
        public void onCreate(SQLiteDatabase db) 
        {
              db.execSQL("CREATE TABLE tbl_sample " +
                            "(row_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "col1 TEXT, col2 TEXT);");
         }

         // �̹������ϴ� DB�� ���׷��̵�
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
              db.execSQL("DROP TABLE IF EXISTS tbl_sample");
              onCreate(db);
         }
}

