package com.andrios.prt.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

import com.andrios.prt.AndriosData;
import com.andrios.prt.CardioActivity;
import com.andrios.prt.BcaActivity;
import com.andrios.prt.PRTActivity;
import com.andrios.prt.R;

public class CalculatorTabsActivity extends TabActivity {
    
	private AndriosData mData; //Read in from saved file, passed to all future intents.
	
	boolean isPremium;
	/** Called when the activity is first created. */
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.calculatortabsactivity);


        getExtras();

        setConnections();
    }

	private void getExtras() {
		Intent intent = this.getIntent();
		isPremium = intent.getBooleanExtra("premium", false);
		mData = (AndriosData) intent.getSerializableExtra("data");

	}



	private void setConnections() {
		TabHost mTabHost = getTabHost();
        Intent intent;
        Resources res = getResources(); 
        
        //Setup for Home Tab (Tab 0)
        intent = new Intent().setClass(this, PRTActivity.class);
        intent.putExtra("premium", isPremium);        
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("PRT").setIndicator("PRT")
        		.setContent(intent));
        
        //Setup for Workout Tab (Tab 1)
        intent = new Intent().setClass(this, BcaActivity.class);
        intent.putExtra("premium", isPremium);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("BCA").setIndicator("BCA")
        		.setContent(intent));
        
        //Setup for Profile Tab (Tab 2)
        intent = new Intent().setClass(this, CardioActivity.class);
        intent.putExtra("premium", isPremium);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("Alternate Cardio").setIndicator("CARDIO")
        		.setContent(intent));
             
      

        //Set Tab host to PRT Tab
        mTabHost.setCurrentTab(0);
	}
	
	
	
	public void onDestroy(){
		super.onDestroy();
		mData.write(this);
		
	}
}