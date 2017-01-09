package com.andrios.prt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrios.prt.Classes.Profile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;

public class NewProfileActivity extends Activity implements Observer{

    private String TAG = "ProfileActivity";
    AndriosData mData;
    ImageView genderImageView;
    ImageView profileImageView;
    TextView ageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        getExtras();
        setConnections();
    }

    private void setConnections() {
        genderImageView = (ImageView) findViewById(R.id.profile_gender_imageview);
        genderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.setGender(!mData.getGender());
                updateUI();
            }
        });
        profileImageView = (ImageView) findViewById(R.id.profile_image_view);
        ageTextView = (TextView) findViewById(R.id.profile_age_textview);

        ageTextView.setText(mData.getAge() + "");
        if(mData.isMale){
            genderImageView.setImageResource(R.drawable.icon_male);
        }else{
            genderImageView.setImageResource(R.drawable.icon_female);
        }

        GradientDrawable profileBackgroundCircle = (GradientDrawable) profileImageView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = ContextCompat.getColor(this, R.color.red);
        // Set the color on the magnitude circle
        profileBackgroundCircle.setColor(magnitudeColor);

        //TODO add library to handle circular imagecropping.
        Log.d(TAG, "setConnections: " + mData.getProfile().getAge());
        profileImageView.setImageBitmap(mData.getProfile().getProfileBitmap(this));

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
}
