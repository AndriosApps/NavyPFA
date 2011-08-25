package com.andrios.prt;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CardioActivity extends Activity{

	private static int MAXWEIGHT = 300;
	private static int MINWEIGHT = 75;
	private static int MAXCAL = 1000;
	private static int MINCAL = 25;
	
	TextView weightLBL, calorieLBL, bikeLBL;
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	Button weightUpBTN, weightDownBTN, calorieUpBTN, calorieDownBTN;
	SegmentedControlButton maleRDO;
	SeekBar weightSeekBar, calorieSeekBar;
	
	int weight = MINWEIGHT, calories = MINCAL;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardioactivity);
        

        setConnections();
        setOnClickListeners();
        setTracker();
        
        adView = (AdView)this.findViewById(R.id.cardioAdView);
	      
	    request = new AdRequest();
		request.setTesting(false);
		adView.loadAd(request);
    }

    
    
    
	private void setConnections() {
		weightLBL = (TextView) findViewById(R.id.cardioActivityWeightLBL);
		weightLBL.setText(Integer.toString(weight));
		calorieLBL = (TextView) findViewById(R.id.cardioActivityCalorieLBL);
		calorieLBL.setText(Integer.toString(calories));
		bikeLBL = (TextView) findViewById(R.id.cardioActivityBikeLBL);
		bikeLBL.setText("");
		
		weightUpBTN = (Button) findViewById(R.id.cardioActivityWeightPlusBTN);
		weightDownBTN = (Button) findViewById(R.id.cardioActivityWeightMinusBTN);
		calorieUpBTN = (Button) findViewById(R.id.cardioActivityCaloriePlusBTN);
		calorieDownBTN = (Button) findViewById(R.id.cardioActivityCalorieMinusBTN);

		weightSeekBar = (SeekBar) findViewById(R.id.cardioActivityWeightSeekBar);
		weightSeekBar.setMax(MAXWEIGHT - MINWEIGHT);
		calorieSeekBar = (SeekBar) findViewById(R.id.cardioActivityCalorieSeekBar);
		calorieSeekBar.setMax(MAXCAL - MINCAL);
		maleRDO = (SegmentedControlButton) findViewById(R.id.cardioActivityMaleRDO);
	}




	private void setOnClickListeners() {
		weightSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				weight = arg1 + MINWEIGHT;
				weightLBL.setText(Integer.toString(weight));
				calcTimes();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		weightUpBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				weight += 1;
				if(weight > MAXWEIGHT){
					weight = MAXWEIGHT;
				}
				weightSeekBar.setProgress(weight - MINWEIGHT);
				
			}
			
		});
		
		weightDownBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				weight -= 1;
				if(weight < MINWEIGHT){
					weight = MINWEIGHT;
				}
				weightSeekBar.setProgress(weight - MINWEIGHT);
				
			}
			
		});
		
		calorieSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				calories = arg1 + MINCAL;
				calorieLBL.setText(Integer.toString(calories));
				calcTimes();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		calorieUpBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				calories += 1;
				if(calories > MAXCAL){
					weight = MAXCAL;
				}
				calorieSeekBar.setProgress(calories - MINCAL);
				
				
			}
			
		});
		
		calorieDownBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				calories -= 1;
				if(calories < MINCAL){
					weight = MINCAL;
				}
				calorieSeekBar.setProgress(calories - MINCAL);
				
			}
			
		});
		
		maleRDO.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				calcTimes();
				
			}
			
		});
		
	}


	private void calcTimes(){
		if(calories != 0){
			calcBike();
		}
		
	}
	
	private void calcBike(){
		double bikeTime;
		if(maleRDO.isChecked()){
			bikeTime = 6.296 + 4.087 * weight / calories;
			
		}else{
			bikeTime = 6.296 + 4.087 * weight / calories + 1.77;
			
			
		}
		
		int minutes = (int) bikeTime;
		float remainder = (float) (bikeTime - minutes);
		int seconds = (int) (60 * remainder);
		String second;
		if(seconds < 10){
			second = "0"+seconds;
		}else{
			second = Integer.toString(seconds);
		}
		bikeLBL.setText(Integer.toString(minutes)+":"+ second);
	}
	
	private int CT9500HR(int kcal){
		//TODO Finish this equation
		int mySeconds= 0;
		double totalEnergy = 0; 
		double rawRuntimeEstimate = 0;
				
										//step 2 Unchanged for CT 9500 HR
		mySeconds = kcal / 12; 			// Step 3 energy production rate in kcal/min
		totalEnergy = weight * 1.09; 	// Step 4 total energy required to run 1.5 mi.
		rawRuntimeEstimate = totalEnergy / mySeconds; // Step 5 calculate raw Runtime Estimate
		rawRuntimeEstimate = rawRuntimeEstimate * 60;    // Step 6 runtime in seconds. 
		if(maleRDO.isChecked()){
			
			mySeconds += 68;		//Add 1:08 for males. 
		}else{
			mySeconds += 135;		//Add 2:15 for females.
		}
		return mySeconds;
	}


	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();

	    // Start the tracker in manual dispatch mode...
	    tracker.start("UA-23366060-2", this);
	}

	
	public void onResume(){
		super.onResume();
		tracker.trackPageView("Cardio");
	}
	
	public void onPause(){
		super.onPause();
		tracker.dispatch();
	}
}
