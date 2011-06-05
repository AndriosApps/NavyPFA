package com.andrios.prt;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.os.Bundle;

public class ComingSoonActivity extends Activity{

	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comingsoonactivity);
        
		adView = (AdView)this.findViewById(R.id.comingSoonAdView);
	      
	    request = new AdRequest();
		request.setTesting(false);
		adView.loadAd(request);
		
		setTracker();
    }
	
	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();

	    // Start the tracker in manual dispatch mode...
	    tracker.start("UA-23366060-2", this);
	    
		
	}


	public void onResume(){
		super.onResume();
		tracker.trackPageView("Coming Soon");
	}
	
	public void onPause(){
		super.onPause();
		tracker.dispatch();
	}
	
}
