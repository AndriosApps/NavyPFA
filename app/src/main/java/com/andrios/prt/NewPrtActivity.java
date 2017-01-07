package com.andrios.prt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.andrios.prt.Classes.Profile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class NewPrtActivity extends Activity implements Observer{


    private static final String TAG = "NewPrtActivity";
    private CustomSeekBar pushupSeekBar;

    private ImageView genderImageView;

    private TextView pushupScoreLBL;
    private TextView pushupTotalLBL;

    private CustomSeekBar curlupSeekBar;

    private TextView curlupScoreLBL;
    private TextView curlupTotalLBL;

    private static final int MIN_PUSHUP = 0;
    private static final int MAX_PUSHUP = 100;

    private static final int MIN_CURLUP = 0;
    private static final int MAX_CURLUP = 110;

    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem progressItem;

    private int pushups = 0;
    private int curlups = 0;
    private int runtime = 0;

    private AndriosData mData;
    private float remainderSpan;
    private boolean pushupchanged;
    private boolean runchanged;
    private boolean curlupchanged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_prt);

        getExtras();

        genderImageView = (ImageView) findViewById(R.id.prt_gender_image_view);

        pushupScoreLBL = (TextView) findViewById(R.id.pushup_score_text_view);
        pushupTotalLBL = (TextView) findViewById(R.id.pushup_total_text_view);

        pushupSeekBar = (CustomSeekBar) findViewById(R.id.pushup_seek_bar);
        pushupSeekBar.setMax(MAX_PUSHUP - MIN_PUSHUP);
        pushupSeekBar.setProgress(54-MIN_PUSHUP);
        pushupSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                pushups = MIN_PUSHUP + (arg1);
                Log.d(TAG, "onProgressChanged: Circumference: " + pushups);
                pushupTotalLBL.setText(pushups + "");
                pushupchanged = true;
                initPushupBar();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        curlupScoreLBL = (TextView) findViewById(R.id.curlup_score_text_view);
        curlupTotalLBL = (TextView) findViewById(R.id.curlup_total_text_view);

        curlupSeekBar = (CustomSeekBar) findViewById(R.id.curlup_seek_bar);
        curlupSeekBar.setMax(MAX_CURLUP - MIN_CURLUP);
        curlupSeekBar.setProgress(54-MIN_CURLUP);
        curlupSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                curlups = MIN_CURLUP + (arg1);
                Log.d(TAG, "onProgressChanged: Circumference: " + curlups);
                curlupTotalLBL.setText(curlups + "");
                curlupchanged = true;
                initCurlupBar();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        initPushupBar();
        initCurlupBar();


    }



    private void initPushupBar() {
        int totalSpan = (MAX_PUSHUP - MIN_PUSHUP);

        int[] pushupRanges = mData.getScoreArrays().get(0);
        progressItemList = new ArrayList<ProgressItem>();

        float failureSpan = (float) pushupRanges[0];//TODO
        float probationarySpan = (pushupRanges[1] - failureSpan);//TODO
        float satisfactorySpan = (pushupRanges[2] - failureSpan - probationarySpan);//TODO
        float goodSpan = pushupRanges[6] - failureSpan - probationarySpan - satisfactorySpan;//TODO
        float excellentSpan = pushupRanges[9] - failureSpan - probationarySpan - satisfactorySpan - goodSpan;//TODO

        //Failure
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (failureSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((failureSpan / totalSpan) * 100);
        progressItem.color = R.color.red;
        progressItemList.add(progressItem);

        //Probationary
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (probationarySpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((probationarySpan / totalSpan) * 100);
        progressItem.color = R.color.redorange;
        progressItemList.add(progressItem);

        //Satisfactory
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (satisfactorySpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((satisfactorySpan / totalSpan) * 100);
        progressItem.color = R.color.orange;
        progressItemList.add(progressItem);

        //Good
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (goodSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((goodSpan / totalSpan) * 100);
        progressItem.color = R.color.yellow;
        progressItemList.add(progressItem);

        //Excellent
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (excellentSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((excellentSpan / totalSpan) * 100);
        progressItem.color = R.color.blue;
        progressItemList.add(progressItem);

        //Outstanding
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.green;
        progressItemList.add(progressItem);


        pushupSeekBar.initData(progressItemList);
        pushupSeekBar.invalidate();


    }

    private void initCurlupBar() {
        Log.d(TAG, "initCurlupBar: ");
        int totalSpan = (MAX_CURLUP - MIN_CURLUP);

        int[] curlupRanges = mData.getScoreArrays().get(1);

        //int[] situpMale50 = {29, 30, 32, 37, 44, 63, 71, 76, 77, 78, 84, 85};
        progressItemList = new ArrayList<ProgressItem>();

        float failureSpan = (float) curlupRanges[0];//TODO
        float probationarySpan = (curlupRanges[1] - failureSpan);//TODO
        float satisfactorySpan = (curlupRanges[2] - failureSpan - probationarySpan);//TODO
        float goodSpan = curlupRanges[6] - failureSpan - probationarySpan - satisfactorySpan;//TODO
        float excellentSpan = curlupRanges[9] - failureSpan - probationarySpan - satisfactorySpan - goodSpan;//TODO

        //Failure
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((failureSpan / totalSpan) * 100);
        progressItem.color = R.color.red;
        progressItemList.add(progressItem);

        //Probationary
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((probationarySpan / totalSpan) * 100);
        progressItem.color = R.color.redorange;
        progressItemList.add(progressItem);

        //Satisfactory
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((satisfactorySpan / totalSpan) * 100);
        progressItem.color = R.color.orange;
        progressItemList.add(progressItem);

        //Good
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((goodSpan / totalSpan) * 100);
        progressItem.color = R.color.yellow;
        progressItemList.add(progressItem);

        //Excellent
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((excellentSpan / totalSpan) * 100);
        progressItem.color = R.color.blue;
        progressItemList.add(progressItem);

        //Outstanding
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.green;
        progressItemList.add(progressItem);


        curlupSeekBar.initData(progressItemList);
        curlupSeekBar.invalidate();

        Log.d(TAG, "initCurlupBar: " + progressItemList.size());

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


    private void calculateScore() {
        int age = mData.getAge();
        if (mData.isMale) {
            if (mData.isAltitude) {
                if (mData.getCardio().equals("500m Swim")) {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.altSwim500Male17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.altSwim500Male20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.altSwim500Male25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.altSwim500Male30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.altSwim500Male35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.altSwim500Male40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.altSwim500Male45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.altSwim500Male50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.altSwim500Male55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.altSwim500Male60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.altSwim500Male65);
                    }
                } else if (mData.getCardio().equals("450m Swim")) {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.altSwim450Male17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.altSwim450Male20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.altSwim450Male25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.altSwim450Male30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.altSwim450Male35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.altSwim450Male40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.altSwim450Male45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.altSwim450Male50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.altSwim450Male55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.altSwim450Male60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.altSwim450Male65);
                    }
                } else {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.altRunMale17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.altRunMale20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.altRunMale25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.altRunMale30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.altRunMale35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.altRunMale40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.altRunMale45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.altRunMale50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.altRunMale55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.altRunMale60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.altRunMale65);
                    }
                }
            } else {
                if (mData.getCardio().equals("500m Swim")) {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.swim500Male17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.swim500Male20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.swim500Male25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.swim500Male30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.swim500Male35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.swim500Male40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.swim500Male45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.swim500Male50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.swim500Male55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.swim500Male60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.swim500Male65);
                    }
                } else if (mData.getCardio().equals("450m swim")) {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.swim450Male17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.swim450Male20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.swim450Male25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.swim450Male30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.swim450Male35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.swim450Male40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.swim450Male45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.swim450Male50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.swim450Male55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.swim450Male60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.swim450Male65);
                    }
                } else {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.runMale17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.runMale20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.runMale25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.runMale30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.runMale35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.runMale40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.runMale45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.runMale50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.runMale55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.runMale60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.runMale65);
                    }
                }
            }


        } else {
            if (mData.isAltitude) {
                if (mData.getCardio().equals("500m Swim")) {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.altSwim500Female17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.altSwim500Female20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.altSwim500Female25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.altSwim500Female30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.altSwim500Female35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.altSwim500Female40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.altSwim500Female45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.altSwim500Female50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.altSwim500Female55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.altSwim500Female60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.altSwim500Female65);
                    }
                } else if (mData.getCardio().equals("450m Swim")) {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.altSwim450Female17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.altSwim450Female20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.altSwim450Female25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.altSwim450Female30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.altSwim450Female35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.altSwim450Female40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.altSwim450Female45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.altSwim450Female50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.altSwim450Female55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.altSwim450Female60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.altSwim450Female65);
                    }
                } else {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.altRunFemale17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.altRunFemale20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.altRunFemale25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.altRunFemale30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.altRunFemale35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.altRunFemale40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.altRunFemale45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.altRunFemale50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.altRunFemale55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.altRunFemale60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.altRunFemale65);
                    }
                }
            } else {
                if (mData.getCardio().equals("500m Swim")) {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.swim500Female17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.swim500Female20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.swim500Female25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.swim500Female30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.swim500Female35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.swim500Female40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.swim500Female45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.swim500Female50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.swim500Female55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.swim500Female60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.swim500Female65);
                    }
                } else if (mData.getCardio().equals("450m swim")) {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.swim450Female17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.swim450Female20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.swim450Female25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.swim450Female30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.swim450Female35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.swim450Female40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.swim450Female45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.swim450Female50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.swim450Female55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.swim450Female60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.swim450Female65);
                    }
                } else {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.runFemale17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.runFemale20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.runFemale25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.runFemale30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.runFemale35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.runFemale40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.runFemale45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.runFemale50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.runFemale55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.runFemale60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.runFemale65);
                    }
                }
            }

        }


    }


    private void calculateMale(int[] pushupMale, int[] situpMale, int[] runMale) {
        int totalScore = 0;
        int pushupScore = 0;
        int situpScore = 0;
        int cardioScore = 0;
        boolean pushupsFailed = true;
        boolean situpsFailed = true;
        boolean cardioFailed = true;

        if (pushupchanged) {
            for (int i = 11; i >= 0; i--) {
                if (pushups >= pushupMale[i]) {
                    totalScore += mData.Scores[i];
                    pushupScore = mData.Scores[i];
                    pushupScoreLBL.setText(getCategory(pushupScore));
                    pushupsFailed = false;
                    break;
                }
            }
            if (pushupsFailed) {
                pushupScoreLBL.setText(R.string.score_failure);
            }
        }

        if (curlupchanged) {
            for (int i = 11; i >= 0; i--) {
                if (curlups >= situpMale[i]) {
                    totalScore += mData.Scores[i];
                    situpScore = mData.Scores[i];
                    //TODO situpScoreLBL.setText(getCategory(situpScore));
                    situpsFailed = false;
                    break;
                }
            }
            if (situpsFailed) {
                //TODO situpScoreLBL.setText(R.string.score_failure);
            }
        }

        if (runchanged) {
            for (int i = 11; i >= 0; i--) {
                if (runtime <= runMale[i]) {
                    totalScore += mData.Scores[i];
                    cardioScore = mData.Scores[i];
                    //TODO runScoreLBL.setText(getCategory(cardioScore));
                    cardioFailed = false;
                    break;
                }
            }
            if (cardioFailed) {
                //TODO runScoreLBL.setText(R.string.score_failure);
            }
        }


        if (!pushupsFailed && !situpsFailed && !cardioFailed && changed()) {

            if ((totalScore / 3) < 45) {
                //TODO scoreLBL.setBackgroundColor(Color.RED);
                //TODO scoreLBL.setTextColor(Color.BLACK);
                //TODO scoreLBL.getBackground().setAlpha(100);
            } else {
                //TODO scoreLBL.setBackgroundColor(Color.GREEN);
                //TODO scoreLBL.setTextColor(Color.BLACK);
                //TODO scoreLBL.getBackground().setAlpha(100);
            }

            //TODO scoreLBL.setText(getCategory(totalScore / 3));

        } else if (changed()) {
            //TODO scoreLBL.setText(R.string.score_failure);
            //TODO scoreLBL.setBackgroundColor(Color.RED);
            //TODO scoreLBL.setTextColor(Color.BLACK);
            //TODO  scoreLBL.getBackground().setAlpha(100);
        }


    }


    private void calculateFemale(int[] pushupFemale, int[] situpFemale, int[] runFemale) {
        int totalScore = 0;
        int pushupScore = 0;
        int situpScore = 0;
        int runScore = 0;
        boolean fail0 = true;//pushups
        boolean fail1 = true;//situps
        boolean fail2 = true;//run

        if (pushupchanged) {


            for (int i = 11; i >= 0; i--) {
                if (pushups >= pushupFemale[i]) {
                    totalScore += mData.Scores[i];
                    pushupScore = mData.Scores[i];
                    pushupScoreLBL.setText(getCategory(pushupScore));
                    fail0 = false;
                    break;
                }
            }
            if (fail0) {
                pushupScoreLBL.setText(getString(R.string.score_failure));
            }
        }

        if (curlupchanged) {
            for (int i = 11; i >= 0; i--) {
                if (curlups >= situpFemale[i]) {
                    totalScore += mData.Scores[i];
                    situpScore = mData.Scores[i];
                    //TODO situpScoreLBL.setText(getCategory(situpScore));
                    fail1 = false;
                    break;
                }
            }
            if (fail1) {
               //TODO situpScoreLBL.setText(R.string.score_failure);
            }
        }

        if (runchanged) {
            for (int i = 11; i >= 0; i--) {
                if (runtime <= runFemale[i]) {
                    totalScore += mData.Scores[i];
                    runScore = mData.Scores[i];
                    //TODO runScoreLBL.setText(getCategory(runScore));
                    fail2 = false;
                    break;
                }
            }
            if (fail2) {
                //TODO runScoreLBL.setText(R.string.score_failure);
            }
        }

        if (!fail0 && !fail1 && !fail2 && changed()) {

            if ((totalScore / 3) < 45) {
                //TODO scoreLBL.setBackgroundColor(Color.RED);
                //TODO scoreLBL.setTextColor(Color.BLACK);
                //TODO scoreLBL.getBackground().setAlpha(100);
            } else {
                //TODO scoreLBL.setBackgroundColor(Color.GREEN);
                //TODO  scoreLBL.setTextColor(Color.BLACK);
                //TODO scoreLBL.getBackground().setAlpha(100);
            }
            //TODO scoreLBL.setText(getCategory(totalScore / 3));

        } else if (changed()) {
            //TODO scoreLBL.setText(R.string.score_failure);
            //TODO scoreLBL.setBackgroundColor(Color.RED);
            //TODO scoreLBL.setTextColor(Color.BLACK);
            //TODO scoreLBL.getBackground().setAlpha(100);
        }
    }



    public String getCategory(int score) {
        String scoreString = getString(R.string.score_failure);

        if ((score) < 45) {
            scoreString = getString(R.string.score_failure);
        } else if ((score) < 50) {
            scoreString = getString(R.string.score_probationary);
        } else if ((score) < 55) {
            scoreString = getString(R.string.score_sat_med);
        } else if ((score) < 60) {
            scoreString = getString(R.string.score_sat_high);
        } else if ((score) < 65) {
            scoreString = getString(R.string.score_good_low);
        } else if ((score) < 70) {
            scoreString = getString(R.string.score_good_med);
        } else if ((score) < 75) {
            scoreString = getString(R.string.score_good_high);
        } else if ((score) < 80) {
            scoreString = getString(R.string.score_excellent_low);
        } else if ((score) < 85) {
            scoreString = getString(R.string.score_excellent_med);
        } else if ((score) < 90) {
            scoreString = getString(R.string.score_excellent_high);
        } else if ((score) < 95) {
            scoreString = getString(R.string.score_outstanding_low);
        } else if ((score) < 100) {
            scoreString = getString(R.string.score_outstanding_med);
        } else if ((score) >= 100) {
            scoreString = getString(R.string.score_outstanding_high);
        }


        return scoreString;

    }

    private boolean changed() {
        boolean changed = false;
        //TODO is this supposed to only return if everything changed?
        if (runchanged && curlupchanged && pushupchanged) {
            changed = true;
        }
        return changed;
    }
}
