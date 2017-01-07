package com.andrios.prt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.andrios.prt.Classes.Profile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class NewPrtActivity extends Activity implements Observer{


    private static final String TAG = "NewPrtActivity";
    private CustomSeekBar circumferenceSeekBar;

    private static final int MIN_PUSHUP = 20;
    private static final int MAX_PUSHUP = 55;

    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem progressItem;

    private int pushups = 20;

    private AndriosData mData;
    private float remainderSpan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_prt);

        circumferenceSeekBar = (CustomSeekBar) findViewById(R.id.circumferance_seek_bar);
        circumferenceSeekBar.setMax(MAX_PUSHUP - MIN_PUSHUP);
        circumferenceSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                pushups = MIN_PUSHUP + (arg1);
                Log.d(TAG, "onProgressChanged: Circumference: " + pushups);
                //circumferenceTextView.setText(pushups + "\"");
                //updateUI();
                initPushupBar();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }


    private void initPushupBar() {
        int totalSpan = (MAX_PUSHUP - MIN_PUSHUP) * 2;
        progressItemList = new ArrayList<ProgressItem>();

        float passSpan = (float) (mData.getCircumference() - MIN_PUSHUP) * 2;

        //RED SPAN
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (passSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((passSpan / totalSpan) * 100);
        progressItem.color = R.color.andrios_grey;
        progressItemList.add(progressItem);

        //GREY SPAN
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.red;
        progressItemList.add(progressItem);


        circumferenceSeekBar.initData(progressItemList);
        circumferenceSeekBar.invalidate();


    }

    private void getExtras() {
        Log.d(TAG, "getExtras: ");
        Intent intent = this.getIntent();
        //isLog = intent.getBooleanExtra("log", false);
        //isPremium = intent.getBooleanExtra("premium", false);
        mData = (AndriosData) intent.getSerializableExtra("data");
        if(mData == null){
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
        //TODO updateUI();
    }
}
