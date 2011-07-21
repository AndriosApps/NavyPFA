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
import android.widget.TextView;

public class InstructionsActivity extends Activity {

	TextView opnavLBL, navadmin1LBL, navadmin2LBL, navadmin3LBL, npcLBL;
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	Button rateBTN;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructionsactivity);
        

        setConnections();
        setOnClickListeners();
        setTracker();
    }
    

	private void setConnections() {
		rateBTN = (Button) findViewById(R.id.instructionActivityRateBTN);
		opnavLBL = (TextView) findViewById(R.id.instructionActivityOPNAVLBL);
		navadmin1LBL = (TextView) findViewById(R.id.instructionActivityNAVADMIN1LBL);
		navadmin2LBL = (TextView) findViewById(R.id.instructionActivityNAVADMIN2LBL);
		navadmin3LBL = (TextView) findViewById(R.id.instructionActivityNAVADMIN3LBL);
		npcLBL = (TextView) findViewById(R.id.instructionActivityNPCLBL);
		
		adView = (AdView)this.findViewById(R.id.instructionsAdView);
	      
	    request = new AdRequest();
		request.setTesting(false);
		adView.loadAd(request);
		
	}

	private void setOnClickListeners() {
		rateBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://details?id=com.andrios.prt"));
				startActivity(intent);
				
			}
			
		});
		opnavLBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "OPNAV INST", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.navy-prt.com/files/6110.1H.pdf"));
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
		npcLBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NPC.navy.mil", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.npc.navy.mil/"));
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
