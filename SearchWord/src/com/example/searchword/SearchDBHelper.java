package com.example.searchword;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SearchDBHelper extends SQLiteOpenHelper
{
  private static SearchDBHelper mInstance;
  private static SQLiteDatabase mDb;

  // 생성자
  public SearchDBHelper (Context context, String name, CursorFactory factory, int version)
  {
    super (context, name, factory, version);
  }
  
  // 생성자
  private SearchDBHelper (final Context context)
  {
    super (context, DatabaseDDL.DATABASE_NAME, null, DatabaseDDL.DATABASE_VERSION);
  }
  
  // Initialize method
  public static void initialize (Context context)
  {
    if (mInstance == null)
    {
      mInstance = new SearchDBHelper(context);
      
      try
      {
        mDb = mInstance.getWritableDatabase(); 
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  // 싱글턴 인스턴스 리턴
  public static final SearchDBHelper getInstance(Context context)
  {
    initialize(context);
    return mInstance;
  }
  
  // DB Close 메서드
  public void close()
  {
    if (mInstance != null)
    {
      mDb.close();
      mInstance = null;
    }
  }
  
  // Select 쿼리 수행
  public Cursor select(String sql)
  {
    Cursor c = null;
    
    try
    {
      c = mDb.rawQuery(sql, null);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
    return c;
  }
  
  // DB가 생성될 때 한번만 호출됨.
  public void onCreate(SQLiteDatabase db) 
  {
    try
    {
      db.execSQL(DatabaseDDL.TABLE_CREATE_TB_NOTE);
      
      // data init
      db.execSQL(DatabaseDDL.INIT_DATA1);
      db.execSQL(DatabaseDDL.INIT_DATA2);
      db.execSQL(DatabaseDDL.INIT_DATA3);
      db.execSQL(DatabaseDDL.INIT_DATA4);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
  {
  }
}

