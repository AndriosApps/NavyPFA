package com.andrios.prt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity {
	

	static final int DATE_DIALOG_ID = 1;
	static final int SELECT_IMAGE = 2;
	Profile profile;
	TextView nameLBL, date1LBL, date2LBL, dateTypeLBL, saveBTN, nextPfaDateLBL, countdownLBL, ageLBL ;
	TextView birthdayYearLBL, nextPfaYearLBL;
	LinearLayout nextPFALL, birthdayLL;
	int mYear, mMonth, mDay;
	int whichDate = 0;
	ImageView profileIMG;
	boolean changes= false;
	GoogleAnalyticsTracker tracker;
	
	SegmentedControlButton maleRDO, femaleRDO;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.profileactivity);
        
        getExtras();
        
        setConnections();
        setOnClickListeners();
        //setTracker();
        
    }
    
	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.start(this.getString(R.string.ga_api_key), getApplicationContext());
	}
	
	public void onResume(){
		super.onResume();
		//tracker.trackPageView("/" + this.getLocalClassName());
	}
	
	public void onPause(){
		super.onPause();
		write(ProfileActivity.this);
		//tracker.dispatch();
	}
    
    
    
    
	private void setConnections() {

		birthdayYearLBL = (TextView) findViewById(R.id.profileActivityBirthdayYearLBL);
		nextPfaYearLBL = (TextView) findViewById(R.id.profileActivityNextPFAYearLBL);
		ageLBL = (TextView) findViewById(R.id.profileActivityAgeLBL);
		profileIMG = (ImageView) findViewById(R.id.profileActivityImageView);
		countdownLBL = (TextView) findViewById(R.id.profileActivityDaysToPFALBL);
		birthdayLL = (LinearLayout) findViewById(R.id.profileActivityBirthdayLL);
		
		nextPfaDateLBL = (TextView) findViewById(R.id.profileActivityNextPfaDateLBL);
		
		nextPFALL = (LinearLayout) findViewById(R.id.profileActivityPFALL);
		
		maleRDO = (SegmentedControlButton) findViewById(R.id.profileMaleRDO);
		femaleRDO = (SegmentedControlButton) findViewById(R.id.profileFemaleRDO);
		
		femaleRDO.setChecked(!profile.isMale());
		
		
		nameLBL = (TextView) findViewById(R.id.ProfileActivityNameLBL);
		
		
		date1LBL = (TextView) findViewById(R.id.profileActivityDateLBL);
		
		
		dateTypeLBL = (TextView) findViewById(R.id.profileActivityDateTypeLBL);
		
		saveBTN = (Button) findViewById(R.id.profileActivitySaveBTN);
	
		
		
		
		setDataField();
	}


	private void setOnClickListeners() {
		profileIMG.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				//startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), SELECT_IMAGE);
				
			}
			
		});
		birthdayLL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				whichDate = 1;
				getCalendar();
				showDialog(DATE_DIALOG_ID);
				
			}
			
		});
		nextPFALL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				whichDate = 2;
				getCalendar();
				showDialog(DATE_DIALOG_ID);
				
			}
			
		});
		maleRDO.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				profile.setMale(maleRDO.isChecked());
				changes = true;
			}
			
		});
		
		saveBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				write(ProfileActivity.this);
				Intent intent = new Intent();
				intent.putExtra("profile", profile);
				ProfileActivity.this.setResult(RESULT_OK, intent);
				ProfileActivity.this.finish();
				
			}
			
		});
		
		nameLBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				getCalendar();
				createDialog();
				
			}
			
		});
	
	}


	private void getExtras() {
			Intent intent = this.getIntent();
			profile = (Profile) intent.getSerializableExtra("profile");
	}
		
		

	public void write(Context ctx){
		
		try {
		
			FileOutputStream fos;
			fos = ctx.openFileOutput("profile", Context.MODE_WORLD_READABLE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(profile);

			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Error: Writing to file",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	private void createDialog(){
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setTitle("Enter Your Name");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString().trim();
				profile.setName(value);
				try{
					nameLBL.setText(profile.getName());
					changes = true;
				}catch(Exception e){
					
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
	
	private void createExitDialog(){
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Quit Without Saving?");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				ProfileActivity.this.finish();
				
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
	
	private void setDataField(){
		if(whichDate == 1){//Set Birthday Fields
			profile.getDate().set(Calendar.YEAR, mYear);
			profile.getDate().set(Calendar.MONTH, mMonth);
			profile.getDate().set(Calendar.DAY_OF_MONTH, mDay);
			changes = true;
		}else if(whichDate == 2){
			profile.getNextPFA().set(Calendar.YEAR, mYear);
			profile.getNextPFA().set(Calendar.MONTH, mMonth);
			profile.getNextPFA().set(Calendar.DAY_OF_MONTH, mDay);
			changes = true;
		}
		
		nameLBL.setText(profile.getName());
		String myName = Integer.toString(profile.getDate().get(Calendar.DAY_OF_MONTH));
		date1LBL.setText(myName);
		ageLBL.setText(Integer.toString(profile.getAge()));
		nextPfaDateLBL.setText(Integer.toString(profile.getNextPFA().get(Calendar.DAY_OF_MONTH)));
		birthdayYearLBL.setText(Integer.toString(profile.getDate().get(Calendar.YEAR)));
		nextPfaYearLBL.setText(Integer.toString(profile.getNextPFA().get(Calendar.YEAR)));
		Calendar c = Calendar.getInstance();
		
		long diff = profile.getNextPFA().getTimeInMillis() - c.getTimeInMillis();
		long days = diff / (24 * 60 * 60 * 1000);
		countdownLBL.setText(Long.toString(days)+ " Days");

		
		//Set Background for Birthday Calendar
		int month = profile.getDate().get(Calendar.MONTH);
		 if(month == 0){
         	birthdayLL.setBackgroundResource(R.drawable.cal_0);
         }else if(month == 1){
         	birthdayLL.setBackgroundResource(R.drawable.cal_1);
         }else if(month == 2){
         	birthdayLL.setBackgroundResource(R.drawable.cal_2);
         }else if(month == 3){
         	birthdayLL.setBackgroundResource(R.drawable.cal_3);
         }else if(month == 4){
         	birthdayLL.setBackgroundResource(R.drawable.cal_4);
         }else if(month == 5){
         	birthdayLL.setBackgroundResource(R.drawable.cal_5);
         }else if(month == 6){
         	birthdayLL.setBackgroundResource(R.drawable.cal_6);
         }else if(month == 7){
         	birthdayLL.setBackgroundResource(R.drawable.cal_7);
         }else if(month == 8){
         	birthdayLL.setBackgroundResource(R.drawable.cal_8);
         }else if(month == 9){
         	birthdayLL.setBackgroundResource(R.drawable.cal_9);
         }else if(month == 10){
         	birthdayLL.setBackgroundResource(R.drawable.cal_10);
         }else if(month == 111){
         	birthdayLL.setBackgroundResource(R.drawable.cal_11);
         }
		 
		//Set Background for Next PFA
			month = profile.getNextPFA().get(Calendar.MONTH);
			 if(month == 0){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_0);
	         }else if(month == 1){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_1);
	         }else if(month == 2){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_2);
	         }else if(month == 3){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_3);
	         }else if(month == 4){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_4);
	         }else if(month == 5){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_5);
	         }else if(month == 6){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_6);
	         }else if(month == 7){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_7);
	         }else if(month == 8){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_8);
	         }else if(month == 9){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_9);
	         }else if(month == 10){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_10);
	         }else if(month == 111){
	         	nextPFALL.setBackgroundResource(R.drawable.cal_11);
	         }
       
		
	}
	

	
	  @Override
	    protected Dialog onCreateDialog(int id) {
	            switch (id) {

	            case DATE_DIALOG_ID:
	            		System.out.println("OnCreate "+ mMonth+" "+mYear+" "+mDay);//TODO REMOVE
	                    return new DatePickerDialog(this,
	                            mDateSetListener,
	                            mYear, mMonth, mDay);
	            }
	            return null;
	    }
	    protected void onPrepareDialog(int id, Dialog dialog) {
	            switch (id) {
	            
	            case DATE_DIALOG_ID:
	            		//getCalendar();
	            	System.out.println("On Prepare " + mMonth+" "+mYear+" "+mDay);//TODO
	                    ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
	                    break;
	            }
	    }   
	    
	    private DatePickerDialog.OnDateSetListener mDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {

					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						mYear = year;
	                    mMonth = monthOfYear;
	                    mDay = dayOfMonth;
	                    setDataField();
						
					}

	          
	    };



		protected void getCalendar() {
			Calendar c = Calendar.getInstance();
        	
			
			if(whichDate == 1){
				c = profile.getDate();
			}else if(whichDate == 2){
				c = profile.getNextPFA();
	        	
			}
			
			mYear = c.get(Calendar.YEAR);
        	mMonth = c.get(Calendar.MONTH);
        	mDay = c.get(Calendar.DAY_OF_MONTH);
			
			
		}
		
		@Override
	    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			
	    	if (requestCode == SELECT_IMAGE) {
	    		if (resultCode == RESULT_OK) {
	    			Uri selectedImage = intent.getData();
					Bitmap bitmap = null;
	    			try {
						bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					int tempW = bitmap.getWidth();
					int tempH = bitmap.getHeight();

					
					Toast.makeText(this, "setting bitmap", Toast.LENGTH_SHORT).show();//TODO
					profileIMG.setImageBitmap(bitmap);
	    			
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
		        	ProfileActivity.this.finish();
		        }
		        return true;
		    }

		    return super.onKeyDown(keyCode, event);
		}
		
    
}
