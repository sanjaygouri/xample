package com.example.xample;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

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

public class sms extends BroadcastReceiver {
    
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
     
    public void onReceive(Context context, Intent intent) {
     
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        Log.v("after closing ", "ON RECEIVE");
        try {
        	 Log.v("after closing ", "INSIDE TRY ");
            if (bundle != null) {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                     
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
 
                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                     
                    Log.v("after closing ", "FOR ");
                   // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    
                    Toast toast = Toast.makeText(context, 
                                 "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();
                    
                    SQLiteDatabase db;
            		Cursor c;
            		
            		//db= openOrCreateDatabase("mydb",context.MODE_PRIVATE);
            		db = context.openOrCreateDatabase("mydb", context.MODE_PRIVATE, null);
            		//db.execSQL("CREATE TABLE IF NOT EXISTS tbl_res(id INTEGER PRIMARY KEY,res_regid VARCHAR,response VARCHAR,Tstamp VARCHAR,lbg VARCHAR,res_pno VARCHAR)");
            		db.execSQL("CREATE TABLE IF NOT EXISTS tbl_msg(id INTEGER PRIMARY KEY,pno VARCHAR,msg VARCHAR)");
            		c =db.rawQuery("SELECT * FROM tbl_msg ", null);	
            		//String Tstamp = c.getString(c.getColumnIndex("Tstamp"));
            		//String phoneno = c.getString(c.getColumnIndex("res_pno"));
            		//db.execSQL("CREATE TABLE IF NOT EXISTS tbl_res(id INTEGER PRIMARY KEY,res_regid VARCHAR,response VARCHAR,Tstamp VARCHAR,lbg VARCHAR,res_pno VARCHAR)");
            		//c =db.rawQuery("SELECT * from tbl_res", null);
            		Log.v("test db cursor",c.getCount()+"");
            		

            		 Boolean numcheck = null ;
            		 //n2= new Boolean(true);
            		if (c != null) {
            			if (c.moveToFirst()) {
            			do {
            				String strdb= c.getString(1).substring(c.getString(1).length()-10);
            			Log.v("IN SMS RECIEVER db",strdb);	
            			Log.v("IN SMS RECIEVER db len",c.getString(1).length()+"");
            			String str =senderNum.substring(senderNum.length()-10);
            			Log.v("string comparison",str);
            			Log.v("COMPARISON ",(char)strdb.compareToIgnoreCase(str)+"");
            			numcheck=strdb.contains(str);
            			if(numcheck)
            			{
            				break;
            			}
            			Log.v("COMPARISON2 ",strdb.contains(str)+"");
            			} while (c.moveToNext());
            			}
            			}
            		//int n1=numcheck.compareTo(n2);
            		if(numcheck)
            		{
            			SmsManager smsManager = SmsManager.getDefault();
            			smsManager.sendTextMessage(senderNum, null," ia m busyy driving  dude ", null, null);
            		}	
            		//String phoneno = c.getString(c.getColumnIndex("res_pno"));
                    
                   
                   
                } // end for loop
                Log.v("after closing ", "FOR END ");
              } // bundle is null
 
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }    
}
