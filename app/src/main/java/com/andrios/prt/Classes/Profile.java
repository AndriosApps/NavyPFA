package com.andrios.prt.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.andrios.prt.LogEntry;
import com.andrios.prt.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

public class Profile extends Observable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8939542873830414073L;

	
	String name;
	String rank;
	Calendar birthday;
	Calendar nextPFA;
	ArrayList<LogEntry> logList;
	boolean isMale;
	boolean isPinProtected;
	int pin;
	String profilePicUriString;
	Bitmap profileBitmap;
	
	public Profile(){
		this.name = "Click to Set Name";
		birthday = Calendar.getInstance();
		birthday.add(Calendar.YEAR, -20);
		nextPFA = Calendar.getInstance();
		logList = new ArrayList<LogEntry>();
		this.isPinProtected = false;
		this.profilePicUriString = "android.resource://com.andrios.prt/" + R.drawable.icon_profile;
	}
	
	/*
	 * Getter Methods
	 */
	
	public String getName(){
		return name;
	}
	
	public Calendar getDate(){
		return birthday;
	}
	
	public Calendar getNextPFA(){
		return nextPFA;
	}
	
	public int getAge(){
		int age = 0;
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
		
		if(month < 0) {
			age = year-1;
			
		} else if (month == 0) {
			if(day < 0) {
				age = year-1;
			} else {
				age = year;
			}
		} else {
			age = year;
		}
		return age;
	}
	
	public boolean isMale(){
		return isMale;
	}
	
	public boolean isPinProtected(){
		return this.isPinProtected;
	}
	
	
	
	public String getDateString(Calendar c){
		String dateString = "";
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH)+1;
		int year = c.get(Calendar.YEAR);
		String monthString = "";
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
        
		return dateString;
	}
	
	public int getPin(){
		return this.pin;
	}
	
	public Uri getProfilePicUri(){

		return Uri.parse(profilePicUriString);
	}
	
	public Bitmap getProfileBitmap(Context context){
        Log.d("Profile: ", "getProfileBitmap: ");
        Bitmap bitmap = null;
		try {
			bitmap = MediaStore.Images.Media.getBitmap(context.getApplicationContext().getContentResolver(), getProfilePicUri());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
            Log.d("Profile: ", "getProfileBitmap: File Not Found");
            e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
            Log.d("Profile: ", "getProfileBitmap: iO Exception");
			e.printStackTrace();
		}


		return bitmap;
	}
	
	
	/*
	 * Setter Methods
	 */
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDate(Calendar c){
		this.birthday = c;
	}
	
	public void setPFA(Calendar c){
		this.nextPFA = c;
		setChanged();
		notifyObservers();
	}
	
	public void setMale(boolean isMale){
		this.isMale = isMale;
	}
	
	public void setPinProtection(boolean isPinProtected){
		this.isPinProtected = isPinProtected;
	}
	
	public void setPin(int pin){
		this.pin = pin;
	}

	
	public void setProfilePicUriString(Uri profilePicUriString){
        //TODO Fix this
		this.profilePicUriString = "";
	}
	
	public void setProfileBitmap(Bitmap profileBitmap){
		this.profileBitmap = profileBitmap;
	}
	
	
}
