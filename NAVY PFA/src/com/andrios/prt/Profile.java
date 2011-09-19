package com.andrios.prt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Profile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8939542873830414073L;

	
	String name;
	String rank;
	Calendar c;
	ArrayList<LogEntry> logList;
	boolean isMale;
	
	public Profile(){
		this.name = "Click to Set Name";
		c = Calendar.getInstance();
		logList = new ArrayList<LogEntry>();
	}
	
	/*
	 * Getter Methods
	 */
	
	public String getName(){
		return name;
	}
	
	public Calendar getDate(){
		return c;
	}
	public int getAge(){
		int age = 0;
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR) - c.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) - c.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH) - c.get(Calendar.DAY_OF_MONTH);
		
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
		return isMale();
	}
	
	
	
	public String getDateString(){
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
        
		return dateString;
	}
	
	
	/*
	 * Setter Methods
	 */
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDate(Calendar c){
		this.c = c;
	}
	
	public void setMale(boolean isMale){
		this.isMale = isMale;
	}
	

	
	
	
	
}
