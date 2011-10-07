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

	
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	Button rateBTN;
	
	LinearLayout OPNAVINST_6110j;
	LinearLayout NAVADMIN_203_11;
	LinearLayout NAVADMIN_118_11;
	LinearLayout NAVADMIN_338_10;
	LinearLayout NAVADMIN_256_10;
	LinearLayout NAVADMIN_193_10;
	LinearLayout NAVADMIN_131_10;
	LinearLayout NAVADMIN_247_09;
	LinearLayout NAVADMIN_073_09;
	LinearLayout NAVADMIN_007_09;
	LinearLayout NAVADMIN_312_08;
	LinearLayout NAVADMIN_277_08;
	LinearLayout NAVADMIN_256_08;
	LinearLayout NAVADMIN_191_08;
	LinearLayout NAVADMIN_011_07;
	LinearLayout NAVADMIN_293_06;
	LinearLayout NAVADMIN_072_06;
	LinearLayout NAVADMIN_041_06;
	
	
	
	
	
	
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
		OPNAVINST_6110j = (LinearLayout) findViewById(R.id.OPNAVINST_6110j);

		NAVADMIN_203_11 = (LinearLayout) findViewById(R.id.NAVADMIN_203_11);
		NAVADMIN_118_11 = (LinearLayout) findViewById(R.id.NAVADMIN_118_11);
		NAVADMIN_338_10 = (LinearLayout) findViewById(R.id.NAVADMIN_338_10);
		NAVADMIN_256_10 = (LinearLayout) findViewById(R.id.NAVADMIN_256_10);
		NAVADMIN_193_10 = (LinearLayout) findViewById(R.id.NAVADMIN_193_10);
		NAVADMIN_131_10 = (LinearLayout) findViewById(R.id.NAVADMIN_131_10);
		NAVADMIN_247_09 = (LinearLayout) findViewById(R.id.NAVADMIN_247_09);
		NAVADMIN_073_09 = (LinearLayout) findViewById(R.id.NAVADMIN_073_09);
		NAVADMIN_007_09 = (LinearLayout) findViewById(R.id.NAVADMIN_007_09);
		NAVADMIN_312_08 = (LinearLayout) findViewById(R.id.NAVADMIN_312_08);
		NAVADMIN_277_08 = (LinearLayout) findViewById(R.id.NAVADMIN_277_08);
		NAVADMIN_256_08 = (LinearLayout) findViewById(R.id.NAVADMIN_256_08);
		NAVADMIN_191_08 = (LinearLayout) findViewById(R.id.NAVADMIN_191_08);
		NAVADMIN_011_07 = (LinearLayout) findViewById(R.id.NAVADMIN_011_07);
		NAVADMIN_293_06 = (LinearLayout) findViewById(R.id.NAVADMIN_293_06);
		NAVADMIN_072_06 = (LinearLayout) findViewById(R.id.NAVADMIN_072_06);
		NAVADMIN_041_06 = (LinearLayout) findViewById(R.id.NAVADMIN_041_06);
		
		
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
		
		
		NAVADMIN_203_11.setOnClickListener(new OnClickListener(){

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
		NAVADMIN_118_11.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2011/NAV11118.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_338_10.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2010/NAV10338.txt"));
				startActivity(browserIntent);
			}
			
		}); 
		NAVADMIN_256_10.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2010/NAV10256.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_193_10.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2010/NAV10193.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_131_10.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2010/NAV10131.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_247_09.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2009/NAV09247.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_073_09.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2009/NAV09073.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_007_09.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2009/NAV09007.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_312_08.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2008/NAV08312.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_277_08.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2008/NAV08277.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_256_08.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2008/NAV08256.txt"));
				startActivity(browserIntent);
			}
			
		}); 
		NAVADMIN_191_08.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2008/NAV08191.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_011_07.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2007/NAV07011.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_293_06.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2006/NAV06293.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_072_06.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2006/NAV06072.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_041_06.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "NAVADMIN 203/11", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2006/NAV06041.txt"));
				startActivity(browserIntent);
			}
			
		});  
		
		
		
		
		OPNAVINST_6110j.setOnClickListener(new OnClickListener(){

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
		


		
	}
	
	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.start(this.getString(R.string.ga_api_key), getApplicationContext());
	}
	
	public void onResume(){
		super.onResume();
		tracker.trackPageView("/" + this.getLocalClassName());
	}
	
	public void onPause(){
		super.onPause();
		tracker.dispatch();
	}
}
