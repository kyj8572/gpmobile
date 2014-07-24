package com.android.dbtest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookDbAdapter extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "book";
	private static final String DATABASE_TABLE = "book";
	private static final int DATABASE_VERSION = 1;
	SQLiteDatabase mDb = null;

	
	private static final String TAG = "BookdbAdapter";
	public BookDbAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	

	public void open(String path) throws SQLException {

		mDb = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.OPEN_READWRITE);
		if (mDb.isOpen())
			Log.i(TAG, "open db");
		else
			Log.i(TAG, "open db failed");

	}

	public Cursor fetchAllBooks() {
		Log.i(TAG, "fetch all Books");
		//return mDb.query(DATABASE_TABLE, new String[] { "_id","name", "cost" },
		//		null, null, null, null, null);
		if (mDb.isOpen())
			return  mDb.rawQuery("select * from "+DATABASE_TABLE , null);
		else
			return null;
	}

	public long insertBook(String name, double cost) {

		Log.i(TAG, "insert book into Db");
		long bl = 0;
		if (mDb.isOpen()) {
			try {
				ContentValues initialValues = new ContentValues();
				initialValues.put("name", name);
				initialValues.put("cost", cost);
				bl = mDb.insert(DATABASE_TABLE, null, initialValues);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bl;
	}
	
	public void updateBook(int id,String name,double cost){
		
		mDb.execSQL("UPDATE "+DATABASE_TABLE+" SET name='"+name+"',cost="+cost+" WHERE _id="+id);
		 
	}
	
	public void deleteBook(int id){
		

		mDb.execSQL("DELETE FROM "+DATABASE_TABLE+"  WHERE _id="+id);
		 
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

}
