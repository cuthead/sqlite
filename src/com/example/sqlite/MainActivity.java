package com.example.sqlite;
   
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
    
public class MainActivity extends Activity implements OnClickListener{
    private Button create;
    private Button insert;
    private Button query;
    private Button update;
    private Button delete;
    private MyDBhelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    public static int counter=0;
    public String name,sex;
     
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
     
    private void initView(){
        create=(Button) findViewById(R.id.create);
        insert=(Button) findViewById(R.id.insert);
        query=(Button) findViewById(R.id.query);
        update=(Button) findViewById(R.id.update);
        delete=(Button) findViewById(R.id.delete);
        create.setOnClickListener(this);
        insert.setOnClickListener(this);
        query.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
    }
     
    private void createDB(){
        dbHelper=new MyDBhelper(this);
        db=dbHelper.getWritableDatabase();
        Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();
    }
     
    private void insertDB(){
        counter++;
        name="user"+counter;
        if(counter%2==0){
            sex="male";
        }else{
            sex="female";
        }
        dbHelper=new MyDBhelper(this);
        db=dbHelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("age",26);
        cv.put("sex",sex);
        db.insert(MyDBhelper.TABLE_NAME, null, cv);
        Toast.makeText(this, name+"insert", Toast.LENGTH_SHORT).show();
        db.close();
    }
     
    private void queryDB(){
        dbHelper=new MyDBhelper(this);
        SQLiteDatabase rdb=dbHelper.getReadableDatabase();
        cursor=rdb.query(MyDBhelper.TABLE_NAME, null, null, null, null, null, null);
        StringBuffer sb=new StringBuffer();
        while(cursor.moveToNext()){
            String rs=cursor.getInt(cursor.getColumnIndex(MyDBhelper.ID))+","+cursor.getString(cursor.getColumnIndex(MyDBhelper.NAME))+","+cursor.getInt(cursor.getColumnIndex(MyDBhelper.AGE))+","+cursor.getString(cursor.getColumnIndex(MyDBhelper.SEX))+"\n";
            sb.append(rs);
        }  
        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        db.close();
    }
     
    private void deleteDB(){
        dbHelper=new MyDBhelper(this);
        db=dbHelper.getWritableDatabase();
        String whereClause="_id>?";
        String[] whereArgs={String.valueOf(1)};
        db.delete(MyDBhelper.TABLE_NAME, whereClause, whereArgs);
        db.close();
        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
    }
     
    private void updateDB(){
        dbHelper=new MyDBhelper(this);
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("age", 21);
        String whereClause="name=?";
        String[] whereArgs={String.valueOf("Сǿ")};
        db.update(MyDBhelper.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
        Toast.makeText(this, "upate", Toast.LENGTH_SHORT).show();
    }
     
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.create:
            createDB();
            break;
       case R.id.insert:
           insertDB();
            break;
       case R.id.query:
            queryDB();
            break;
       case R.id.update:
           updateDB();
            break;
       case R.id.delete:
           deleteDB();
            break;
        }
    }
}