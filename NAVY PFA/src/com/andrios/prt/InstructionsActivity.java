package com.andrios.prt;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InstructionsActivity extends Activity {

	TextView opnavLBL, navadmin203_11LBL, navadmin1LBL, navadmin2LBL, navadmin3LBL;
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	Button rateBTN;
	boolean isPremium;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructionsactivity);
        
        getExtras();
        setConnections();
        setOnClickListeners();
        setTracker();
    }
    

	private void getExtras() {
		Intent intent = this.getIntent();
		
		isPremium = intent.getBooleanExtra("premium", false);	
		
	}


	private void setConnections() {
		rateBTN = (Button) findViewById(R.id.instructionActivityRateBTN);
		opnavLBL = (TextView) findViewById(R.id.instructionActivityOPNAVLBL);

		navadmin203_11LBL = (TextView) findViewById(R.id.instructionActivityNAVADMIN20311LBL);
		navadmin1LBL = (TextView) findViewById(R.id.instructionActivityNAVADMIN1LBL);
		navadmin2LBL = (TextView) findViewById(R.id.instructionActivityNAVADMIN2LBL);
		navadmin3LBL = (TextView) findViewById(R.id.instructionActivityNAVADMIN3LBL);
		
		if(!isPremium){
			adView = (AdView)this.findViewById(R.id.instructionsAdView);
		      
		    request = new AdRequest();
			request.setTesting(false);
			adView.loadAd(request);
		}else{
			LinearLayout adLL = (LinearLayout) findViewById(R.id.instructionActivityAdLL);
			adLL.setVisibility(View.GONE);
		}
		
		
	}

	private void setOnClickListeners() {
		rateBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://details?id=com.andrios.prt"));
				startActivity(intent);
				
			}
			
		});
		
		navadmin203_11LBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2011/NAV11203.txt"));
				startActivity(browserIntent);
			}
			
		});
		
		opnavLBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "OPNAV INST 6110.1J", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://navy-fitness.com/wp-content/uploads/2011/07/6110.1J-Physical-Readiness-program.pdf"));
				startActivity(browserIntent);
			}
			
		});
		navadmin1LBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 180-05", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.navy-prt.com/navadmin180-05.html"));
				startActivity(browserIntent);
			}
			
		});
		navadmin2LBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 293-06", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.navy-prt.com/navadmin293-06.html"));
				startActivity(browserIntent);
			}
			
		});
		navadmin3LBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 011-07", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.navy-prt.com/navadmin011-07.html"));
				startActivity(browserIntent);
			}
			
		});


		
	}
	
	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();

	    // Start the tracker in manual dispatch mode...
	    tracker.start("UA-23366060-2", this);
	}

	
	public void onResume(){
		super.onResume();
		tracker.trackPageView("Instructions");
	}
	
	public void onPause(){
		super.onPause();
		tracker.dispatch();
	}
}
