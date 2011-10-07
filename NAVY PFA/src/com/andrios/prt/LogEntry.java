package com.andrios.prt;

import java.io.Serializable;
import java.util.Calendar;


abstract class LogEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8070254636752752078L;

	String name;
	boolean isOfficial;
	int score;
	Calendar c;
	int mood;
	int layout;
	int age;
	boolean isAltitude;
	boolean isMale;
	
	
	/*
	 * Abstract Methods
	 */
	
	public abstract String getScoreString();
	
	
	/*
	 * Getter Methods
	 */
	public String getName(){
		return name;
	}
	
	public Calendar getDate(){
		return c;
	}
	
	
	
	public boolean isOfficial(){
		return isOfficial;
	}
	
	public int getMood(){
		return this.mood;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getLayout(){
		return layout;
	}
	
	public int getAge(){
		return age;
	}
	
	public boolean isAltitude(){
		return isAltitude;
	}
	
	public boolean isMale(){
		return isMale;
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
	
	public void setDate(int dayOfMonth, int monthOfYear, int year) {
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, monthOfYear);
		c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		
	}
	
	public void setOfficial(boolean isOfficial){
		this.isOfficial = isOfficial;
	}
	
	public void setMood(int mood){
		this.mood = mood;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public void setLayout(int layout){
		this.layout = layout;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public void setAltitude(boolean isAltitude){
		this.isAltitude = isAltitude;
	}
	
	public void setIsMale(boolean isMale){
		this.isMale = isMale;
	}
	

	
	
	
}
