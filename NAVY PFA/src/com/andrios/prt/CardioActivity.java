package com.andrios.prt;

import java.util.Observable;
import java.util.Observer;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CardioActivity extends Activity implements Observer{

	private static int MAXWEIGHT = 300;
	private static int MINWEIGHT = 75;
	private static int MAXCAL = 400;
	private static int MINCAL = 25;
	
	AndriosData mData;
	Spinner bikeSpinner, ellipticalSpinner;
	TextView weightLBL, calorieLBL, bikeLBL, ellipticalLBL;
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	Button weightUpBTN, weightDownBTN, calorieUpBTN, calorieDownBTN;
	SegmentedControlButton maleRDO, femaleRDO;
	SeekBar weightSeekBar, calorieSeekBar;
	boolean isPremium;
	private String bikeArray[], ellipticalArray[];
	int weight = MINWEIGHT, calories = MINCAL;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardioactivity);
        

        setConnections();
        setOnClickListeners();
        getExtras();
        weightSeekBar.setProgress(200-MINWEIGHT);
		calorieSeekBar.setProgress(200-MINCAL);
        setTracker();
        
        
    }

	private void getExtras() {
		Intent intent = this.getIntent();
		isPremium = intent.getBooleanExtra("premium", false);
		mData = (AndriosData) intent.getSerializableExtra("data");
		mData.addObserver(this);
		
		
		
		
		
		femaleRDO.setChecked(!mData.getGender());
		
		
	}
    
    
	private void setConnections() {
		bikeArray=new String[1];
		bikeArray[0]="95 CI";
		
		bikeSpinner = (Spinner) findViewById(R.id.cardioActivityBikeSpinner); 
		ArrayAdapter adapter = new ArrayAdapter(this,
				R.layout.my_spinner_item, bikeArray);
		bikeSpinner.setAdapter(adapter);
		
		ellipticalArray=new String[5];
		ellipticalArray[0]="CT 9500 HR";
		ellipticalArray[1]="CT 9500";
		ellipticalArray[2]="95 XI";
		ellipticalArray[3]="E9 16";
		ellipticalArray[4]="EFX 556";
		
		ellipticalSpinner = (Spinner) findViewById(R.id.cardioActivityEllipticalSpinner); 
		ArrayAdapter adapter2 = new ArrayAdapter(this,
				R.layout.my_spinner_item, ellipticalArray);
		ellipticalSpinner.setAdapter(adapter2);

		
		weightLBL = (TextView) findViewById(R.id.cardioActivityWeightLBL);
		weightLBL.setText(Integer.toString(weight));
		calorieLBL = (TextView) findViewById(R.id.cardioActivityCalorieLBL);
		calorieLBL.setText(Integer.toString(calories));
		bikeLBL = (TextView) findViewById(R.id.cardioActivityBikeLBL);
		bikeLBL.setText("");
		
		
		ellipticalLBL = (TextView) findViewById(R.id.cardioActivityEllipticalLBL);
		ellipticalLBL.setText("");
		
		weightUpBTN = (Button) findViewById(R.id.cardioActivityWeightPlusBTN);
		weightDownBTN = (Button) findViewById(R.id.cardioActivityWeightMinusBTN);
		calorieUpBTN = (Button) findViewById(R.id.cardioActivityCaloriePlusBTN);
		calorieDownBTN = (Button) findViewById(R.id.cardioActivityCalorieMinusBTN);

		weightSeekBar = (SeekBar) findViewById(R.id.cardioActivityWeightSeekBar);
		weightSeekBar.setMax(MAXWEIGHT - MINWEIGHT);
		
		calorieSeekBar = (SeekBar) findViewById(R.id.cardioActivityCalorieSeekBar);
		calorieSeekBar.setMax(MAXCAL - MINCAL);
		
		maleRDO = (SegmentedControlButton) findViewById(R.id.cardioActivityMaleRDO);
		femaleRDO = (SegmentedControlButton) findViewById(R.id.cardioActivityFemaleRDO);
		if(!isPremium){
			adView = (AdView)this.findViewById(R.id.cardioAdView);
		      
		    request = new AdRequest();
			request.setTesting(false);
			adView.loadAd(request);
		}
	}




	private void setOnClickListeners() {
		ellipticalSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				calcTimes();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
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
				mData.setGender(maleRDO.isChecked());
			}
			
		});
		
	}


	private void calcTimes(){
		if(calories != 0){
			calcBike();
			calcElliptical(calories);
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
	
	private int calcRawRunTime(int calories, int weight){
		double myCalories = (double) calories;
		int posit = ellipticalSpinner.getSelectedItemPosition();
		if(posit == 2){
			myCalories += 13;
		}else if(posit == 3){
			myCalories += 20;
		}if(posit == 4){
			myCalories += 7;
		}
		//TODO Add rest of ellipticals here. 
		double myWeight = weight * 0.45359237; // convert to kg
		double ER = 2.413 * myWeight;
		double EER = (double) myCalories / 12.0;
		double T = ER / EER;
		
		int totalSeconds = (int) (T*60);
		return totalSeconds;
	}
	
	private void calcElliptical(int kcal){
		
		int mySeconds = calcRawRunTime(kcal,weight);
		System.out.println("Raw Run Time: " + mySeconds);
		if(maleRDO.isChecked()){
			
			mySeconds += 68;		//Add 1:08 for males. 
		}else{
			mySeconds += 135;		//Add 2:15 for females.
		}
		ellipticalLBL.setText(formatTimer(mySeconds));
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
	
	private String formatTimer(int seconds){
		String minutesTXT, secondsTXT;
		int minutes = (int) Math.floor(seconds/60);
		int secondsPrep = seconds % 60;
		if(minutes < 10){
			minutesTXT = "0"+Integer.toString(minutes);
		}else{
			minutesTXT = Integer.toString(minutes);
		}
		if(secondsPrep < 10){
			secondsTXT = "0"+Integer.toString(secondsPrep);
		}else{
			secondsTXT = Integer.toString(secondsPrep);
		}
		
		String formattedTime = minutesTXT + ":" + secondsTXT;
		return formattedTime;
		
		
	}

	public void update(Observable observable, Object data) {
		femaleRDO.setChecked(!mData.getGender());
		maleRDO.setChecked(mData.getGender());
		
	}
}
