package com.android.dbtest1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class BookCursorAdapter extends CursorAdapter {

	public BookCursorAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView txtId = (TextView) view.findViewById(R.id.textView1);
		TextView txtName = (TextView) view.findViewById(R.id.textView2);
		TextView txtCost = (TextView) view.findViewById(R.id.textView4);
		try {
			String id = null,name, cost;
			id=cursor.getString(0); 
			name = cursor.getString(1);
			 cost = cursor.getString(2);
			
			 txtId.setText(id);
			txtName.setText(name);
			txtCost.setText(cost);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.raw, parent, false);
		return v;
	}

}
