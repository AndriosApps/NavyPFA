package com.andrios.prt;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    
	private AndriosData mData; //Read in from saved file, passed to all future intents.
	
	
	/** Called when the activity is first created. */
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        readData();
        setConnections();
    }

	private void readData() {
		mData = new AndriosData();
		
	}

	private void setConnections() {
		TabHost mTabHost = getTabHost();
        Intent intent;
        Resources res = getResources(); 
        
        //Setup for Home Tab (Tab 0)
        intent = new Intent().setClass(this, PRTActivity.class);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("PRT").setIndicator("",res.getDrawable(R.drawable.calculator2))
        		.setContent(intent));
        
        //Setup for Workout Tab (Tab 1)
        intent = new Intent().setClass(this, ComingSoonActivity.class);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("BCA").setIndicator("",res.getDrawable(R.drawable.weight2))
        		.setContent(intent));
        
        //Setup for Profile Tab (Tab 2)
        intent = new Intent().setClass(this, ComingSoonActivity.class);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("Alternate Cardio").setIndicator("",res.getDrawable(R.drawable.cardio2))
        		.setContent(intent));
             
        //Setup for Exercise Tab (Tab 3)
        intent = new Intent().setClass(this, InstructionsActivity.class);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("Instructions").setIndicator("",res.getDrawable(R.drawable.instructions2))
        		.setContent(intent));
       

        //Set Tab host to Home Tab
        mTabHost.setCurrentTab(0);
	}
}