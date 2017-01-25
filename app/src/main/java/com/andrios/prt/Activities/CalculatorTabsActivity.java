package com.andrios.prt.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

import com.andrios.prt.AndriosData;
import com.andrios.prt.BcaActivity;
import com.andrios.prt.NewProfileActivity;
import com.andrios.prt.NewPrtActivity;
import com.andrios.prt.R;

public class CalculatorTabsActivity extends TabActivity {
    
	private AndriosData mData;
	
	boolean isPremium;
   
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
        intent = new Intent().setClass(this, NewPrtActivity.class);
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
        intent = new Intent().setClass(this, NewProfileActivity.class);
        intent.putExtra("premium", isPremium);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("Profile").setIndicator("PROFILE")
        		.setContent(intent));

        //Set Tab host to PRT Tab
        mTabHost.setCurrentTab(0);


            mTabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.color.colorPrimary);
        mTabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.color.colorPrimary);
        mTabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.color.colorPrimary);


	}

	public void onDestroy(){
		super.onDestroy();
		mData.write(this);
		
	}
}