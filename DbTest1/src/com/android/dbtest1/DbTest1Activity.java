package com.android.dbtest1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DbTest1Activity extends Activity implements OnClickListener {

	EditText editName, editCost,editNewName,editNewCost;
	Button btAdd,btDelete,btUpdate;
	ListView listBook;
	String outputFilePath;
	BookDbAdapter bookAdapter;
	Cursor cursor ;
	BookCursorAdapter cursorAdapter;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
		
	}

	public void init() {
		editName = (EditText) findViewById(R.id.editText1);
		editCost = (EditText) findViewById(R.id.editText2);
		editNewName = (EditText) findViewById(R.id.editText3);
		editNewCost = (EditText) findViewById(R.id.editText4);
		listBook = (ListView) findViewById(R.id.listView1);
		btAdd = (Button) findViewById(R.id.button1);
		btDelete= (Button) findViewById(R.id.button2);
		btUpdate= (Button) findViewById(R.id.button3);
		if (btAdd != null)
			btAdd.setOnClickListener(this);
		if (btDelete != null)
			btDelete.setOnClickListener(this);
		if (btUpdate != null)
			btUpdate.setOnClickListener(this);

		bookAdapter = new BookDbAdapter(this);
		copyDbToPkg();
		bookAdapter.open(outputFilePath);
		setListFromDb();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btAdd) {// Add book into db.
			 
			try {
				String strName = editName.getText().toString();
				String strCost = editCost.getText().toString();
				double digitCost = 0;
				Log.i("BTN CLICKED", strName + ":" + strCost);

				if (strName != null && strCost != null) {
					 digitCost = Integer.parseInt(strCost);
					 bookAdapter.insertBook(strName, digitCost);
					 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(v == btDelete){
			String strId=editNewName.getText().toString();
			int deleteId=-1;
			deleteId= Integer.parseInt(strId);
			bookAdapter.deleteBook(deleteId);
			
		}
		else if(v == btUpdate){
			String strId=editName.getText().toString();
			int deleteId=-1;
			deleteId= Integer.parseInt(strId);
			String strName = editNewName.getText().toString();
			String strCost = editNewCost.getText().toString();
			double newCost=	Double.parseDouble(strCost);	
			bookAdapter.updateBook(deleteId,strName,newCost);
			
		}
		
		notifyListDataChanged();
	}
	public void notifyListDataChanged(){
		 cursorAdapter.getCursor().requery();
		 cursorAdapter.notifyDataSetChanged();
	}
	public void setListFromDb() {

		 cursor = bookAdapter.fetchAllBooks();
		Log.i("BTN CLICKED", "cursor:" + cursor.getCount());
		cursorAdapter=new BookCursorAdapter(this, cursor);
		 listBook.setAdapter(cursorAdapter);
		
	}

	public void copyDbToPkg() {
		// DB Copy
		try {
			// ** 이부분을 넣지않으면 몇몇 기기에서 데이터베이스가 정상적으로 복사되지 않습니다 **//
			// bookAdapter.mDb= bookAdapter.getReadableDatabase();
			// bookAdapter.mDb.close();
			outputFilePath = this.getFilesDir() + "/book.db";
			if (!isFileExists("book", this.getFilesDir().toString())) {

				// InputStream is =
				// this.getResources().openRawResource(R.raw.book);
				InputStream is = this.getAssets().open("book.db");
				// **zip파일일 경우
				// ZipInputStream zis = new ZipInputStream(is);
				// zis.getNextEntry();

				FileOutputStream fos = new FileOutputStream(outputFilePath);
				byte[] buffer = new byte[4096];
				while (true) {
					int readSize = is.read(buffer, 0, buffer.length);
					if (readSize == -1)
						break;
					fos.write(buffer, 0, readSize);
				}

				fos.close();
				// zis.close();
				is.close();
			} else
				Log.i("TEST_KSI", "db is already copied");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public boolean isFileExists(String fileName, String filePath) {
		boolean result = false;
		File file = new File(filePath, fileName + ".db");
		if (file.exists())
			result = true;

		return result;
	}

}
