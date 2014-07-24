package com.example.searchword;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener
{
  SearchControlAddDAO mDynamicControlAddDAO;
  ImageButton mIbtnSearch;
  AutoCompleteTextView mEtSearchTxt;
  Cursor mTbNoteCursor;
  
  private LinearLayout mLlNoteItemList;
  int mIndex = 1;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    mLlNoteItemList = (LinearLayout) findViewById(R.id.llNoteItemList);
    mIbtnSearch = (ImageButton) findViewById(R.id.ibtnSearch);
    mEtSearchTxt = (AutoCompleteTextView) findViewById(R.id.etSearchTxt);
    
    mIbtnSearch.setOnClickListener(this);
    
    mDynamicControlAddDAO = new SearchControlAddDAO(getApplicationContext());
  }
  
  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    
    try
    {
      if (mTbNoteCursor != null)
        mTbNoteCursor.close();
      
      mDynamicControlAddDAO.close(); 
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  // 버튼의 OnClick 이벤트 처리 
  @Override
  public void onClick(View v)
  {
    switch(v.getId())
    {
      case R.id.ibtnSearch: // Search Button
      mIbtnSearchOnClick();

        break;
      default:
        break;
    }
  }
  private void mIbtnSearchOnClick()
  {
    String searchText = mEtSearchTxt.getText().toString();
    mLlNoteItemList.removeAllViews();
    mIndex = 1;
    findNoteList(searchText);
  }
  
  private void findNoteList(String searchText)
  {
    String note;
    
    mTbNoteCursor = mDynamicControlAddDAO.selectNoteList(searchText);
    
    if (mTbNoteCursor != null && mTbNoteCursor.getCount() > 0)
    {
      while (mTbNoteCursor.moveToNext())
      {
        note = mTbNoteCursor.getString(mTbNoteCursor.getColumnIndex(TbNote.NOTE));
        
        RelativeLayout rlNoteItem = new RelativeLayout(this);
        
        TextView tvNote = new TextView(this); // NOTE
        
        tvNote.setId(mIndex);
        tvNote.setText(note);
        tvNote.setSingleLine(true);
        tvNote.setTextSize(18);
        tvNote.setGravity(Gravity.CENTER_VERTICAL);
        tvNote.setTextColor(Color.BLACK);
        tvNote.setPadding(15, 0, 10, 0);
        tvNote.setSingleLine(true);
        tvNote.setWidth(300);
        tvNote.setHeight(100);   
        
        rlNoteItem.addView(tvNote);
        
        RelativeLayout.LayoutParams itemTvNoteParam = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        itemTvNoteParam.addRule(RelativeLayout.CENTER_IN_PARENT);
        
        tvNote.setLayoutParams(itemTvNoteParam);
        
        mLlNoteItemList.addView(rlNoteItem);
        
        mIndex = mIndex + 1;
      }

      mTbNoteCursor.deactivate();
      mTbNoteCursor.close();
    }
  }
}
