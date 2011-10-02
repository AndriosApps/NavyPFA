package com.andrios.prt;

import java.util.List;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends Activity {
	
	
	Button facebookBTN, twitterBTN, emailBTN, marketBTN;
	GoogleAnalyticsTracker tracker;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.aboutactivity);
        
        setConnections();
        setOnClickListeners();
        setTracker();
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


	private void setConnections() {
		facebookBTN = (Button) findViewById(R.id.aboutActivityFacebookBTN);
		twitterBTN = (Button) findViewById(R.id.aboutActivityTwitterBTN);
		emailBTN = (Button) findViewById(R.id.aboutActivityEmailBTN);
		marketBTN = (Button) findViewById(R.id.aboutActivityMarketBTN);
		facebookBTN.setVisibility(View.INVISIBLE);
	}


	private void setOnClickListeners() {
		emailBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				tracker.trackEvent(
			            "Clicks",  // Category
			            "Link",  // Action
			            "Email", // Label
			            0);       // Value
				
			    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			     
			    emailIntent .setType("plain/text");
			     
			    emailIntent .putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"andriosapps@gmail.com"});
			     
			    emailIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, "Navy PRT Android App");
			     
			    //emailIntent .putExtra(android.content.Intent.EXTRA_TEXT, myBodyText);
			     
			    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

			}

		});
		
		marketBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				tracker.trackEvent(
			            "Clicks",  // Category
			            "Link",  // Action
			            "Market", // Label
			            0);       // Value
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://search?q=pub:AndriOS"));
				startActivity(intent);

				
			}
			
		});
		
		

		facebookBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				//Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/pages/AndriOS/224807700868604"));
				//startActivity(browserIntent);
/*
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setClassName("com.facebook.katana", "com.facebook.katana.ProfileTabHostActivity");
				intent.putExtra("extra_user_id", "224807700868604");
				startActivity(intent);
*/
				tracker.trackEvent(
			            "Clicks",  // Category
			            "Link",  // Action
			            "Facebook", // Label
			            0);       // Value
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "AndriOS Apps");
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Navy PRT");
				startActivity(Intent.createChooser(sharingIntent, "Share using"));

				
			}
			
		});
		
		twitterBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				tracker.trackEvent(
			            "Clicks",  // Category
			            "Link",  // Action
			            "Twitter", // Label
			            0);       // Value
				String message = "@AndriOS_Apps";
				Context context = AboutActivity.this;
				/*	
				try{
				    Intent intent = new Intent(Intent.ACTION_SEND);
				    intent.putExtra(Intent.EXTRA_TEXT, message);
				    intent.setType("text/plain");
				    final PackageManager pm = context.getPackageManager();
				    final List activityList = pm.queryIntentActivities(intent, 0);
				        int len =  activityList.size();
				    for (int i = 0; i < len; i++) {
				        final ResolveInfo app = (ResolveInfo) activityList.get(i);
				        if ("com.twitter.android.PostActivity".equals(app.activityInfo.name)) {
				            final ActivityInfo activity=app.activityInfo;
				            final ComponentName name=new ComponentName(activity.applicationInfo.packageName, activity.name);
				            intent=new Intent(Intent.ACTION_SEND);
				            intent.addCategory(Intent.CATEGORY_LAUNCHER);
				            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				            intent.setComponent(name);
				            intent.putExtra(Intent.EXTRA_TEXT, message);
				            context.startActivity(intent);
				            break;
				        }
				        //TODO Add search for other twitter clients here.
				    }
				}
				catch(final ActivityNotFoundException e) {
					Toast.makeText(AboutActivity.this, "We currently only support the official Twitter Client. Tweet us @AndriOS_Apps", Toast.LENGTH_SHORT).show();
				}

*/
				
				Intent intent = findTwitterClient();
				intent.putExtra(Intent.EXTRA_TEXT, message);
				startActivity(Intent.createChooser(intent, null)); 
	            //context.startActivity(intent);
			}
			
		});
		
		
	}
	
	public Intent findTwitterClient() {
        final String[] twitterApps = {
                // package // name
                "com.twitter.android", // official
                "com.levelup.touiteur", // Plume 
                "com.twidroid", // twidroyd
                "com.handmark.tweetcaster", //
                "com.thedeck.android"   };
        Intent tweetIntent = new Intent();
        tweetIntent.setType("text/plain");
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(
                tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        for (int i = 0; i <twitterApps.length; i++) {
            for (ResolveInfo resolveInfo : list) {
                String p = resolveInfo.activityInfo.packageName;
                if (p != null && p.startsWith(twitterApps[i])) {
                    tweetIntent.setPackage(p);
                    return tweetIntent;
                }
            }
        }
        return null;
    }

    
    
}
