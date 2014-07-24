package com.example.searchview;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Window;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.Toast;

public class SearchActivity extends Activity {
	
	private final static String DB_NAME="wordlist.db";
	private final static String DB_TABEL="word";
	private final static int DB_VERSION=1;
	
	private TextView lbldata;
	private SQLiteDatabase db;
	private int datE;
	
	@Override
	public void onCreate(Bundle b){
		super.onCreate(b);
		startSet();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(0, 0, 0));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		lbldata=new TextView(this);
		lbldata.setText("day");
		lbldata.setTextSize(40.0f);
		lbldata.setTextColor(Color.rgb(255, 255, 255));
		lbldata.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));
		layout.addView(lbldata);
		
		DBHelper dbHelper=new DBHelper(this);
		db=dbHelper.getWritableDatabase();
	}
	@Override
	public void onStart()
	{
		try{
			super.onStart();
			datE=new Date().getDate();
			datE%=6;
			lbldata.setText(readDB());
		}catch(Exception e){
			Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
			
		}
	}
	
	public String readDB()
	throws Exception
	{
		Cursor c=db.query(DB_TABEL,new String[]{"id","info"},"id="+datE,null,null,null,null);
		
		if(c.getCount()==0) throw new Exception();
		c.moveToFirst();
		String str=c.getString(1);
		c.close();
		return str;
	}
	private static class DBHelper extends SQLiteOpenHelper{
		private static final String DB_TABLE = null;
		public DBHelper(Context context){
			super(context,DB_NAME,null,DB_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db){
			db.execSQL("create table if net exists"+DB_TABLE+"(id text primary key,info text)");
		}
		@Override
		public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
			db.execSQL("drop table if exists"+DB_TABLE);
			onCreate(db);
		}
	}
	
	public void startSet(){
		AssetManager am=null;
		InputStream[] arrls = new InputStream[1];
		BufferedInputStream[] arrBis=new BufferedInputStream[1];
		
		FileOutputStream fos =null;
		BufferedOutputStream bos=null;
		try
		{ File f= new File("/data/data/com.example.searchview/databases/wordlist.db");
		if(f.exists())
		{ f.delete();
		f.createNewFile();
		}
		am=this.getResources().getAssets();
		arrls[0]=am.open("wordlist.db");
		arrBis[0]=new BufferedInputStream(arrls[0]);
		
		fos=new FileOutputStream(f);
		bos=new BufferedOutputStream(fos);
		int read=-1;
		byte[] buffer= new byte[1024];
		
		while((read=arrBis[0].read(buffer,0,1024))!=-1)
		{
			bos.write(buffer,0,read);
		}
		bos.flush();
		
		Toast.makeText(this, "파일읽어오기성공", Toast.LENGTH_SHORT).show();
		}
		catch(Exception e){
			Toast.makeText(this, "파일읽어오기실패", Toast.LENGTH_LONG).show();
			finish();
			
		}
		
	}
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event){
			if(keyCode==4)finish();
			return true;
		}
		
}