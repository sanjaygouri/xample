package com.example.xample;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.xample.R.string;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;


public class SecActivity extends ActionBarActivity 
{
	 public TextView outputText;
	// AutoCompleteTextView autocompletetextview;
	 ListView lv;
	 ListView lv1;
	 
	 public  ArrayAdapter adapter;
	 public ArrayAdapter<String> a2;
	 public ArrayAdapter<String> adapter1;
	 public ArrayAdapter<String> a3;
	 ArrayList<String> list;
	 ArrayList<String> list1;
	// ArrayList<String> list2;
	 ArrayList<ArrayList> Mlist = new ArrayList<ArrayList>();
	 ArrayList<String> numlist;
	 ArrayList<String> list3 = new ArrayList<String>();
	 ArrayList<String> list4 = new ArrayList<String>();
	 protected void onCreate(Bundle savedInstanceState) 
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_sec);
		// Log.v("our test scenario 1", "brask point 1");
		 list= new ArrayList<String>();
		 list1= new ArrayList<String>();
		
		 
		 outputText = (TextView) findViewById(R.id.textView1);
		 
		 fetchContacts();
		 
		 EditText et= (EditText)findViewById(R.id.et);
		 
		 Log.v("contascts fetched", "test edit text");
		 // AutoCompleteTextView etv=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		 // etv.setAdapter(adapter);
		// Log.v("our test scenario 1", "brask point 3");
		 et.addTextChangedListener(new TextWatcher() 
		 {
			 @Override
			 public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) 
			 {	
				 //TODO Auto-generated method stub
				 adapter.getFilter().filter(arg0);
			 }
			 @Override
			 public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) 
			 {
				 // TODO Auto-generated method stub
			 }
			 @Override
			 public void afterTextChanged(Editable arg0) 
			 {
				 // TODO Auto-generated method stub
			 }
		 });
	
		 Log.v("contascts fetched", "listner assigned 2");
		 
		// Log.v("our test scenario 1", "brask point 4");
		// autocompletetextview = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
               
		// Log.v("our test scenario 1", "brask point 5");
		 adapter = new ArrayAdapter  (this,android.R.layout.simple_list_item_multiple_choice,list);
		 a2  = new ArrayAdapter  (this,android.R.layout.simple_list_item_multiple_choice,list);
		 adapter1 = new ArrayAdapter  (this,android.R.layout.simple_dropdown_item_1line,list1);
		 a3 = new ArrayAdapter  (this,android.R.layout.simple_dropdown_item_1line,Mlist);
		 //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text2, list);
        
		 Log.v("contascts fetched", "adapter initilisation");
		 
		 lv = (ListView)findViewById(R.id.list);
		 lv1= (ListView)findViewById(R.id.list1);
		 lv.setAdapter(adapter);
//		 lv2= (ListView)findViewById(R.id.list1);
		 
	//	 autocompletetextview.setAdapter(adapter);
		 Log.v("contascts fetched", "t");
		 
		 lv.setOnItemClickListener(new OnItemClickListener() 
		 {
			 @Override
			 public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			 {
				 // ListView Clicked item index
				 int itemPosition     = position;
				 // ListView Clicked item value
				 String  itemValue    = (String)lv.getItemAtPosition(position);
				 int xyz=   a2.getPosition(itemValue);
				 //lv.setSelection(xyz);
				 //lv.setSelected(true);
				 // Show Alert 
				 Toast.makeText(getApplicationContext(),
				 "Position :"+itemPosition+"   actual pos"+xyz+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();
				 //lv.setSelection(xyz);
				 
				 list3=Mlist.get(xyz);
				 Iterator<String> i = list3.iterator();
				 String temp="";
				 
				 do{
					 String temp2 = i.next();
					temp= temp+" "+temp2+"-";
					list4.add(temp2);
				 } while(i.hasNext());
			
				 String itemtemp=itemValue+" "+temp;
				 list1.add(itemtemp);
				 lv1.getCheckedItemIds();
				 lv1.setAdapter(adapter1);
				 
				
				 
			 }
		 });
		 
	 }
	 
			   
	 public void fetchContacts() 
	 {    	  	 
		 String phoneNumber = null;
		 Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
		 String _ID = ContactsContract.Contacts._ID;
		 String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
		 String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        
		 Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		 String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
		 String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        
		 StringBuffer output = new StringBuffer();
		 ContentResolver contentResolver = getContentResolver();
		 Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null); 
    	 if (cursor.getCount() > 0) 
    	 {
    		 int xcount = 0;
    		 while (cursor.moveToNext()) 
    		 {
    			 xcount++;
    		 if(xcount>1000)
    		 {
    			 xcount =0;
    			 break;
    		 }
    		 
    			 String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
    			 String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));
    			 int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));
    			 if (hasPhoneNumber > 0) 
    			 {    		 
    				 list.add(name);
    				 Mlist.add(new ArrayList<String>());
    				 Log.v("SIZE OF MLIST", Mlist.size()+"");
    				 Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
    				 
    				 while (phoneCursor.moveToNext()) 
    				 {        		 
    					 phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));        		 
    				
//    					 list.add(phoneNumber);
    					 //list2= new ArrayList<String>();
    					 //list2.add(phoneNumber);
    					 Mlist.get(Mlist.size()-1).add(phoneNumber);
    					 
    					 
    				    
    				 }
    				 //Iterator<String> i2 = list2.iterator();
    				 //String temp="";
    				
    				// do{
    					//temp= temp+" "+i2.next()+"-";
    				//	Log.v("list view test", temp);
    				// } while(i2.hasNext());
    				// Log.v("list view test", "eoi");
                 // Mlist.add(list2); 
                 
    				 phoneCursor.close();   
    			/*	 try{
    					 Log.v("before closing ", "caught ");
    				//	 list2.clear();
    					 Log.v("after closing ", "caught ");
    				 }
    				 catch (Exception e) {
						// TODO: handle exception
    					 Log.v("execption ", "caught "+e);
					}*/
    			 }
    			 output.append("\n");
    		 }
    		 
    		 outputText.setText(output);
    	 }
	 }
	 
	public void db(View v) {
		//writing int sqlite data base

		SQLiteDatabase db;
		Cursor c; 
		db= openOrCreateDatabase("mydb",MODE_PRIVATE,null);
		//db.execSQL("drop table if exists tbl_msg");
		db.execSQL("CREATE TABLE IF NOT EXISTS tbl_msg(id INTEGER PRIMARY KEY,pno VARCHAR,msg VARCHAR)");
		ContentValues values = new ContentValues();
		
		//Iterator<ArrayList> it1 = Mlist.iterator();
		//while(it1.hasNext())
		//{
			//ArrayList<String> list_var = it1.next();
			
			Iterator<String> it2 = list4.iterator();
			while(it2.hasNext())
			{
				String num = it2.next();
				String msg = "hey call u later dude";
			    values.put("msg",msg);
		        values.put("pno",num);
		        db.insert("tbl_msg", null, values);
			
			}
			
		//}
		Cursor c2 =db.rawQuery("SELECT * FROM tbl_msg ", null);
		c2 =db.rawQuery("SELECT * FROM tbl_msg ", null);
		Log.v("test db cursor",c2.getCount()+"");
		if(c2.getCount()>= 0)
		{
			
			if (c2.moveToFirst()) {
    			do {
    			Log.v("after closing-----",c2.getString(1)); 
    			} while (c2.moveToNext());
    			}
    			}
			
		}
		
		
					
	
}




