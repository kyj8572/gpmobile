package com.example.searchword;

import android.provider.BaseColumns;

public class TbNote implements BaseColumns
{
  public static final String TABLE_NAME = "tb_note";

  public static final String NOTE = "note";
  public static final String CONTENT = "content";  

  public static final String[] ALL_COLUMNS = { 
	  TbNote.NOTE
    , TbNote.CONTENT
    };
  
  public String toString()
  {
    StringBuffer returnValue = new StringBuffer();
    
    returnValue.append("[note : " + note + "], ");
    returnValue.append("[content : " + content + "] ");
    
    return returnValue.toString();
  }
  

  private String note;
  private String content;
  
 
  public String getNote()
  {
    return note;
  }
  public void setNote(String note)
  {
    this.note = note;
  }
  public String getContent()
  {
    return content;
  }
  public void setContent(String content)
  {
    this.content = content;
  }
}

