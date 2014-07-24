package com.example.searchword;

import android.content.Context;
import android.database.Cursor;


public class SearchControlAddDAO
{
  SearchDBHelper mDb;
  
  public SearchControlAddDAO (Context context)
  {
    mDb = SearchDBHelper.getInstance(context);
  }
  
  public void close()
  {
    mDb.close();
  }
  
  public Cursor selectNoteList(String searchText)
  {
    StringBuffer sbSql = new StringBuffer();
    
    sbSql.append("select                \n");
    sbSql.append("    '0' as _id        \n");
    sbSql.append("    , note            \n");
    sbSql.append("    , content         \n");
    sbSql.append("from                  \n");
    sbSql.append("     tb_note          \n");
    sbSql.append("where                 \n");
    sbSql.append("    note like '%" + searchText + "%' \n");
    sbSql.append("order by note \n");
   
    Cursor c = null;
    
    try
    {
      c = mDb.select(sbSql.toString());      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
    return c;
  }
}

