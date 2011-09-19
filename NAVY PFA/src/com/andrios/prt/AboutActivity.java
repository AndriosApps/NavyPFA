package com.andrios.prt;

import java.util.ArrayList;
import java.util.List;

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
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AboutActivity extends Activity {
	
	
	Button facebookBTN, twitterBTN, emailBTN, marketBTN;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.aboutactivity);
        
        setConnections();
        setOnClickListeners();
    
    }


	private void setConnections() {
		facebookBTN = (Button) findViewById(R.id.aboutActivityFacebookBTN);
		twitterBTN = (Button) findViewById(R.id.aboutActivityTwitterBTN);
		emailBTN = (Button) findViewById(R.id.aboutActivityEmailBTN);
		marketBTN = (Button) findViewById(R.id.aboutActivityMarketBTN);
	}


	private void setOnClickListeners() {
		emailBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
			    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			     
			    emailIntent .setType("plain/text");
			     
			    emailIntent .putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"andriosapps@gmail.com"});
			     
			    emailIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, "RE: "+ "@string/app_name");
			     
			    //emailIntent .putExtra(android.content.Intent.EXTRA_TEXT, myBodyText);
			     
			    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

			}

		});
		
		marketBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://search?q=pub:AndriOS"));
				startActivity(intent);

				
			}
			
		});
		
		

		facebookBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				//Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/pages/AndriOS/224807700868604"));
				//startActivity(browserIntent);

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setClassName("com.facebook.katana", "com.facebook.katana.ProfileTabHostActivity");
				intent.putExtra("extra_user_id", "kristen.korpacz");
				startActivity(intent);

				

				
			}
			
		});
		
		twitterBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				//System.out.println("CLICK CLICK");
				//System.out.println(intentTwitter("@AndriOS_Apps"));

				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/html");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>Hello!.</p>"));
				startActivity(Intent.createChooser(sharingIntent,"Share using"));


			}
			
		});
		
		
	}
    public boolean intentTwitter(String message){  
        // Boolean to show if we succeeded or not  
        // we assume we did until proven otherwise.  
        boolean success=true;  
         
        Context context = getApplication();
        Intent intent;
        /*
        try {
       intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");
        final PackageManager pm = context.getPackageManager();
        final List activityList = pm.queryIntentActivities(intent, 0);
        int len = activityList.size();
        for (int i = 0; i < len; i++) {
        final ResolveInfo app = (ResolveInfo) activityList.get(i);
        if ("com.twitter.android.PostActivity"
        .equals(app.activityInfo.name)) {
        final ActivityInfo activity = app.activityInfo;
        final ComponentName name = new ComponentName(
        activity.applicationInfo.packageName, activity.name);
        intent = new Intent(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.setComponent(name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        context.startActivity(intent);
        break;
        }
        }
        } catch (final ActivityNotFoundException e) {
        	success = false;
        Toast.makeText(this, "Damn! no suitable Twitter apps found.",
        Toast.LENGTH_SHORT) .show();
        }
        */
        if(true){
        	 //Try twidroidpro first  
            intent = new Intent("com.twidroidpro.SendTweet");  
            intent.putExtra("com.twidroidpro.extra.MESSAGE", message);   
            intent.setType("application/twitter");   
              try{  
               startActivityForResult(intent, 1);  
              }  
              catch(ActivityNotFoundException e){  
               success=false;  
              }  
             
            // Then twidroid if we failed  
            if (!success){  
             success=true;  
             intent = new Intent("com.twidroid.SendTweet");  
             intent.putExtra("com.twidroid.extra.MESSAGE", message);   
             intent.setType("application/twitter");   
               try{  
                startActivityForResult(intent, 1);  
               }  
               catch(ActivityNotFoundException e){  
                success=false;  
               }  
            }  
        }
       
            
            
        //Then send general intent if we failed again  
        if (!success){  
         success=true;  
          try {  
           intent = new Intent(Intent.ACTION_SEND);   
           intent.putExtra(Intent.EXTRA_TEXT, message);   
           //intent.setType("application/twitter");   
           startActivity(Intent.createChooser(intent, null));  
          } catch (ActivityNotFoundException e) {  
           success=false;  
          }  
        }  
       // return indicating if we were successful in bringing up an intent  
       // of some description  
       return success;  
       }  
    
}
