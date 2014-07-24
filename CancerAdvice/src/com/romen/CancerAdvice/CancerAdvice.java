package com.romen.CancerAdvice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.Toast;

public class CancerAdvice extends TabActivity {
    /** Called when the activity is first created. */
	//DB옮기기
	public SQLiteDatabase db;
	public Cursor cursor;
	public SimpleCursorAdapter Adapter=null;
	public SimpleCursorAdapter AdapterStomach=null;
	public SimpleCursorAdapter AdapterColon=null;
	public SimpleCursorAdapter AdapterLiver=null;
	public SimpleCursorAdapter AdapterBreast=null;
	public SimpleCursorAdapter AdapterCervix=null;
	public static final String ROOT_DIR = "/data/data/com.romen.CancerAdvice/databases/";
	ProductDBHelper mHelper;
	public void setDB() {
		  File folder = new File(ROOT_DIR);
		  if(folder.exists()) {
			 
		  }
		  else {
		   folder.mkdirs();
		   //Toast.makeText(this, "폴더생성", Toast.LENGTH_LONG).show();
		  }

		   AssetManager assetManager = getResources().getAssets();
		   File outfile = new File(ROOT_DIR+"kmdc.db"); //--폰에 위치할 경로
		  
		   InputStream is = null; 
		     
		   FileOutputStream fo = null;
		   
		   long filesize = 0;
		         
		  		     
		   try {
		    // --asset 폴더 및 복사할 DB 지정
			   is = assetManager.open("kmdc.db", AssetManager.ACCESS_BUFFER);  
		     filesize = is.available(); //--사이즈 검증
		     
		    // 파일이 없거나 패키지 폴더에 설치된 DB파일이 포함된 DB파일 보다 크기가 같지않을 경우 DB파일을 덮어 쓴다.
		     if (outfile.length() <= 0) {
		     byte[] tempdata = new byte[(int) filesize];
		     is.read(tempdata); 
		     is.close(); 
		     outfile.createNewFile();
		     fo = new FileOutputStream(outfile);
		     fo.write(tempdata);
		     fo.close();    
		    }
		     else
		     {
		    	 //Toast.makeText(this, "db있음", Toast.LENGTH_LONG).show();
		     
		     }
		   } catch (IOException e) { 
			  Toast.makeText(this, "db이동실패", Toast.LENGTH_LONG).show();
		   }   
		  } 

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	setDB();
        super.onCreate(savedInstanceState);
        TabHost mTab=getTabHost();
        //ArrayAdapter<CharSequence> Adapter;
        LayoutInflater inflater=LayoutInflater.from(this);
        inflater.inflate(R.layout.main, mTab.getTabContentView(),true);
        	
        	mTab.addTab(mTab.newTabSpec("tag")
        	.setIndicator("위암")
        	.setContent(R.id.opt_stomach));
        	
        	mTab.addTab(mTab.newTabSpec("tag")
                	.setIndicator("대장암")
                	.setContent(R.id.opt_colon));
        	
        	mTab.addTab(mTab.newTabSpec("tag")
                	.setIndicator("간암")
                	.setContent(R.id.opt_liver));
        	mTab.addTab(mTab.newTabSpec("tag")
                	.setIndicator("유방암")
                	.setContent(R.id.opt_breast));
        	
        	mTab.addTab(mTab.newTabSpec("tag")
                	.setIndicator("자궁경부암")
                	.setContent(R.id.opt_cervix));
        	
        	mHelper=new ProductDBHelper(this);
        	
        	db=mHelper.getWritableDatabase();
        	cursor=db.rawQuery("Select * from CanGuide where CanKnd='위암'",null);
        	startManagingCursor(cursor);
				//cursor.moveToLast();
				//String str=cursor.getString(5);
				//Toast.makeText(this,str, Toast.LENGTH_LONG).show();
        	
        	ListView list=(ListView)findViewById(R.id.stomach_list);
        	AdapterStomach=new SimpleCursorAdapter(list.getContext(), R.layout.mylist, cursor, 
        			new String[]{"DxCodName"},new int[]{R.id.text1});
        	
        	list.setAdapter(AdapterStomach);
        	
        	
        	
    	cursor=db.rawQuery("Select * from CanGuide where CanKnd='대장암'",null);
       	startManagingCursor(cursor);
			
      	
       	ListView listColon=(ListView)findViewById(R.id.colon_list);
       	AdapterColon=new SimpleCursorAdapter(listColon.getContext(), R.layout.mylist, cursor, 
        			new String[]{"DxCodName"},new int[]{R.id.text1});
       	
     	listColon.setAdapter(AdapterColon);
        	
        	
        	
        	
        	cursor=db.rawQuery("Select * from CanGuide where CanKnd='간암'",null);
        	startManagingCursor(cursor);
			        	ListView listLiver=(ListView)findViewById(R.id.liver_list);
			        	AdapterLiver=new SimpleCursorAdapter(listLiver.getContext(), R.layout.mylist, cursor, 
        			new String[]{"DxCodName"},new int[]{R.id.text1});
        	
        	listLiver.setAdapter(AdapterLiver);
        	
        	
        	
        	cursor=db.rawQuery("Select * from CanGuide where CanKnd='유방암'",null);
        	startManagingCursor(cursor);
			ListView listBreast=(ListView)findViewById(R.id.breast_list);
			AdapterBreast=new SimpleCursorAdapter(listBreast.getContext(), R.layout.mylist, cursor, 
        			new String[]{"DxCodName"},new int[]{R.id.text1});
        	
        	listBreast.setAdapter(AdapterBreast);
        	
        	
        	cursor=db.rawQuery("Select * from CanGuide where CanKnd='자궁암'",null);
        	startManagingCursor(cursor);
			ListView listCervix=(ListView)findViewById(R.id.cervix_list);
			AdapterCervix=new SimpleCursorAdapter(listCervix.getContext(), R.layout.mylist, cursor, 
        			new String[]{"DxCodName"},new int[]{R.id.text1});
        	
        	listCervix.setAdapter(AdapterCervix);
        	
        	
        	
        	
        	list.setOnItemClickListener(new OnItemClickListener() {
        		  @Override
        		  public void onItemClick(AdapterView<?> parent, View v, int position,
        		  long id) {
        		  cursor=db.rawQuery("Select * from CanGuide where CanKnd='위암'",null);
            	  startManagingCursor(cursor);	  
        		  Cursor c = (Cursor)AdapterStomach.getItem(position);
        		  String note = c.getString(5); 
        		  AlertDialog.Builder bld=new AlertDialog.Builder(CancerAdvice.this);
        		  bld.setTitle(c.getString(3));
        		  bld.setMessage(note);
        		  bld.setIcon(R.drawable.ic_menu_edit);
        		  bld.setPositiveButton("Close", new DialogInterface.OnClickListener() {
  					
  					@Override
  					public void onClick(DialogInterface dialog, int which) {
  						// TODO Auto-generated method stub
  						
  					}
  				});
        		  bld.show();
        		  //Toast.makeText(getApplicationContext(), note, Toast.LENGTH_LONG).show();
        		  			}
        		  });
        	
        	
        	
        	listColon.setOnItemClickListener(new OnItemClickListener() {
        		  @Override
        		  public void onItemClick(AdapterView<?> parent, View v, int position,
        		  long id) {
        		  cursor=db.rawQuery("Select * from CanGuide where CanKnd='대장암'",null);
        		  startManagingCursor(cursor);	  
        		  Cursor c = (Cursor)AdapterColon.getItem(position);
        		  String note = c.getString(5); 
        		  AlertDialog.Builder bld=new AlertDialog.Builder(CancerAdvice.this);
        		  bld.setTitle(c.getString(3));
        		  bld.setMessage(note);
        		  bld.setIcon(R.drawable.ic_menu_edit);
        		  bld.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
        		  bld.show();
        		  //Toast.makeText(getApplicationContext(), note, Toast.LENGTH_LONG).show();
        		  			}
        		  });
        	
        	listLiver.setOnItemClickListener(new OnItemClickListener() {
      		  @Override
      		  public void onItemClick(AdapterView<?> parent, View v, int position,
      		  long id) {
      		  cursor=db.rawQuery("Select * from CanGuide where CanKnd='간암'",null);
      		  startManagingCursor(cursor);	  
      		  Cursor c = (Cursor)AdapterLiver.getItem(position);
      		  String note = c.getString(5); 
      		  AlertDialog.Builder bld=new AlertDialog.Builder(CancerAdvice.this);
      		  bld.setTitle(c.getString(3));
      		  bld.setMessage(note);
      		  bld.setIcon(R.drawable.ic_menu_edit);
      		  bld.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
      		  bld.show();
      		  //Toast.makeText(getApplicationContext(), note, Toast.LENGTH_LONG).show();
      		  			}
      		  });
        	
        	
        	listBreast.setOnItemClickListener(new OnItemClickListener() {
        		  @Override
        		  public void onItemClick(AdapterView<?> parent, View v, int position,
        		  long id) {
        		  cursor=db.rawQuery("Select * from CanGuide where CanKnd='유방암'",null);
        		  startManagingCursor(cursor);	  
        		  Cursor c = (Cursor)AdapterBreast.getItem(position);
        		  String note = c.getString(5); 
        		  AlertDialog.Builder bld=new AlertDialog.Builder(CancerAdvice.this);
        		  bld.setTitle(c.getString(3));
        		  bld.setMessage(note);
        		  bld.setIcon(R.drawable.ic_menu_edit);
        		  bld.setPositiveButton("Close", new DialogInterface.OnClickListener() {
  					
  					@Override
  					public void onClick(DialogInterface dialog, int which) {
  						// TODO Auto-generated method stub
  						
  					}
  				});
        		  bld.show();
        		  //Toast.makeText(getApplicationContext(), note, Toast.LENGTH_LONG).show();
        		  			}
        		  });
        	
        	
        	listCervix.setOnItemClickListener(new OnItemClickListener() {
      		  @Override
      		  public void onItemClick(AdapterView<?> parent, View v, int position,
      		  long id) {
      		  cursor=db.rawQuery("Select * from CanGuide where CanKnd='자궁암'",null);
      		  startManagingCursor(cursor);	  
      		  Cursor c = (Cursor)AdapterCervix.getItem(position);
      		  String note = c.getString(5); 
      		  AlertDialog.Builder bld=new AlertDialog.Builder(CancerAdvice.this);
      		  bld.setTitle(c.getString(3));
      		  bld.setMessage(note);
      		  bld.setIcon(R.drawable.ic_menu_edit);
      		  bld.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
      		  bld.show();
      		  //Toast.makeText(getApplicationContext(), note, Toast.LENGTH_LONG).show();
      		  			}
      		  });
      	
        	
        	
        	
        	
    }
    
    
  class ProductDBHelper extends SQLiteOpenHelper{

	public ProductDBHelper(Context context) {
		super(context, "kmdc.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	  
  }
    
  
	 
//cursor.moveToLast();
	//String str=cursor.getString(5);
	//Toast.makeText(this,str, Toast.LENGTH_LONG).show();

//   AdapterView.OnItemClickListener mItemClickListener=new AdapterView.OnItemClickListener() {
//
//	@Override
//	public void onItemClick(AdapterView parent, View view, int position, long id) {
//		// TODO Auto-generated method stub
//		//cursor=db.rawQuery("Select * from CanGuide where DxCodName='"+parent.getSelectedItem()+"'",null);
////    	
////		
//		
////		startManagingCursor(cursor);
////		cursor.move(position);
////		String str=cursor.getString(5);
////		
//		//Toast.makeText(CancerAdvice.this,Integer.toString(position), Toast.LENGTH_LONG).show();
//		//Toast.makeText(CancerAdvice.this,parent.Adapter.getItem(position), Toast.LENGTH_LONG).show();
//		//String str=cursor.getString(5);
//	}
//};
}