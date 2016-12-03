package com.andrios.prt;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

public class InstructionsActivity extends Activity {

	private static String OPNAVINST6110J = "6110.1J-Physical-Readiness-program.pdf";
	private static String OPNAVINST6110JURL = "http://navy-fitness.com/wp-content/uploads/2011/07/6110.1J-Physical-Readiness-program.pdf";
	
	LinearLayout OPNAVINST_6110j;
	LinearLayout Guide_1;
	LinearLayout Guide_2;
	LinearLayout Guide_3;
	LinearLayout Guide_4;
	LinearLayout Guide_5;
	LinearLayout Guide_6;
	LinearLayout Guide_7;
	LinearLayout Guide_8;
	LinearLayout Guide_9;
	LinearLayout Guide_10;
	LinearLayout Guide_11;
	LinearLayout Guide_12;
	LinearLayout Guide_13;
	LinearLayout Guide_14;
	LinearLayout Forms;
	LinearLayout NAVADMIN_072_06;
	LinearLayout NAVADMIN_041_06;
	
	
	
	
	
	
	boolean isPremium;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.instructionsactivity);
        
        getExtras();
        setConnections();
        setOnClickListeners();
    }
    

	private void getExtras() {
		Intent intent = this.getIntent();
		
		isPremium = intent.getBooleanExtra("premium", false);	
		
	}


	private void setConnections() {
		
		OPNAVINST_6110j = (LinearLayout) findViewById(R.id.OPNAVINST_6110j);

		Guide_1 = (LinearLayout) findViewById(R.id.linear_guide_1);
		Guide_2 = (LinearLayout) findViewById(R.id.linear_guide_2);
		Guide_3 = (LinearLayout) findViewById(R.id.linear_guide_3);
		Guide_4 = (LinearLayout) findViewById(R.id.linear_guide_4);
		Guide_5 = (LinearLayout) findViewById(R.id.linear_guide_5);
		Guide_6 = (LinearLayout) findViewById(R.id.linear_guide_6);
		Guide_7 = (LinearLayout) findViewById(R.id.linear_guide_7);
		Guide_8 = (LinearLayout) findViewById(R.id.linear_guide_8);
		Guide_9 = (LinearLayout) findViewById(R.id.linear_guide_9);
		Guide_10 = (LinearLayout) findViewById(R.id.linear_guide_10);
		Guide_11 = (LinearLayout) findViewById(R.id.linear_guide_11);
		Guide_12 = (LinearLayout) findViewById(R.id.linear_guide_12);
		Guide_13 = (LinearLayout) findViewById(R.id.linear_guide_13);
		Guide_14 = (LinearLayout) findViewById(R.id.linear_guide_14);
		Forms = (LinearLayout) findViewById(R.id.linear_forms);
		
		
		if(!isPremium){

		}else{
			LinearLayout adLL = (LinearLayout) findViewById(R.id.instructionActivityAdLL);
			adLL.setVisibility(View.GONE);
		}
		
		
	}

	private void setOnClickListeners() {
		
		
		Guide_1.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%201-%20CFLAdministrative%20Duties%20and%20Responsibilities%202016.pdf"));
				startActivity(browserIntent);
			}
			
		}); 
		Guide_2.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%202-%20Inspection%20and%20Command%20Self%20Assessment%20Checklist%202016.pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_3.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%203-PFA%20Checklist%202016.pdf"));
				startActivity(browserIntent);
			}
			
		}); 
		Guide_4.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%204-%20Body%20Composition%20Assessment%20(BCA)%202016.pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_5.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%205-%20Physical%20Readiness%20Test%20%202016.pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_6.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%206-%20PFA%20Medical%20Waiver%202016.pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_7.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%207-%20Adminstrative%20Separation%20(ADSEP)%202016%20(F).pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_8.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%208-%20Managing%20PFA%20Records%20for%20Pregnant%20Service%20Women%202016%20(F).pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_9.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%209-%20Managing%20PFA%20Records%20for%20IA%20GSA%20OSA%20PEP%20and%20Mobilized%20Reservists%202016.pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_10.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2010-%20Alternate%20Cardio%20Options%20Procedures%202016.pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_11.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2011-%20Member%27s%20Responsibilities%202016.pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_12.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2012-%20Glossary%20of%20Physical%20Readiness%20Program%20Related%20Terms%202016.pdf"));
				startActivity(browserIntent);
			}
			
		}); 
		Guide_13.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2013-%20Command%20PT%20and%20FEP%20Guide%202016%20(F).pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Guide_14.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2014-%20Nutrition%20Resource%20Guide%202016.pdf"));
				startActivity(browserIntent);
			}
			
		});  
		Forms.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Pages/Guide13Forms.aspx"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_072_06.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2006/NAV06072.txt"));
				startActivity(browserIntent);
			}
			
		});  
		NAVADMIN_041_06.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2006/NAV06041.txt"));
				startActivity(browserIntent);
			}
			
		});  
		
		
		
		
		OPNAVINST_6110j.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 
				 
				 try{
					 open(OPNAVINST6110J);
				 }catch(Exception e){
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(OPNAVINST6110JURL));
					startActivity(browserIntent);
				 }
				
			}
			
		});
	}

	
	public void onResume(){
		super.onResume();
	}
	
	public void onPause(){
		super.onPause();
	}
	
	private void open(String filename){
		
		String PATH = Environment.getExternalStorageDirectory()
                + "/download/";
		
		File file = new File(PATH + filename);
		if (file.exists()) {
			 System.out.println("file exists");
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");

            try {
            	System.out.println("Start Activity");
                startActivity(intent);
            } 
            catch (ActivityNotFoundException e) {
                Toast.makeText(InstructionsActivity.this, 
                    "No Application Available to View PDF", 
                    Toast.LENGTH_SHORT).show();
            }
		}else{
			float f = 1/0;
		}
	}
}
