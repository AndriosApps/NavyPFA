package com.andrios.prt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.andrios.prt.VideoStream.VideoListActivity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends Activity implements
		Serializable, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3088858143996312467L;
	protected static final int PROFILEVIEW = 1;
	Button profileBTN, logBTN, calcBTN, aboutBTN, instructionBTN, videoBTN;
	boolean premium;
	AndriosData mData;
	Profile profile;

	/*
	 * LifeCycle
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainactivity);

		AppRater.app_launched(this);
		setConnections();
		setOnClickListeners();
		readData();
		testProfile();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
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
		profileBTN = (Button) findViewById(R.id.mainActivityProfileBTN);
		logBTN = (Button) findViewById(R.id.mainActivityLogBTN);
		calcBTN = (Button) findViewById(R.id.mainActivityCalculatorsBTN);
		aboutBTN = (Button) findViewById(R.id.mainActivityAboutBTN);
		instructionBTN = (Button) findViewById(R.id.mainActivityInstructionsBTN);
		videoBTN = (Button) findViewById(R.id.mainActivityVideoBTN);
		mData = new AndriosData();
	}

	private void setOnClickListeners() {
		profileBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						ProfileActivity.class);
				intent.putExtra("profile", profile);
				startActivityForResult(intent, PROFILEVIEW);
			}

		});
		logBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {


				/*
				if (premium) {
					Intent intent = new Intent(v.getContext(),
							LogActivity.class);
					mData.setAge(profile.getAge());
					mData.setGender(profile.isMale());
					intent.putExtra("data", mData);
					startActivity(intent);
				} else {

					setAlertDialog();
				}*/

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
		aboutBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), BCA_Activity.class);

				startActivity(intent);

			}

		});
		videoBTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						VideoListActivity.class);
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

}
