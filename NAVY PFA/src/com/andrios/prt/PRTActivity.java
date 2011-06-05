package com.andrios.prt;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class PRTActivity extends Activity {
	
	RadioButton maleRDO, femaleRDO;
	CheckBox weightCheckBox, SitReachCheckBox;
	SeekBar ageSeekBar, pushupSeekBar, situpSeekBar, runSeekBar;
	TextView ageLBL, pushupLBL, situpLBL, minutesLBL, runLBL, scoreLBL;
	Button minuteUpBTN, minuteDownBTN, secondUpBTN, secondDownBTN;
	AndriosData mData;
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	
	int age = 18, pushups = 0, situps = 0, runtime = 0, minutes, seconds;
	boolean male;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prtactivity);
        
        getExtras();
        setConnections();
        setOnClickListeners();
        setTracker();
    }



	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();

	    // Start the tracker in manual dispatch mode...
	    tracker.start("UA-23366060-2", this);
	    
		
	}



	private void getExtras() {
		Intent intent = this.getIntent();
		
		mData = (AndriosData) intent.getSerializableExtra("data");
		
	}

	private void setConnections() {


		maleRDO  = (RadioButton) findViewById(R.id.calculatorMaleRDO); 
		femaleRDO  = (RadioButton) findViewById(R.id.calculatorFemaleRDO);

		weightCheckBox  = (CheckBox) findViewById(R.id.calculatorWeightCheckBox);
		SitReachCheckBox  = (CheckBox) findViewById(R.id.calculatorSitReachCheckBox);

		ageSeekBar = (SeekBar) findViewById(R.id.calculatorAgeSeekBar); 
		pushupSeekBar = (SeekBar) findViewById(R.id.calculatorPushupSeekBar); 
		situpSeekBar = (SeekBar) findViewById(R.id.calculatorSitUpSeekBar);
		runSeekBar = (SeekBar) findViewById(R.id.calculatorRunTimeSeekBar);
		 
		scoreLBL = (TextView) findViewById(R.id.calculatorScoreLBL);
		ageLBL = (TextView) findViewById(R.id.calculatorAgeLBL);
		pushupLBL = (TextView) findViewById(R.id.calculatorPushUpLBL); 
		situpLBL = (TextView) findViewById(R.id.calculatorSitUpLBL);
		runLBL = (TextView) findViewById(R.id.calculatorRunLBL);
		
		
		secondUpBTN = (Button) findViewById(R.id.calculatorSecondsUpBTN);
		
		secondDownBTN = (Button) findViewById(R.id.calculatorSecondsDownBTN);
		
		ageSeekBar.setMax(100);//max age 100
		ageSeekBar.setProgress(18);
		
		pushupSeekBar.setMax(100);//max pushups is 92
		situpSeekBar.setMax(110);//max situps is 109 (19 yr male)
		runSeekBar.setMax(1260);//max runtime 17:23 (50yr female) 18 min * 60 = 1080
		
		adView = (AdView)this.findViewById(R.id.homeAdView);
	      
	    request = new AdRequest();
		request.setTesting(false);
		adView.loadAd(request);
	}
	
	private void setOnClickListeners() {
		ageSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				age = arg1;
				ageLBL.setText(Integer.toString(age));
				calculateScore();
				
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		pushupSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				pushups = arg1;
				pushupLBL.setText(Integer.toString(pushups));
				calculateScore();
				
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		situpSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				situps = arg1;
				situpLBL.setText(Integer.toString(situps));
				calculateScore();
				
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		runSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				runtime = arg1;
				minutes = (Integer) runtime / 60;
				seconds = runtime % 60;
				formatTimer();
				calculateScore();
				
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
		});
		

		secondUpBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				runtime += 1;
				if(runtime > 1080){
					runtime = 1080;
				}
				minutes = (Integer) runtime / 60;
				seconds = runtime % 60;
				formatTimer();
				runSeekBar.setProgress(runtime);
				calculateScore();
			}
			
		});
		
		secondDownBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				runtime -= 1;
				if(runtime < 0){
					runtime = 0;
				}
				minutes = (Integer) runtime / 60;
				seconds = runtime % 60;
				formatTimer();
				runSeekBar.setProgress(runtime);
				calculateScore();
			}
			
		});
		
		maleRDO.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				calculateScore();
				
			}
			
		});
		weightCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				calculateScore();
				
			}
			
		});
		SitReachCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				calculateScore();
				
			}
			
		});
		
		
	}
	
	private void formatTimer(){
		String minutesTXT, secondsTXT;
		if(minutes < 10){
			minutesTXT = "0"+Integer.toString(minutes);
		}else{
			minutesTXT = Integer.toString(minutes);
		}
		if(seconds < 10){
			secondsTXT = "0"+Integer.toString(seconds);
		}else{
			secondsTXT = Integer.toString(seconds);
		}
		runLBL.setText(minutesTXT + ":" + secondsTXT);
		
		
	}
	
	private void calculateScore(){
		if(maleRDO.isChecked()){
			if(age < 20){
				calculateMale(mData.pushupMale17, mData.situpMale17, mData.runMale17);
			}else if(age < 25){
				calculateMale(mData.pushupMale20, mData.situpMale20, mData.runMale20);
			}else if(age < 30){
				calculateMale(mData.pushupMale25, mData.situpMale25, mData.runMale25);
			}else if(age < 35){
				calculateMale(mData.pushupMale30, mData.situpMale30, mData.runMale30);
			}else if(age < 40){
				calculateMale(mData.pushupMale35, mData.situpMale35, mData.runMale35);
			}else if(age < 45){
				calculateMale(mData.pushupMale40, mData.situpMale40, mData.runMale40);
			}else if(age < 50){
				calculateMale(mData.pushupMale45, mData.situpMale45, mData.runMale45);
			}else if(age < 55){
				calculateMale(mData.pushupMale50, mData.situpMale50, mData.runMale50);
			}else if(age < 60){
				calculateMale(mData.pushupMale55, mData.situpMale55, mData.runMale55);
			}else if(age < 65){
				calculateMale(mData.pushupMale60, mData.situpMale60, mData.runMale60);
			}else{
				calculateMale(mData.pushupMale65, mData.situpMale65, mData.runMale65);
			}
			
		}else{
			if(age < 20){
				calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.runFemale17);
			}else if(age < 25){
				calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.runFemale20);
			}else if(age < 30){
				calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.runFemale25);
			}else if(age < 35){
				calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.runFemale30);
			}else if(age < 40){
				calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.runFemale35);
			}else if(age < 45){
				calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.runFemale40);
			}else if(age < 50){
				calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.runFemale45);
			}else if(age < 55){
				calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.runFemale50);
			}else if(age < 60){
				calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.runFemale55);
			}else if(age < 65){
				calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.runFemale60);
			}else{
				calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.runFemale65);
			}
		}
		
		if(!weightCheckBox.isChecked() || !SitReachCheckBox.isChecked()){
			scoreLBL.setText("Fail");
		}
		
	}



	private void calculateMale(int[] pushupMale, int[] situpMale, int[] runMale) {
		int totalScore = 0;
		int[] Scores = {45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
		boolean fail0 = true;//pushups
		boolean fail1 = true;//situps
		boolean fail2 = true;//run
		for(int i = 11; i >= 0; i--){
			if(pushups >= pushupMale[i]){
				if(i > 0){
					totalScore += Scores[i];
					fail0 = false;
					System.out.println("Pushup Score " + Scores[i-1]);
				}
				
				break;
			}
		}
		for(int i = 11; i >= 0; i--){
			if(situps >= situpMale[i]){
				if(i > 0){
					totalScore += Scores[i];
					fail1 = false;
					System.out.println("Situp Score " + Scores[i-1]);
				}	
			
				break;
			}
		}
		for(int i = 11; i >= 0; i--){
			if(runtime <= runMale[i]){
				if(i > 0){
					totalScore += Scores[i];
					fail2 = false;
					System.out.println("Run Score " + Scores[i-1]);
				}
				break;
			}
		}
		
		if(!fail0 && !fail1 && !fail2){

			System.out.println("Total Score " + totalScore / 3);
			if((totalScore / 3) < 50){
				scoreLBL.setText("Probationary");
			}else if((totalScore / 3) < 55){
				scoreLBL.setText("Satisfactory Medium");
			}else if((totalScore / 3)< 60){
				scoreLBL.setText("Satisfactory High");
			}else if((totalScore / 3)< 65){
				scoreLBL.setText("Good Low");
			}else if((totalScore / 3)< 70){
				scoreLBL.setText("Good Medium");
			}else if((totalScore / 3)< 75){
				scoreLBL.setText("Good High");
			}else if((totalScore / 3)< 80){
				scoreLBL.setText("Excellent Low");
			}else if((totalScore / 3)< 85){
				scoreLBL.setText("Excellent Medium");
			}else if((totalScore / 3)< 90){
				scoreLBL.setText("Excellent High");
			}else if((totalScore / 3) < 95){
				scoreLBL.setText("Outstanding Low");
			}else if((totalScore / 3) < 100){
				scoreLBL.setText("Outstanding Medium");
			}else if((totalScore / 3) == 100){
				scoreLBL.setText("Outstanding High");
			}
		}else{
			scoreLBL.setText("Fail");
		}
	
		
		
	}



	private void calculateFemale(int[] pushupFemale, int[] situpFemale, int[] runFemale) {
		int totalScore = 0;
		int[] Scores = {45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
		boolean fail0 = true;//pushups
		boolean fail1 = true;//situps
		boolean fail2 = true;//run
		for(int i = 11; i >= 0; i--){
			if(pushups >= pushupFemale[i]){
				if(i > 0){
					totalScore += Scores[i];
					fail0 = false;
					System.out.println("Pushup Score " + Scores[i-1]);
				}
				break;
			}
		}
		for(int i = 11; i >= 0; i--){
			if(situps >= situpFemale[i]){
				if(i > 0){
					totalScore += Scores[i];
					fail1=false;
					System.out.println("Situp Score " + Scores[i-1]);
				}	
				
				break;
			}
		}
		for(int i = 11; i >= 0; i--){
			if(runtime <= runFemale[i]){
				if(i > 0){
					totalScore += Scores[i];
					fail2 = false;
					System.out.println("Run Score " + Scores[i-1]);
				}
				break;
			}
		}
		
		if(!fail0 && !fail1 && !fail2){

			System.out.println("Total Score " + totalScore / 3);
			if((totalScore / 3) < 50){
				scoreLBL.setText("Probationary");
			}else if((totalScore / 3) < 55){
				scoreLBL.setText("Satisfactory Medium");
			}else if((totalScore / 3)< 60){
				scoreLBL.setText("Satisfactory High");
			}else if((totalScore / 3)< 65){
				scoreLBL.setText("Good Low");
			}else if((totalScore / 3)< 70){
				scoreLBL.setText("Good Medium");
			}else if((totalScore / 3)< 75){
				scoreLBL.setText("Good High");
			}else if((totalScore / 3)< 80){
				scoreLBL.setText("Excellent Low");
			}else if((totalScore / 3)< 85){
				scoreLBL.setText("Excellent Medium");
			}else if((totalScore / 3)< 90){
				scoreLBL.setText("Excellent High");
			}else if((totalScore / 3) < 95){
				scoreLBL.setText("Outstanding Low");
			}else if((totalScore / 3) < 100){
				scoreLBL.setText("Outstanding Medium");
			}else if((totalScore / 3) == 100){
				scoreLBL.setText("Outstanding High");
			}
		}else{
			scoreLBL.setText("Fail");
		}
	
		
		
	}
	public void onResume(){
		super.onResume();
		tracker.trackPageView("PRT");
	}
	
	public void onPause(){
		super.onPause();
		tracker.dispatch();
	}

}
