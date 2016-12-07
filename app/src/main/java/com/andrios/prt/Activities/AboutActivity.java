package com.andrios.prt.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.andrios.prt.R;

import java.util.List;

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
    

	
	public void onResume(){
		super.onResume();
	}
	
	public void onPause(){
		super.onPause();
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
			     
			    emailIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, "Navy PRT Android App");
			     
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
				try{
					String uri = "fb://page/224807700868604";
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					startActivity(intent);
				}catch(Exception e){
					Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/pages/AndriOS/224807700868604"));
					startActivity(browserIntent);
				}
				
			}
			
		});
		
		twitterBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {

				String message = "@AndriOS_Apps";

				
				try{
					Intent intent = findTwitterClient();
					intent.putExtra(Intent.EXTRA_TEXT, message);
					startActivity(Intent.createChooser(intent, null)); 
				}catch(Exception e){
					Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://twitter.com/#!/AndriOS_Apps"));
					startActivity(browserIntent);
				}
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
