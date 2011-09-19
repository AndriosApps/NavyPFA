package com.andrios.prt;

import java.util.Calendar;

public class BcaEntry extends LogEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1538116430747812374L;

	public BcaEntry(){
		this.name = "BCA";
		this.c = Calendar.getInstance();
		
	}
	
	
	@Override
	public String getScoreString() {
		// TODO Auto-generated method stub
		return null;
	}

}
