package com.andrios.prt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
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
	AndriosData mData;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.logactivity);
        
        getExtras();
        readData();
        setConnections();
        setOnClickListeners();
        setTracker();
    }
    
	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.start(this.getString(R.string.ga_api_key), getApplicationContext());
	}
	
	public void onResume(){
		super.onResume();
		tracker.trackPageView("/" + this.getLocalClassName());
	}
	
	public void onPause(){
		super.onPause();
		tracker.dispatch();
	}
    
    

	private void getExtras() {
		Intent intent = this.getIntent();
		mData = (AndriosData) intent.getSerializableExtra("data");
	}



	private void setConnections() {
		
		
		newBTN = (Button) findViewById(R.id.logListActivityNewBTN);
		listView = (ListView) findViewById(R.id.logListActivityListview);
		logAdapter = new LogAdapter(this, R.layout.list_item_prt_entry,
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
					/*
					
					*/
					createLogDialog();
				}
			});
			
			listView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View v, int row,
						long arg3) {
					Intent intent = null;
					if(notesList.get(row).getName().equals("PRT")){
						intent = new Intent(v.getContext(), PrtLogViewActivity.class);
					}else{
						intent = new Intent(v.getContext(), BcaLogViewActivity.class);
					}
					
					intent.putExtra("list", notesList);
					intent.putExtra("index", row);
					startActivityForResult(intent, LOGVIEW);
					
				}

			});
			
			listView.setOnItemLongClickListener(new OnItemLongClickListener(){

				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					createDeleteDialog(arg2);
					return false;
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
    			
    			Toast.makeText(this, "Log Entry Canceled", Toast.LENGTH_SHORT).show();
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
	private void createDeleteDialog(final int row){
		
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Delete " + notesList.get(row).getName() + " entry");
		
		alert.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						logAdapter.remove(notesList.get(row));
						write(LogActivity.this);
					}
				});
	
		alert.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		alert.show();
	}

	private void createLogDialog(){
		String items[] = {"PRT", "BCA"};
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Log Options");
		alert.setItems(items, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				if(which == 0){
					Intent intent = new Intent(LogActivity.this.getBaseContext(), PrtLogViewActivity.class);
					intent.putExtra("list", notesList);
					
					intent.putExtra("index", -1);
					intent.putExtra("data", mData);
					startActivityForResult(intent, LOGVIEW);
				}else if(which == 1){
					Intent intent = new Intent(LogActivity.this.getBaseContext(), BcaLogViewActivity.class);
					intent.putExtra("list", notesList);
					intent.putExtra("data", mData);
					intent.putExtra("index", -1);
					startActivityForResult(intent, LOGVIEW);
				}
				
			}
		});
		
	
		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		alert.show();
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