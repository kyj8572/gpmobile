package com.example.searchword;

public class DatabaseDDL
{
  public static final String DATABASE_NAME = "note.db";
  public static final int DATABASE_VERSION = 1;
  
  // tb_note
  public static final String TABLE_CREATE_TB_NOTE = "CREATE TABLE IF NOT EXISTS "
    + TbNote.TABLE_NAME + " ( "  
    + TbNote.NOTE     + " text, "
    + TbNote.CONTENT  + " text"
    + ");";
  
  // tb_note init data
  public static final String INIT_DATA1 = "insert into tb_note (note, content) values ('Test data1', '1�� �׽�Ʈ �������Դϴ�.')";
  public static final String INIT_DATA2 = "insert into tb_note (note, content) values ('Test data2', '2�� �׽�Ʈ �������Դϴ�.')";
  public static final String INIT_DATA3 = "insert into tb_note (note, content) values ('Test data3', '3�� �׽�Ʈ �������Դϴ�.')";
  public static final String INIT_DATA4 = "insert into tb_note (note, content) values ('Test data4', '4�� �׽�Ʈ �������Դϴ�.')";
}


