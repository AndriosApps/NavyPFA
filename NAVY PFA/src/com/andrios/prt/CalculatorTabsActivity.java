package com.andrios.prt;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

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
        mTabHost.addTab(mTabHost.newTabSpec("PRT").setIndicator("",res.getDrawable(R.drawable.calculator2))
        		.setContent(intent));
        
        //Setup for Workout Tab (Tab 1)
        intent = new Intent().setClass(this, BCAActivity.class);
        intent.putExtra("premium", isPremium);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("BCA").setIndicator("",res.getDrawable(R.drawable.weight2))
        		.setContent(intent));
        
        //Setup for Profile Tab (Tab 2)
        intent = new Intent().setClass(this, CardioActivity.class);
        intent.putExtra("premium", isPremium);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("Alternate Cardio").setIndicator("",res.getDrawable(R.drawable.cardio2))
        		.setContent(intent));
             
      

        //Set Tab host to PRT Tab
        mTabHost.setCurrentTab(0);
	}
	
	
	
	public void onDestroy(){
		super.onDestroy();
		mData.write(this);
		
	}
}