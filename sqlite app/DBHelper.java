package com.example.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String dbname="mydb";
    private static int VERSION=1;
    SQLiteDatabase sqLiteDatabase;
    private final String TAG="DBHelper";

    public DBHelper(Context context) {
        super(context, dbname, null, VERSION);
        Log.i(TAG,"Dbhelper constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(prnno int primary key,sname varchar(30) not null," +
                "email varchar(30), phone varchar(10), course varchar(3))");
        Log.i(TAG,"Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
     boolean addRecord(int prn,String sn, String mail, String ph, String crc) {
         sqLiteDatabase = getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("prnno", prn);
         contentValues.put("sname", sn);
         contentValues.put("email", mail);
         contentValues.put("phone", ph);
         contentValues.put("course", crc);

         long i = sqLiteDatabase.insert("student", null, contentValues);
         if (i > 0) {
             Log.i("DBHelper", "Record added");
             return true;
         } else {
          Log.i(TAG,"Some error in adding");
             return false;
         }
     }

     Cursor showRecord(int prn)
     {
         sqLiteDatabase=getReadableDatabase();
         Log.i(TAG,"inside show record");
         Cursor cursor=sqLiteDatabase.rawQuery("select * from student where " +
                 " prnno=" + prn , null);
         return cursor;
     }

     boolean updateRecord(int prn, String nm, String mail, String ph, String crc)
     {
         sqLiteDatabase=getWritableDatabase();
         String p=String.valueOf(prn);
         ContentValues contentValues=new ContentValues();
         contentValues.put("prnno",prn);
         contentValues.put("sname",nm);
         contentValues.put("email",mail);
         contentValues.put("phone",ph);
         contentValues.put("course",crc);
         long i = sqLiteDatabase.update("student", contentValues, "prnno = ?", new String[]{p});
         if (i > 0) {
             Log.i(TAG, "Record updated");
             return true;
         } else {
             Log.i(TAG, "Record not updated");
             return false;
         }


     }
     boolean deleteRecord(int prn)
     {
         sqLiteDatabase=getWritableDatabase();
         String p=String.valueOf(prn);
         long i=sqLiteDatabase.delete("student","prnno=?", new String[]{p});
         if(i>0)
             return true;
         else
             return false;
     }
}
