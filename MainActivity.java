package com.example.administrator.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addata = (Button)findViewById(R.id.add_data);
        addata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest" +
                        ".provider/book");
                ContentValues values = new ContentValues();
                values.put("name","A Clash of Kings");
                values.put("author","George Martin");
                values.put("pages",1040);
                values.put("price",22.85);
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
            }
        });
        Button querydata = (Button)findViewById(R.id.query_data);
        querydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest" +
                        ".provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,
                        null);
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        String name =cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","book name is "+name);
                        Log.d("MainActivity","book author is "+author);
                        Log.d("MainActivity","book pages are "+pages);
                        Log.d("MainActivity","book price is "+price);
                    }
                    cursor.close();
                }

            }
        });


        Button updateData=(Button) findViewById(R.id.update);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest" +
                        ".provider/book/"+newId);
                ContentValues values=new ContentValues();
                values.put("name","A SMALL CAT");
                values.put("pages",2000);
                values.put("price",13.14);
                getContentResolver().update(uri,values,null,null);
            }
        });
        Button deleteData=(Button) findViewById(R.id.delete);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest" +
                        ".provider/book/"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}
