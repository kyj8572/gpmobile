package kidsbear.cursoradapter;

import android.content.Context; 
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

 String sql;
 
 public DBHelper(Context context, String name, CursorFactory factory, int version) {
  super(context, name, factory, version);
 }

 @Override
 public void onCreate(SQLiteDatabase db) {
  sql = "CREATE TABLE test ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
    " name TEXT, age TEXT);";
  db.execSQL(sql);
  
  db.execSQL("INSERT INTO TEST VALUES(NULL, 'KIDSBEAR', '22');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, 'KIDS', '12');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, 'Ű���', '30');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, 'ȫ�浿', '66');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, 'ö��', '24');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, '����', '21');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, '������', '42');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, '������', '25');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, '���ٶ˲�', '11');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, '����̵�', '52');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, '�Ӳ���', '32');");
  db.execSQL("INSERT INTO TEST VALUES(NULL, '������', '19');");
 }

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  //������ ���׷��̵� ���� ��� �۾��� ������ �ۼ��մϴ�.
 }

}


 