package com.andrios.prt;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;
import java.lang.Math;
import java.util.Observable;
import java.util.Observer;

public class BCAActivity extends Activity implements Observer{
	
	private static int MIN_HEIGHT = 51;
	private static int MAX_HEIGHT = 86;
	private static int MIN_WEIGHT = 90;
	private static int MAX_WEIGHT = 300;
	
	AndriosData mData;
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	ViewFlipper flipper;//Used to Show animation between Back / Front of card. 
	SegmentedControlButton maleRDO, femaleRDO;
	Button maleNeckPlusBTN, maleNeckMinusBTN, femaleNeckPlusBTN, femaleNeckMinusBTN;
	Button maleWaistPlusBTN, maleWaistMinusBTN, femaleWaistPlusBTN, femaleWaistMinusBTN;
	Button femaleHipsPlusBTN, femaleHipsMinusBTN;
	Double neck = 10.0, waist= 30.0, difference = 20.0, percentFat = 0.0;
	Double fneck = 15.0, fwaist= 30.0, fhips = 35.0, fdifference = 50.0, fpercentFat = 0.0;
	CheckBox ageCheckBox;
	
	SeekBar heightSeekBar, weightSeekBar;
	Button heightUpBTN, heightDownBTN, weightUpBTN, weightDownBTN;
	int height = 69, weight = 100, age = 17;
	TextView maleNeckLBL, maleWaistLBL, femaleNeckLBL, femaleWaistLBL, femaleHipsLBL;
	TextView differenceLBL, percentFatLBL, femaleDifferenceLBL, femalePercentFatLBL;
	TextView weightLBL, heightInchLBL, heightFeetLBL;
	TextView HWLBL, bodyFatLBL;
	boolean HWchanged, maleBFchanged, femaleBFchanged;
	
	LinearLayout HWLL, bodyFatLL;
	
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.bcaactivity);
	        
	        
	        setConnections();
	        setOnClickListeners();
	        getExtras();
	        setTracker();
	    }
	
		private void getExtras() {
			Intent intent = this.getIntent();
			
			mData = (AndriosData) intent.getSerializableExtra("data");
			mData.addObserver(this);
			age = mData.getAge();
			if(age > 39){
				ageCheckBox.setChecked(true);
			}else{
				ageCheckBox.setChecked(false);
			}
			
			
			
			if(!mData.getGender()){
				femaleRDO.setChecked(true);
			}
		}

		private void setConnections() {
			ageCheckBox = (CheckBox) findViewById(R.id.bcaActivityAgeCheckBox);
			flipper = (ViewFlipper) findViewById(R.id.details); 
			flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		    flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));  
			maleRDO = (SegmentedControlButton) findViewById(R.id.bcaActivityrMaleRDO);
			femaleRDO = (SegmentedControlButton) findViewById(R.id.bcaActivityrFemaleRDO);
		
			maleNeckPlusBTN = (Button) findViewById(R.id.bcaActivityMaleNeckPlusBTN);
			maleNeckMinusBTN = (Button) findViewById(R.id.bcaActivityMaleNeckMinusBTN);
			maleNeckLBL = (TextView) findViewById(R.id.bcaActivityMaleNeckLBL);
			maleNeckLBL.setText(Double.toString(neck));
			
			maleWaistPlusBTN = (Button) findViewById(R.id.bcaActivityMaleWaistPlusBTN);
			maleWaistMinusBTN = (Button) findViewById(R.id.bcaActivityMaleWaistMinusBTN);
			maleWaistLBL = (TextView) findViewById(R.id.bcaActivityMaleWaistLBL);
			maleWaistLBL.setText(Double.toString(waist));
			
			
			differenceLBL = (TextView) findViewById(R.id.bcaActivityDifferenceLBL);
			differenceLBL.setText(Double.toString(difference));
			percentFatLBL = (TextView) findViewById(R.id.bcaActivityPercentFatLBL);
			percentFatLBL.setText(Double.toString(percentFat));
			
			//Female Side
			femaleNeckPlusBTN = (Button) findViewById(R.id.bcaActivityFemaleNeckPlusBTN);
			femaleNeckMinusBTN = (Button) findViewById(R.id.bcaActivityFemaleNeckMinusBTN);
			femaleNeckLBL = (TextView) findViewById(R.id.bcaActivityFemaleNeckLBL);
			femaleNeckLBL.setText(Double.toString(fneck));
			
			femaleWaistPlusBTN = (Button) findViewById(R.id.bcaActivityFemaleWaistPlusBTN);
			femaleWaistMinusBTN = (Button) findViewById(R.id.bcaActivityFemaleWaistMinusBTN);
			femaleWaistLBL = (TextView) findViewById(R.id.bcaActivityFemaleWaistLBL);
			femaleWaistLBL.setText(Double.toString(fwaist));
			
			femaleHipsPlusBTN = (Button) findViewById(R.id.bcaActivityFemaleHipPlusBTN);
			femaleHipsMinusBTN = (Button) findViewById(R.id.bcaActivityFemaleHipMinusBTN);
			femaleHipsLBL = (TextView) findViewById(R.id.bcaActivityFemaleHipLBL);
			femaleHipsLBL.setText(Double.toString(fhips));
			
			femaleDifferenceLBL = (TextView) findViewById(R.id.bcaActivityFemaleCircumLBL);
			femaleDifferenceLBL.setText(Double.toString(fdifference));
			femalePercentFatLBL = (TextView) findViewById(R.id.bcaActivityFemaleBodyFatLBL);
			femalePercentFatLBL.setText(Double.toString(fpercentFat));
			
			//Height / Weight Sliders
			heightUpBTN = (Button) findViewById(R.id.bcaActivityHeightPlusBTN);
			heightDownBTN = (Button) findViewById(R.id.bcaActivityHeightMinusBTN);
			weightUpBTN = (Button) findViewById(R.id.bcaActivityWeightPlusBTN);
			weightDownBTN = (Button) findViewById(R.id.bcaActivityWeightMinusBTN);
			
			heightSeekBar = (SeekBar) findViewById(R.id.bcaActivityHeightSeekBar);
			weightSeekBar = (SeekBar) findViewById(R.id.bcaActivityWeightSeekBar);
			heightSeekBar.setMax(MAX_HEIGHT - MIN_HEIGHT); //value later offset by MIN_HEIGHT
			heightSeekBar.setProgress(height-MIN_HEIGHT);
			weightSeekBar.setMax(MAX_WEIGHT - MIN_WEIGHT);//Max weight 300lbs
			weightSeekBar.setProgress(weight - MIN_WEIGHT);
			
			weightLBL = (TextView) findViewById(R.id.bcaActivityWeightLBL);
			heightInchLBL = (TextView) findViewById(R.id.bcaActivityHeightInchesLBL);
			heightFeetLBL = (TextView) findViewById(R.id.bcaActivityHeightFeetLBL);
			
			weightLBL.setText(Integer.toString(weight) + "lbs");
			heightInchLBL.setText(Integer.toString(height) + "\"");
			
			heightFeetLBL.setText(formatInches());
			
			HWLBL = (TextView) findViewById(R.id.HeightWeightLBL);

			bodyFatLBL = (TextView) findViewById(R.id.BodyFatLBL);
			
			HWLL = (LinearLayout) findViewById(R.id.bcaActivityHeightWeightLL);

			bodyFatLL = (LinearLayout) findViewById(R.id.bcaActivityBodyFatLL);
			
			
		}

		private void setOnClickListeners() {
			ageCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					if(maleRDO.isChecked()){
						calculateMale();
					}else{
						calculateFemale();
					}
					
				}
				
			});
			maleNeckPlusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					neck = neck + 0.5;
					if(neck >= 25.0){
						neck = 25.0;
					}
					maleNeckLBL.setText(Double.toString(neck));
					difference = waist - neck;
					differenceLBL.setText(Double.toString(difference));
					maleBFchanged = true;
					calculateMale();
					
				}
				
			});
			
			maleNeckMinusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					neck = neck - 0.5;
					if(neck < 10.0){
						neck = 10.0;
					}
					maleNeckLBL.setText(Double.toString(neck));
					difference = waist - neck;
					differenceLBL.setText(Double.toString(difference));
					maleBFchanged = true;
					calculateMale();
				}
				
			});
			
			maleWaistPlusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					waist = waist + 0.5;
					if(waist >= 55.0){
						waist = 55.0;
					}
					maleWaistLBL.setText(Double.toString(waist));
					difference = waist - neck;
					differenceLBL.setText(Double.toString(difference));
					maleBFchanged = true;
					calculateMale();
				}
				
			});
			
			maleWaistMinusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					waist = waist - 0.5;
					if(waist < 20.0){
						waist = 20.0;
					}
					maleWaistLBL.setText(Double.toString(waist));
					difference = waist - neck;
					differenceLBL.setText(Double.toString(difference));
					maleBFchanged = true;
					calculateMale();
				}
				
			});
			
			femaleNeckPlusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					fneck = fneck + 0.5;
					if(fneck >= 25.0){
						fneck = 25.0;
					}
					femaleNeckLBL.setText(Double.toString(fneck));
					fdifference = (fwaist + fhips) - fneck;
					femaleDifferenceLBL.setText(Double.toString(fdifference));
					femaleBFchanged = true;
					calculateFemale();
				}
				
			});
			
			femaleNeckMinusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					fneck = fneck - 0.5;
					if(fneck < 10.0){
						fneck = 10.0;
					}
					femaleNeckLBL.setText(Double.toString(fneck));
					fdifference = (fwaist + fhips) - fneck;
					femaleDifferenceLBL.setText(Double.toString(fdifference));
					femaleBFchanged = true;
					calculateFemale();
				}
				
			});
			
			femaleWaistPlusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					fwaist = fwaist + 0.5;
					if(fwaist >= 55.0){
						fwaist = 55.0;
					}
					femaleWaistLBL.setText(Double.toString(fwaist));
					fdifference = (fwaist + fhips) - fneck;
					femaleDifferenceLBL.setText(Double.toString(fdifference));
					femaleBFchanged = true;
					calculateFemale();
				}
				
			});
			
			femaleWaistMinusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					fwaist = fwaist - 0.5;
					if(fwaist < 20.0){
						fwaist = 20.0;
					}
					femaleWaistLBL.setText(Double.toString(fwaist));
					fdifference = (fwaist + fhips) - fneck;
					femaleDifferenceLBL.setText(Double.toString(fdifference));
					femaleBFchanged = true;
					calculateFemale();
				}
				
			});
			
			femaleHipsPlusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					fhips = fhips + 0.5;
					if(fhips >= 55.0){
						fhips = 55.0;
					}
					femaleHipsLBL.setText(Double.toString(fhips));
					fdifference = (fwaist + fhips) - fneck;
					femaleDifferenceLBL.setText(Double.toString(fdifference));
					femaleBFchanged = true;
					calculateFemale();
				}
				
			});
			
			femaleHipsMinusBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					fhips = fhips - 0.5;
					if(fhips < 10.0){
						fhips = 10.0;
					}
					femaleHipsLBL.setText(Double.toString(fhips));
					fdifference = (fwaist + fhips) - fneck;
					femaleDifferenceLBL.setText(Double.toString(fdifference));
					femaleBFchanged = true;
					calculateFemale();
				}
				
			});
			
		maleRDO.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				flipper.showNext();
				if(HWchanged){
					calcHeightWeight();
				}
				
				if(maleRDO.isChecked()){
					calculateMale();
				}else{
					calculateFemale();
				}
				mData.setGender(maleRDO.isChecked());
			}
			
		});
		
		heightSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				height = arg1 + MIN_HEIGHT;
				updateLBLS();
				
				HWchanged = true;
				calcHeightWeight();
				calcBodyFat();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
		});
		weightSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				weight = arg1 + MIN_WEIGHT;
				updateLBLS();
				HWchanged = true;
				
				calcHeightWeight();
				
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
		});
		
		heightUpBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				height += 1;
				if(height > MAX_HEIGHT){
					height = (MAX_HEIGHT);
				}
				System.out.println("Height " + height);
				heightSeekBar.setProgress(height - MIN_HEIGHT);
				
				
			}
			
		});
		
		heightDownBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				height -= 1;
				if(height < MIN_HEIGHT){
					height = MIN_HEIGHT;
				}
				
				heightSeekBar.setProgress(height - MIN_HEIGHT);
				
				
			}
			
		});
		
		weightUpBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				weight += 1;
				if(weight > MAX_WEIGHT){
					weight = MAX_WEIGHT;
				}
				
				weightSeekBar.setProgress(weight - MIN_WEIGHT);
				
				
			}
			
		});
		
		weightDownBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				weight -= 1;
				if(weight < MIN_WEIGHT){
					weight = MIN_WEIGHT;
				}
				System.out.println("Weight: "+ weight);
				weightSeekBar.setProgress(weight - MIN_WEIGHT);
				
				
			}
			
		});
		
	}
		private void calcBodyFat(){
			if(maleRDO.isChecked() && maleBFchanged){
				calculateMale();
			}else if(!maleRDO.isChecked() && femaleBFchanged){
				calculateFemale();
			}
		}
		private void calculateMale(){
			//Equation derived from DoD Instruction 1308.3 Page 13
			percentFat = 86.010 * Math.log10(waist - neck) - 70.041 * Math.log10(height) + 36.76;
			percentFat = (double) Math.round(percentFat);
			percentFatLBL.setText(Double.toString(percentFat)+ "%");
			if(maleBFchanged){
				double allowableFat = 22.0;
				if(ageCheckBox.isChecked()){
					allowableFat = 23.0;
				}
				if(percentFat > allowableFat){
					bodyFatLL.setBackgroundResource(R.drawable.failbtn);
				}else{
					bodyFatLL.setBackgroundResource(R.drawable.passbtn);
				}
				
			}else{
				bodyFatLL.setBackgroundResource(R.drawable.grey_button);
			}
			
	
		}
		
		private void calculateFemale(){
			fpercentFat = 163.205 * Math.log10(fwaist + fhips - fneck) - 97.684 * Math.log10(height) - 78.387;
			fpercentFat = (double) Math.round(fpercentFat);
			femalePercentFatLBL.setText(Double.toString(fpercentFat)+ "%");
			if(femaleBFchanged){
				double allowableFat = 33.0;
				if(ageCheckBox.isChecked()){
					allowableFat = 34.0;
				}
				if(fpercentFat > allowableFat){
					bodyFatLL.setBackgroundResource(R.drawable.failbtn);
				}else{
					bodyFatLL.setBackgroundResource(R.drawable.passbtn);
					
				}
			}else{
				bodyFatLL.setBackgroundResource(R.drawable.grey_button);
			}
			
		
			
		
		}

		private void setTracker() {
			tracker = GoogleAnalyticsTracker.getInstance();

		    // Start the tracker in manual dispatch mode...
		    tracker.start("UA-23366060-2", this);
		    
			
		}
		
		public void onResume(){
			super.onResume();
			tracker.trackPageView("BCA");
		}
		
		public void onPause(){
			super.onPause();
			tracker.dispatch();
		}
		
		//returns string of type 5'9"
		private String formatInches(){
			String format = "";
			String feet = Integer.toString((int) height / 12);
			String inches = Integer.toString(height % 12);
			format = feet + "'" + inches + "\"";
			return format;
		}
		
		private void updateLBLS(){
			heightFeetLBL.setText(formatInches());
			heightInchLBL.setText(Integer.toString(height)+"\"");
			weightLBL.setText(Integer.toString(weight)+"lbs");
		}
		
		private void calcHeightWeight(){
			boolean inStandards = false;
			
			if(maleRDO.isChecked()){
			
				if(weight > mData.weightMale[height-MIN_HEIGHT]){
					inStandards = false;
				}else{
					inStandards = true;
				}
			}else{
				if(weight > mData.weightFemale[height-MIN_HEIGHT]){
					inStandards = false;
				}else{
					inStandards = true;
				}
			}
			
			if(inStandards){
				
				HWLL.setBackgroundResource(R.drawable.passbtn);
			}else{
				HWLL.setBackgroundResource(R.drawable.failbtn);
			}
		}

		public void update(Observable observable, Object data) {
			age = mData.getAge();
			if(age > 39){
				ageCheckBox.setChecked(true);
			}else{
				ageCheckBox.setChecked(false);
			}
			
			
			
			
			femaleRDO.setChecked(!mData.getGender());
			maleRDO.setChecked(mData.getGender());
			
		}
	  
}
