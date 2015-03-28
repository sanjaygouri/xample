package com.example.xample;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.os.Build;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends ActionBarActivity {
	Button b1;
	protected Object adapter;
	//ListView lv; 
	//ArrayList<String> list1;
	//public ArrayAdapter<String> a;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
    /*    lv = (ListView)findViewById(R.id.list);
        a  = new ArrayAdapter  (this,android.R.layout.simple_list_item_multiple_choice);
		SQLiteDatabase db;
		Cursor c; 
        db= openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_msg(id INTEGER PRIMARY KEY,pno VARCHAR,msg VARCHAR)");
        c =db.rawQuery("SELECT * from tbl_msg", null);
        String msg = c.getString(c.getColumnIndex("msg"));
		String pno = c.getString(c.getColumnIndex("pno"));
		list1.add(pno);
		lv.setAdapter(a);
    */
    }
    
    public void activity2(View view){
        Intent intent = new Intent(this,SecActivity.class);
        startActivity(intent);
     }

    


  
    }
