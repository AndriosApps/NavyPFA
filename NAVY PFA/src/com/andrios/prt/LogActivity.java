package com.andrios.prt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LogActivity extends Activity {
	
	protected static final int LOGVIEW = 0;
	ArrayList<LogEntry> notesList;
	ListView listView;
	LogAdapter logAdapter;
	Button newBTN;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.logactivity);
        
        readData();
        setConnections();
        setOnClickListeners();
    }
    
    

	private void setConnections() {
		
		
		newBTN = (Button) findViewById(R.id.logListActivityNewBTN);
		listView = (ListView) findViewById(R.id.logListActivityListview);
		logAdapter = new LogAdapter(this, R.layout.log_entry_list_item,
				notesList);
		listView.setAdapter(logAdapter);
		logAdapter.setNotifyOnChange(true);
		logAdapter.sort(new Comparator<LogEntry>() {

			public int compare(LogEntry object1, LogEntry object2) {
				 Calendar c1 = object1.getDate();
		            Calendar c2 = object2.getDate();
		            if (c1.after(c2)) {
		                return -1;
		            } else if (c1.before(c2)) {
		                return 1;
		            } else {
		                return 0;
		            }
				
			}

		});
		
	}
	
	
	private void setOnClickListeners() {
			newBTN.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					
					Intent intent = new Intent(v.getContext(), LogViewActivity.class);
					intent.putExtra("list", notesList);
					
					intent.putExtra("index", -1);
					startActivityForResult(intent, LOGVIEW);
					
				}
			});
			
			listView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View v, int row,
						long arg3) {
					/*
					Intent intent = new Intent(v.getContext(), LogEntryViewActivity.class);
					intent.putExtra("list", notesList);
					intent.putExtra("index", row);
					startActivityForResult(intent, NOTEVIEW);
					*/
				}

			});
	}





	private void createTestNote() {
		
		
	}

    
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
    	if (requestCode == LOGVIEW) {
    		if (resultCode == RESULT_OK) {
    			notesList = (ArrayList<LogEntry>) intent.getSerializableExtra("list");
    			setConnections();
    			write(LogActivity.this);
    		} else {
    			
    			Toast.makeText(this, "Add  Canceled", Toast.LENGTH_SHORT).show();
    		}
    	}
    }
	
	
    private void readData() {
		try {
			FileInputStream fis = openFileInput("notes");
			ObjectInputStream ois = new ObjectInputStream(fis);

			notesList = (ArrayList<LogEntry>) ois.readObject();
			ois.close();
			fis.close();
			
		} catch (Exception e) {
			notesList = new ArrayList<LogEntry>();
			createTestNote();
			
			
		}
		
		
	}
	






	public void write(Context ctx){
		
		try {
		
			FileOutputStream fos;
			fos = ctx.openFileOutput("notes", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(notesList);

			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Error: Writing to file",
					Toast.LENGTH_SHORT).show();
		}
	}
}