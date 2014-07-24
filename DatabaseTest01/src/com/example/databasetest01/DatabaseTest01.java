package com.example.databasetest01;
 
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
 
public class DatabaseTest01 extends Activity {
 
    dbHelper helper;
    SQLiteDatabase db;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        helper = new dbHelper(this);
       db = helper.getWritableDatabase();
       
        //���̺��� ��� ���ڵ带 Ŀ����ü�� �����´�.
        Cursor cursor = db.rawQuery("select * from contact",null);
       
        //startManagingCursor(cursor) ������ ��Ƽ��Ƽ�� Ŀ�� ��ü�� �����ϵ��� �Ѵ�.
        //�� ��Ƽ��Ƽ�� �����ֱ�� Ŀ���� �����ֱ⸦ ��ġ��Ű�°��̴�.
       //void android.app.Activity.startManagingCursor(Cursor c)
        startManagingCursor(cursor);
       
        String[] from = {"name", "tel"};
        int[] to = {android.R.id.text1, android.R.id.text2};
       SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,cursor,from,to);
        //android.R.layout.simple_list_item_2��� ǥ�� ���̾ƿ��� ����ϴ� Ŀ�� ����� ��ü�� �����Ѵ�. �� ǥ�� ���̾ƿ��� 2���� �ؽ�Ʈ�並
        //������ ������ ù��° �ؽ�Ʈ��(android.R.id.text1)�� "name"�ʵ带 ǥ���ϰ� �ι�° �ؽ�Ʈ ��(android.R.id.text2)�� "tel"�ʵ带 ǥ���ϵ��� �����Ѵ�.
       
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }
 
}
 
 
class dbHelper extends SQLiteOpenHelper{
   
   
    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION =1;
  
   
    /*
     *���� SQLiteOpenHelperŬ������ ��ӹ��� dbHelperŬ������ ���� �Ǿ� �ִ�. �����ͺ��̽� ���� �̸��� "mycontacts.db"���ǰ�,
    *�����ͺ��̽� ������ 1�� �Ǿ��ִ�. ���� �����ͺ��̽��� ��û�Ǿ��µ� �����ͺ��̽��� ������ onCreate()�� ȣ���Ͽ� �����ͺ��̽�
     *������ �������ش�.
     */
   
   public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
           
    }
   
    @Override
    public void onCreate(SQLiteDatabase db) {
       
        //���̺� ����
        db.execSQL("CREATE TABLE contact (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, tel TEXT);");
       
       //���̺� �ʱⰪ ����(insert)
        for(int i=0; i<20; i++){
            db.execSQL("INSERT INTO contact VALUES(null, 'This is a sample data "+i+"','010-1234-100"+i+"');");
        }      
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXITS contact");
        onCreate(db);  
    }
   
}
