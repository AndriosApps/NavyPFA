package com.andrios.prt;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class LogViewActivity extends Activity {
	

	protected static final int PRTACTIVITY = 0;
	protected static final int BCAACTIVITY = 1;
	static final int DATE_DIALOG_ID = 1;
	
	ArrayList<LogEntry> logList;
	int index, mMonth, mDay, mYear;
	PrtEntry entry;
	TextView dateLBL;
	TextView pushupsLBL, situpsLBL, runLBL;
	TextView pushupScoreLBL, situpScoreLBL, runScoreLBL, totalScoreLBL;
	Profile profile;
	
	Button saveBTN, editBTN;
	ViewFlipper flipper;
	OnClickListener myOnClickListener;

	Spinner moodSpinner;
	CheckBox officialCheckBox;

	private String array_spinner[];
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.logviewactivity);
        
     
        getExtras();
        //setConnections();
        //setOnClickListeners();
        
    }
    
	@SuppressWarnings("unchecked")
	private void getExtras() {
		Intent intent = this.getIntent();
		logList = (ArrayList<LogEntry>) intent.getSerializableExtra("list");
		index = intent.getIntExtra("index", -1);
		if(index == -1){
			Intent prtIntent = new Intent(LogViewActivity.this.getBaseContext(), PRTActivity.class);
			AndriosData mData = new AndriosData();
			
			//mData.setAge(profile.getAge());//TODO
			//mData.setGender(profile.isMale());//TODO
			prtIntent.putExtra("log", true);
			prtIntent.putExtra("premium", true);
			prtIntent.putExtra("data", mData);
			startActivityForResult(prtIntent, PRTACTIVITY);
			
			
		}else if(index == -2){
			//entry = new BcaEntry();
		}else{
			//entry = logList.get(index);
		}
		
	}
	
	private void setConnections() {
		pushupsLBL = (TextView)findViewById(R.id.logViewActivityPushupsLBL);
		pushupsLBL.setText(entry.getPushups());
		situpsLBL = (TextView)findViewById(R.id.logViewActivitySitupsLBL);
		situpsLBL.setText(entry.getSitups());
		runLBL = (TextView)findViewById(R.id.logViewActivityRunLBL);
		runLBL.setText(entry.getRun());

		pushupScoreLBL = (TextView)findViewById(R.id.logViewActivityPushupScoreLBL);
		pushupScoreLBL.setText(entry.getPushupScore());
		situpScoreLBL = (TextView)findViewById(R.id.logViewActivitySitupScoreLBL);
		situpScoreLBL.setText(entry.getSitupScore());
		runScoreLBL = (TextView)findViewById(R.id.logViewActivityRunScoreLBL);
		runScoreLBL.setText(entry.getRunScore());

		totalScoreLBL = (TextView)findViewById(R.id.logViewActivityTotalScoreLBL);
		totalScoreLBL.setText(entry.getTotalScore());
		
		editBTN = (Button) findViewById(R.id.journalEntryActivityFlipBTN);
		flipper = (ViewFlipper) findViewById(R.id.details); 
		flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
	    flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
		
		array_spinner=new String[8];
		array_spinner[0]="Excited";
		array_spinner[1]="Happy";
		array_spinner[2]="Scared";
		array_spinner[3]="Hopeful";
		array_spinner[4]="Worried";
		array_spinner[5]="Crying";
		array_spinner[6]="Uncertain";
		array_spinner[7]="Mad";
		moodSpinner = (Spinner) findViewById(R.id.journalEntryViewActivityMoodSpinner);
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, array_spinner);
		moodSpinner.setAdapter(adapter);
		moodSpinner.setSelection(entry.getMood());
		
		saveBTN = (Button) findViewById(R.id.journalEntryViewActivitySaveBTN);
		
		dateLBL = (TextView) findViewById(R.id.journalEntryViewActivityDateLBL);
		dateLBL.setText(entry.getDateString());
		
		
		officialCheckBox = (CheckBox) findViewById(R.id.journalEntryViewActivityImportantCheckBox);
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
					entry.setMood(moodSpinner.getSelectedItemPosition());
					entry.setOfficial(officialCheckBox.isChecked());
					
					if(index == -1){
						logList.add(entry);
					}else{
						logList.set(index, entry);
					}
					
					
					intent.putExtra("list", logList);
				System.out.println("SAVE BUTTON CLICKED");
					LogViewActivity.this.setResult(RESULT_OK, intent);
					LogViewActivity.this.finish();
				}
				
			}

			

			
			
		});
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
			
	    	if (requestCode == PRTACTIVITY) {
	    		if (resultCode == RESULT_OK) {
	    			entry = (PrtEntry) intent.getSerializableExtra("entry");
	    			setConnections();
	    		} else {
	    			
	    			Toast.makeText(this, "Add  Canceled", Toast.LENGTH_SHORT).show();
	    		}
	    	}
	    }

}
