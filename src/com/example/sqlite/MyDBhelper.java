package com.example.sqlite;
   
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
    
public class MyDBhelper extends SQLiteOpenHelper{
    public static final String ID="_id";
    public static final String NAME="name";
    public static final String AGE="age";
    public static final String SEX="sex";
    public static final String TABLE_NAME="person"; 
 
    public MyDBhelper(Context context) {
        super(context,"persons", null,1);
    }
     
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+" TEXT NOT NULL,"
                +AGE+" INTEGER,"
                +SEX+" TEXT"+")");
        System.out.println("database created!");
    }
     
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("database update!");
    }
}