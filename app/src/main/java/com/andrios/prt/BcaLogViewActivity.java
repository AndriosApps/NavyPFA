package com.andrios.prt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andrios.prt.Classes.Profile;

import java.util.ArrayList;
import java.util.Calendar;

public class BcaLogViewActivity extends Activity {
	

	protected static final int PRTACTIVITY = 0;
	protected static final int BCAACTIVITY = 1;
	static final int DATE_DIALOG_ID = 1;
	AndriosData mData;
	ArrayList<LogEntry> logList;
	int index, mMonth, mDay, mYear;
	BcaEntry entry;
	TextView dateLBL;
	TextView heightLBL, weightLBL;
	TextView neckLBL, waistLBL, hipLBL, hipLBLBL;
	Profile profile;
	LinearLayout heightWeightLL, bodyfatLL, bodyfatStatsLL;
	Button saveBTN;
	OnClickListener myOnClickListener;
	boolean changes;
	CheckBox officialCheckBox;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bcalogviewactivity);
        
     
        getExtras();
        //setConnections();
        //setOnClickListeners();
        
    }
    

	
	public void onResume(){
		super.onResume();
	}
	
	public void onPause(){
		super.onPause();
	}
    
	@SuppressWarnings("unchecked")
	private void getExtras() {
		Intent intent = this.getIntent();
		logList = (ArrayList<LogEntry>) intent.getSerializableExtra("list");
		index = intent.getIntExtra("index", -1);
		mData = (AndriosData) intent.getSerializableExtra("data");
		if(index == -1){
			Intent bcaIntent = new Intent(BcaLogViewActivity.this.getBaseContext(), BcaActivity.class);
			
			
			
			bcaIntent.putExtra("log", true);
			bcaIntent.putExtra("premium", true);
			bcaIntent.putExtra("data", mData);
			startActivityForResult(bcaIntent, BCAACTIVITY);
			
			
		}else{
			entry = (BcaEntry) logList.get(index);
			changes = false;
	        setConnections();
	        setOnClickListeners();
		}
		
	}
	
	private void setConnections() {
		heightLBL = (TextView)findViewById(R.id.bcaLogViewActivityHeightLBL);
		heightLBL.setText(entry.getHeight());
		weightLBL = (TextView)findViewById(R.id.bcaLogViewActivityWeightLBL);
		weightLBL.setText(entry.getWeight());
		
		neckLBL = (TextView)findViewById(R.id.bcaLogViewActivityNeckLBL);
		neckLBL.setText(entry.getNeck());

		waistLBL = (TextView)findViewById(R.id.bcaLogViewActivityWaistLBL);
		waistLBL.setText(entry.getWaist());
		
		
		hipLBLBL = (TextView)findViewById(R.id.bcaLogViewActivityHipsLBLLBL);
		
		hipLBL = (TextView)findViewById(R.id.bcaLogViewActivityHipsLBL);
		if(entry.getHips().equals(" ")){
			hipLBLBL.setVisibility(View.INVISIBLE);
			hipLBL.setVisibility(View.INVISIBLE);
		}else{
			hipLBL.setText(entry.getHips());
		}
		
		
		heightWeightLL = (LinearLayout)findViewById(R.id.bcaLogViewActivityHeightWeightLL);
		
		bodyfatStatsLL = (LinearLayout)findViewById(R.id.bcaLogViewActivityBodyfatStatsLL);
		bodyfatLL = (LinearLayout)findViewById(R.id.bcaLogViewActivityBodyfatLL);
		if(!entry.isHeightWeight()){
			heightWeightLL.setBackgroundResource(R.drawable.failbtn);
		}else{
			heightWeightLL.setBackgroundResource(R.drawable.passbtn);
			bodyfatStatsLL.setVisibility(View.INVISIBLE);
		}
		if(!entry.isBodyFat()){
			bodyfatLL.setBackgroundResource(R.drawable.failbtn);
		}else{
			bodyfatLL.setBackgroundResource(R.drawable.passbtn);
		}
		
		
		
		
		saveBTN = (Button) findViewById(R.id.bcaLogViewActivitySaveBTN);
		
		dateLBL = (TextView) findViewById(R.id.bcaLogViewActivityDateLBL);
		dateLBL.setText(entry.getDateString());
		
		
		officialCheckBox = (CheckBox) findViewById(R.id.bcaLogViewActivityImportantCheckBox);
		officialCheckBox.setChecked(entry.isOfficial());
		setOnClickListeners();
		
	}
	
	private void setOnClickListeners(){
		
		dateLBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
				
			}
			
		});
		
		saveBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				if(checkFormat()){
					Intent intent = new Intent();
					entry.setOfficial(officialCheckBox.isChecked());
					
					if(index == -1){
						logList.add(entry);
					}else{
						logList.set(index, entry);
					}
					
					
					intent.putExtra("list", logList);
					BcaLogViewActivity.this.setResult(RESULT_OK, intent);
					BcaLogViewActivity.this.finish();
				}
				
			}

			

			
			
		});
	}
	
	private void createExitDialog(){
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Quit Without Saving?");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				BcaLogViewActivity.this.finish();
				
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
	
	private boolean checkFormat() {
		// TODO Auto-generated method stub
		return true;
	}
	
	  @Override
	    protected Dialog onCreateDialog(int id) {
	            switch (id) {

	            case DATE_DIALOG_ID:
	                    return new DatePickerDialog(this,
	                            mDateSetListener,
	                            entry.getDate().get(Calendar.YEAR), 
	                            entry.getDate().get(Calendar.MONTH), 
	                            entry.getDate().get(Calendar.DAY_OF_MONTH));
	            }
	            return null;
	    }
	    protected void onPrepareDialog(int id, Dialog dialog) {
	            switch (id) {

	            case DATE_DIALOG_ID:
	                    ((DatePickerDialog) dialog).updateDate(
	                    		entry.getDate().get(Calendar.YEAR), 
	                            entry.getDate().get(Calendar.MONTH), 
	                            entry.getDate().get(Calendar.DAY_OF_MONTH));
	                    break;
	            }
	    }    
	   
	    private DatePickerDialog.OnDateSetListener mDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {

					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						
	                    entry.setDate(dayOfMonth, monthOfYear, year);
	                    dateLBL.setText(entry.getDateString());
						
					}

	          
	    };

		@Override
	    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			
	    	if (requestCode == BCAACTIVITY) {
	    		if (resultCode == RESULT_OK) {
	    			entry = (BcaEntry) intent.getSerializableExtra("entry");
	    			setConnections();
	    			changes = true;
	    		} else {
	    			
	    			Toast.makeText(this, "Add  Canceled", Toast.LENGTH_SHORT).show();
	    		}
	    	}
	    }

		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event)  {
		    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
		        if(changes){
		        	createExitDialog();
		        }else{
		        	BcaLogViewActivity.this.finish();
		        }
		        return true;
		    }

		    return super.onKeyDown(keyCode, event);
		}
}
