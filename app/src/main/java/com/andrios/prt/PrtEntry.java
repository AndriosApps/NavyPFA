package com.andrios.prt;

import java.util.Calendar;

public class PrtEntry extends LogEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1538116430747812374L;
	boolean isPushupsWaived;
	boolean isSitupsWaived;
	boolean isRunWaived;
	boolean isWaived0;
	boolean isWaived1;
	boolean isWaived2;
	boolean isWaived3;
	boolean isWaived4;
	boolean isWaived5;
	boolean isWaived6;
	String alternateCardio;
	String pushups;
	String situps;
	String run;
	String pushupScore;
	String situpScore;
	String runScore;
	String totalScore;
	
	
	
	public PrtEntry(String pushups, String situps, String run, String pushupScore,
		String situpScore, String runScore, String totalScore){
		this.name = "PRT";
		this.c = Calendar.getInstance();
		this.pushups = pushups;
		this.situps = situps;
		this.run = run;
		this.pushupScore = pushupScore;
		this.situpScore = situpScore;
		this.runScore = runScore;
		this.totalScore = totalScore;
		this.layout = R.layout.list_item_prt_entry;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.andrios.prt.LogEntry#getScoreString()
	 */
	@Override
	public String getScoreString() {
		return totalScore;
	}
	
	/*
	 * Getter Methods
	 */
	
	public String getSitups(){
		return situps;
	}
	
	public String getPushups(){
		return pushups;
	}
	
	public String getRun(){
		return run;
	}
	
	public String getPushupScore(){
		return pushupScore;
	}
	
	public String getSitupScore(){
		return situpScore;
	}
	
	public String getRunScore(){
		return runScore;
	}
	
	public String getTotalScore(){
		return totalScore;
	}

	public String getAlternateCardio() {
		return alternateCardio;
	}

	public void setAlternateCardio(String alternateCardio) {
		this.alternateCardio = alternateCardio;
	}
}
