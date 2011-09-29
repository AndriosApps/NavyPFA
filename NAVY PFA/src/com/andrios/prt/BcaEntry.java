package com.andrios.prt;

import java.util.Calendar;

public class BcaEntry extends LogEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1538116430747812374L;
	String height;
	String weight;
	String neck;
	String waist;
	String hips;
	String circum;
	String bodyfat;
	boolean isHeightWeight;
	boolean isBodyFat;
	
	public BcaEntry(String height, String weight, String neck, String waist, String hips, String circum, 
			String bodyfat, boolean isHeightWeight, boolean isBodyFat){
		this.name = "BCA";
		this.c = Calendar.getInstance();
		this.isOfficial = false;
		this.layout = R.layout.list_item_bca_entry;
		this.height = height;
		this.weight = weight;
		this.neck = neck;
		this.waist = waist;
		this.hips = hips;
		this.circum = circum;
		this.bodyfat = bodyfat;
		this.isHeightWeight = isHeightWeight;
		this.isBodyFat = isBodyFat;
	}
	
	public String getHeight(){
		return height;
	}
	
	public String getWeight(){
		return weight;
	}
	
	public String getNeck(){
		return neck;
	}
	
	public String getWaist(){
		return waist;
	}
	
	public String getHips(){
		return hips;
	}
	
	public String getCircum(){
		return circum;
	}
	
	public String getBodyfat(){
		return bodyfat;
	}
	
	public boolean isHeightWeight(){
		return isHeightWeight;
	}
	
	public boolean isBodyFat(){
		return isBodyFat;
	}
	
	@Override
	public String getScoreString() {
		if(isHeightWeight()){
			return "Height Weight\nin Standards";
		}else if(isBodyFat()){
			return "Bodyfat \nin Standards";
		}else{
			return "Failure";
		}
		
		
	}

}
