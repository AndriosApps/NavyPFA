package com.andrios.prt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ProfileActivity extends Activity {
	
	Profile profile;
	TextView nameLBL, date1LBL, date2LBL, dateTypeLBL;
	
	SegmentedControlButton dueRDO, lmpRDO;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.profileactivity);
        
        readData();
        
        setConnections();
        setOnClickListeners();
        
    }
    
    
	private void setConnections() {
		
		nameLBL = (TextView) findViewById(R.id.ProfileActivityNameLBL);
		
		
		date1LBL = (TextView) findViewById(R.id.profileActivityDateLBL);
		
		
		dateTypeLBL = (TextView) findViewById(R.id.profileActivityDateTypeLBL);
	
		//dueRDO = (SegmentedControlButton) findViewById(R.id.profileActivityDueRDO);
		
		//lmpRDO = (SegmentedControlButton) findViewById(R.id.profileActivityLMPRDO);
		
		
		setDataField();
	}


	private void setOnClickListeners() {
		/*
		dueRDO.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				
				setDataField();
			}
			
		});
		*/
	}


	private void readData() {
		try {
			FileInputStream fis = openFileInput("profile");
			ObjectInputStream ois = new ObjectInputStream(fis);

			profile = (Profile) ois.readObject();
			ois.close();
			fis.close();
			
		} catch (Exception e) {
			profile = new Profile();
			createDialog();
			
		}
		
		
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
					write(ProfileActivity.this);
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
	
	private void setDataField(){
		
		nameLBL.setText(profile.getName());
		date1LBL.setText(profile.getDateString());
		
		
		Calendar c = (Calendar) profile.getDate().clone();
		

		
		String dateString = "";
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH)+1;
		int year = c.get(Calendar.YEAR);
		String monthString = "";
		System.out.println("Month Is " + month);
		if(month == 1){
			monthString = "January";
		}else if(month == 2){
			monthString = "February";
		}else if(month == 3){
			monthString = "March";
		}else if(month == 4){
			monthString = "April";
		}else if(month == 5){
			monthString = "May";
		}else if(month == 6){
			monthString = "June";
		}else if(month == 7){
			monthString = "July";
		}else if(month == 8){
			monthString = "August";
		}else if(month == 9){
			monthString = "September";
		}else if(month == 10){
			monthString = "October";
		}else if(month == 11){
			monthString = "November";
		}else if(month == 12){
			monthString = "December";
		}
		dateString = day + " " + monthString + " " + year; 
       
		
	}
	
	public void onPause(){
		super.onPause();
		write(ProfileActivity.this);
	}
    
}
