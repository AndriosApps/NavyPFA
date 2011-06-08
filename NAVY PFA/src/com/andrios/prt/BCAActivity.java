package com.andrios.prt;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.os.Bundle;

public class BCAActivity extends Activity {
	
	AndriosData mData;
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.bcaactivity);
	        
	        getExtras();
	        setConnections();
	        setOnClickListeners();
	        setTracker();
	    }
	
		private void getExtras() {
		// TODO Auto-generated method stub
		
	}

		private void setConnections() {
		// TODO Auto-generated method stub
		
	}

		private void setOnClickListeners() {
		// TODO Auto-generated method stub
		
	}
		
		private void calculateMale(){
			/*
			 *     For men[2], all measurements in inches:

%Fat = 86.010*LOG(abdomen - neck) - 70.041*LOG(height) + 36.76

    For men, all measurements in centimeters:

%Fat = 86.010*LOG(abdomen - neck) - 70.041*LOG(height) + 30.30

LOG refers to the logarithm with a base of 10, or LOG10, not that with a base of e, or LN. LOG(100) = 2.
Online calculators often use the formula that has been used from 1984 to 2002 for the metric variant. When rounded to the nearest percent the difference with the newer one is generally 0%, and 1% in the worst cases.
If you want mathematical/computational precision (because you want to write your own calculator) then use for the last metric constants:

    -( 86.010 - 70.041)*LOG(2.54) + 36.76 = 30.29521038 for the men,
    -(163.205 - 97.684)*LOG(2.54) - 78.387 = -104.9121099 for the women.

*/
			
		}
		
		private void calculateFemale(){
			/*
			 *     For women, all measurements in inches:

%Fat = 163.205*LOG(abdomen + hip - neck) - 97.684*LOG(height) - 78.387

    For women, all measurements in centimeters:

   

%Fat = 163.205*LOG(abdomen + hip - neck) - 97.684*LOG(height) - 104.912
			 */
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
	  
}
