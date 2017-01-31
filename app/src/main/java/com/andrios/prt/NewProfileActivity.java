package com.andrios.prt;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrios.prt.Classes.Profile;
import com.andrios.prt.Classes.RoundedImageView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class NewProfileActivity extends Activity implements Observer{


    static final int DATE_DIALOG_ID = 1;
    private String TAG = "ProfileActivity";
    AndriosData mData;
    ImageView genderImageView;
    RoundedImageView profileImageView;
    TextView ageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        getExtras();
        setConnections();
    }

    private void setConnections() {
        genderImageView = (ImageView) findViewById(R.id.profile_gender_image_view);
        genderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.setGender(!mData.getGender());
                updateUI();
            }
        });
        //profileImageView = (RoundedImageView) findViewById(R.id.profile_image_view);
        ageTextView = (TextView) findViewById(R.id.profile_age_text_view);
        ageTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        ageTextView.setText(mData.getAge() + "");
        if(mData.isMale){
            genderImageView.setImageResource(R.drawable.icon_male);
        }else{
            genderImageView.setImageResource(R.drawable.icon_female);
        }

        //GradientDrawable backgroundCircle = (GradientDrawable) genderImageView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        //int magnitudeColor = ContextCompat.getColor(this, R.color.andrios_grey);
        // Set the color on the magnitude circle
        //backgroundCircle.setColor(magnitudeColor);

        //backgroundCircle = (GradientDrawable) profileImageView.getBackground();
        //backgroundCircle.setColor(magnitudeColor);

        //backgroundCircle = (GradientDrawable) ageTextView.getBackground();
        //backgroundCircle.setColor(magnitudeColor);

        //TODO add library to handle circular imagecropping.
        Log.d(TAG, "setConnections: " + mData.getProfile().getAge());
        //profileImageView.setImageBitmap(mData.getProfile().getProfileBitmap(this));
    }

    private void getExtras() {
        Log.d(TAG, "getExtras: ");
        Intent intent = this.getIntent();
        //isLog = intent.getBooleanExtra("log", false);
        //isPremium = intent.getBooleanExtra("premium", false);
        mData = (AndriosData) intent.getSerializableExtra("data");
        if (mData == null) {
            readData();
        }
        mData.addObserver(this);
    }

    private void readData() {
        Log.d(TAG, "readData: ");

        try {
            FileInputStream fis = openFileInput("profile");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Profile profile = (Profile) ois.readObject();
            ois.close();
            fis.close();

            mData = new AndriosData();
            mData.setAge(profile.getAge());
            mData.setGender(profile.isMale());

        } catch (Exception e) {
            Profile profile = new Profile();
            mData = new AndriosData();
        }
    }

    public void update(Observable observable, Object data) {
        Log.d(TAG, "update: ");
        updateUI();
        //TODO updateUI();
    }

    private void updateUI() {
        if(mData.isMale){
            genderImageView.setImageResource(R.drawable.icon_male);
        }else{
            genderImageView.setImageResource(R.drawable.icon_female);
        }
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case DATE_DIALOG_ID:
                int mYear = mData.getProfile().getDate().get(Calendar.YEAR);
                int mMonth = mData.getProfile().getDate().get(Calendar.MONTH);
                int mDay = mData.getProfile().getDate().get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {

            case DATE_DIALOG_ID:
                //getCalendar();
                int mYear = mData.getProfile().getDate().get(Calendar.YEAR);
                int mMonth = mData.getProfile().getDate().get(Calendar.MONTH);
                int mDay = mData.getProfile().getDate().get(Calendar.DAY_OF_MONTH);
                System.out.println("On Prepare " + mMonth+" "+mYear+" "+mDay);//TODO
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    mData.getProfile().getDate().set(Calendar.YEAR, year);
                    mData.getProfile().getDate().set(Calendar.MONTH, monthOfYear);
                    mData.getProfile().getDate().set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    ageTextView.setText(mData.getAge() + "Yrs");
                }
            };
}