package com.andrios.prt;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import net.robotmedia.billing.BillingController;
import net.robotmedia.billing.BillingRequest.ResponseCode;
import net.robotmedia.billing.helper.AbstractBillingActivity;
import net.robotmedia.billing.model.Transaction;
import net.robotmedia.billing.model.Transaction.PurchaseState;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AbstractBillingActivity implements
		Serializable, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3088858143996312467L;
	protected static final int PROFILEVIEW = 1;
	Button logBTN, calcBTN, instructionBTN;
	boolean premium;
	AndriosData mData;
	Profile profile;
	private GoogleAnalyticsTracker tracker;

	/*
	 * LifeCycle
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainactivity);

		AppRater.app_launched(this);
		setConnections();
		setOnClickListeners();
		//setTracker();
		restoreTransactions();

		readData();

		updateOwnedItems();
		testProfile();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;
	}

	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.start(this.getString(R.string.ga_api_key),
				getApplicationContext());
	}

	@Override
	public void onResume() {
		super.onResume();
		//tracker.trackPageView("/" + this.getLocalClassName());
	}

	@Override
	public void onPause() {
		super.onPause();
		//tracker.dispatch();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (requestCode == PROFILEVIEW) {
			if (resultCode == RESULT_OK) {
				profile = (Profile) intent.getSerializableExtra("profile");
				// setConnections();
			} else {
				if (profile.getName().equals("Click to Set Name")) {
					Toast.makeText(this, "Profile Inclomplete",
							Toast.LENGTH_SHORT).show();
				}

			}
		}
	}

	/*
	 * Interface
	 */

	private void setConnections() {
		logBTN = (Button) findViewById(R.id.mainActivityLogBTN);
		calcBTN = (Button) findViewById(R.id.mainActivityCalculatorsBTN);
		instructionBTN = (Button) findViewById(R.id.mainActivityInstructionsBTN);
		mData = new AndriosData();
		
		Drawable drawable = getResources().getDrawable(R.drawable.button_calculator);
		drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.25), 
		                         (int)(drawable.getIntrinsicHeight()*0.25));
		ScaleDrawable sd = new ScaleDrawable(drawable, 0, (int) (drawable.getIntrinsicWidth()*0.25), (int) (drawable.getIntrinsicHeight()*0.25));
		
		calcBTN.setCompoundDrawables(sd.getDrawable(), null, null, null);
		
		Drawable drawableLog = getResources().getDrawable(R.drawable.button_log);
		drawableLog.setBounds(0, 0, (int)(drawableLog.getIntrinsicWidth()*0.25), 
		                         (int)(drawableLog.getIntrinsicHeight()*0.25));
		ScaleDrawable sdLog = new ScaleDrawable(drawableLog, 0, (int) (drawableLog.getIntrinsicWidth()*0.25), (int) (drawableLog.getIntrinsicHeight()*0.25));
		
		logBTN.setCompoundDrawables(sdLog.getDrawable(), null, null, null);
		
		
		Drawable drawableInstructions = getResources().getDrawable(R.drawable.button_instructions);
		drawableInstructions.setBounds(0, 0, (int)(drawableInstructions.getIntrinsicWidth()*0.25), 
		                         (int)(drawableInstructions.getIntrinsicHeight()*0.25));
		ScaleDrawable sdInstructions = new ScaleDrawable(drawableInstructions, 0, (int) (drawableInstructions.getIntrinsicWidth()*0.25), (int) (drawableInstructions.getIntrinsicHeight()*0.25));
		
		instructionBTN.setCompoundDrawables(sdInstructions.getDrawable(), null, null, null);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.about:
	        	Intent i = new Intent(this.getBaseContext(), AboutActivity.class);
				startActivity(i);
				
	            return true;
	            
	        case R.id.profile:
	        	Intent intent = new Intent(this.getBaseContext(),
						ProfileActivity.class);
				intent.putExtra("profile", profile);
				startActivityForResult(intent, PROFILEVIEW);
				
	            return true;
	            
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void setOnClickListeners() {
	
		logBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (premium) {
					Intent intent = new Intent(v.getContext(),
							LogActivity.class);
					mData.setAge(profile.getAge());
					mData.setGender(profile.isMale());
					intent.putExtra("data", mData);
					startActivity(intent);
				} else {

					setAlertDialog();
				}

			}

		});
		
		
		
		calcBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						CalculatorTabsActivity.class);
				intent.putExtra("premium", premium);
				mData.setAge(profile.getAge());
				mData.setGender(profile.isMale());
				intent.putExtra("data", mData);
				startActivity(intent);

			}
 
			
		});
		
		instructionBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						InstructionsActivity.class);
				intent.putExtra("premium", premium);
				startActivity(intent);

			}

		});

	}

	/*
	 * Model Access
	 */

	private void testProfile() {
		if (profile.getName().equals("Click to Set Name")) {
			createProfileDialog();
		}

	}

	public void update(Observable observable, Object data) {

	}

	private void readData() {

		try {
			FileInputStream fis = openFileInput("profile");
			ObjectInputStream ois = new ObjectInputStream(fis);

			profile = (Profile) ois.readObject();
			profile.addObserver(MainActivity.this);
			ois.close();
			fis.close();

		} catch (Exception e) {
			profile = new Profile();

		}
	}

	/*
	 * Dialogs
	 */

	private void createProfileDialog() {
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Please create a profile");
		alert.setMessage("Your profile will allow you to set defaults for the calculators and track upcoming PFAs");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Intent intent = new Intent(MainActivity.this.getBaseContext(),
						ProfileActivity.class);
				intent.putExtra("profile", profile);
				startActivityForResult(intent, PROFILEVIEW);

			}
		});
		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		alert.show();
	}

	private void setAlertDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = LayoutInflater.from(this);
		final View layout = inflater.inflate(
				R.layout.alert_dialog_premium_features, null);

		builder.setView(layout)
				.setTitle("Premium Features")
				.setPositiveButton("Buy Now!",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								tracker.trackEvent("Purchase", // Category
										"Alert Dialog", // Action
										"Yes", // Label
										0); // Value
								requestPurchase("premium_features");
							}
						})
				.setNegativeButton("No Thanks",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								tracker.trackEvent("Purchase", // Category
										"Alert Dialog", // Action
										"No", // Label
										0); // Value
							}

						});
		AlertDialog ad = builder.create();
		ad.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.robotmedia.billing.BillingController.IConfiguration#getObfuscationSalt
	 * ()
	 */

	public byte[] getObfuscationSalt() {
		return new byte[] { 41, -90, -116, -41, 66, -53, 122, -110, -127, -96,
				-88, 77, 127, 115, 1, 73, 57, 110, 48, -116 };
	}

	public String getPublicKey() {

		return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsWpPBgnPmwEHkkfcy11L5/gdEzeVGy/xxFA719PrQeX8a1VlJAQeWjvxVd5xgpcmcUXnt4t+0IWAfL1plRIH6OUCYWbZlhJv/QdqmC5v7WAH8U925Yl6o0GNxdgZDfVyBDw/wboSbQ1dxbEMXZ0Jqpfz2DApszhxC9vj9xLIo6hBm1dGYWkTVPn7LLiRfnCkxJgRNxtMrEP8FwnebV3Lvk8520/0VQj8wVU3QZaGPEQ3yO+z664D3Zlx9p0hMk54xeoJo86OwvXw1lKA8vlXBCc1eHhHa6QhAUn0SNVq9e1sF3rsxKJWbT1tUvK9qHHinrMjXF5095jx+evH58pn7wIDAQAB";
	}

	@Override
	public void onBillingChecked(boolean supported) {


	}

	@Override
	public void onPurchaseStateChanged(String itemId, PurchaseState state) {
		if (itemId.equals("premium_features")
				&& PurchaseState.PURCHASED.equals(state)) {
			premium = true;
		} else if (itemId.equals("premium_features")
				&& PurchaseState.CANCELLED.equals(state)) {
		}
		if (itemId.equals("premium_features")
				&& PurchaseState.REFUNDED.equals(state)) {
		}
	}

	@Override
	public void onRequestPurchaseResponse(String itemId, ResponseCode response) {

	}

	private void updateOwnedItems() {

		List<Transaction> transactions = BillingController
				.getTransactions(this);
		final ArrayList<String> ownedItems = new ArrayList<String>();
		premium = false;
		for (Transaction t : transactions) {
			if (t.purchaseState == PurchaseState.PURCHASED) {
				if (t.productId.equals("premium_features")) {
					premium = true;
					break;
				}

			}
		}

	}

}
