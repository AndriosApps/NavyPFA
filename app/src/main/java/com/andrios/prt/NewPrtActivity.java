package com.andrios.prt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.andrios.prt.Classes.Profile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class NewPrtActivity extends Activity implements Observer {


    private static final String TAG = "NewPrtActivity";
    private CustomSeekBar pushupSeekBar;

    private ImageView genderImageView;
    private ImageView cardioImageView;

    private TextView pushupScoreLBL;
    private TextView pushupTotalLBL;

    private CustomSeekBar curlupSeekBar;

    private TextView curlupScoreLBL;
    private TextView curlupTotalLBL;
    private TextView ageTextView;
    private TextView cardioLBL;
    private TextView cardioCaloriesLBL;

    private CustomSeekBar cardioSeekBar;

    private TextView cardioScoreLBL;
    private TextView cardioTotalLBL;

    private static final int MIN_PUSHUP = 0;
    private static final int MAX_PUSHUP = 100;

    private static final int MIN_CURLUP = 0;
    private static final int MAX_CURLUP = 110;

    private static final int MIN_CARDIO = 375;
    private static final int MIN_CARDIO_BIKE = 420;
    private static final int MAX_CARDIO = 1400;

    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem progressItem;

    private int pushups = MIN_PUSHUP;
    private int curlups = MIN_CURLUP;
    private int runtime = MIN_CARDIO;

    private AndriosData mData;
    private float remainderSpan;
    private boolean pushupchanged;
    private boolean cardiochanged;
    private boolean curlupchanged;
    private CardioHelper cardioHelper;
    private int weight;
    private int calories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_prt);

        getExtras();
        cardioHelper = new CardioHelper(this);

        ageTextView = (TextView) findViewById(R.id.prt_age_text_view);
        cardioCaloriesLBL = (TextView) findViewById(R.id.cardio_calories_text_view);
        cardioLBL = (TextView) findViewById(R.id.cardio_label_text_view);
        cardioLBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCardio();
            }
        });

        cardioImageView = (ImageView) findViewById(R.id.pass_cardio_image_view_left);
        cardioImageView.setImageResource(R.drawable.pencil);
        cardioImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCardio();
            }
        });

        genderImageView = (ImageView) findViewById(R.id.prt_gender_image_view);
        genderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.setGender(!mData.getGender());
                updateUI();
            }
        });

        pushupScoreLBL = (TextView) findViewById(R.id.pushup_score_text_view);
        pushupTotalLBL = (TextView) findViewById(R.id.pushup_total_text_view);

        pushupSeekBar = (CustomSeekBar) findViewById(R.id.pushup_seek_bar);
        pushupSeekBar.setMax(MAX_PUSHUP - MIN_PUSHUP);
        pushupSeekBar.setProgress(MIN_PUSHUP);
        pushupSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                pushups = MIN_PUSHUP + (arg1);
                Log.d(TAG, "onProgressChanged: Circumference: " + pushups);
                pushupTotalLBL.setText(pushups + "");
                pushupchanged = true;
                calculateScore();
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
        curlupSeekBar.setProgress(MIN_CURLUP);
        curlupSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                curlups = MIN_CURLUP + (arg1);
                Log.d(TAG, "onProgressChanged: Circumference: " + curlups);
                curlupTotalLBL.setText(curlups + "");
                curlupchanged = true;
                calculateScore();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        cardioScoreLBL = (TextView) findViewById(R.id.cardio_score_text_view);
        cardioTotalLBL = (TextView) findViewById(R.id.cardio_total_text_view);
        cardioTotalLBL.setText(formatTime(runtime));

        cardioSeekBar = (CustomSeekBar) findViewById(R.id.cardio_seek_bar);
        cardioSeekBar.setMax(MAX_CARDIO - MIN_CARDIO);
        cardioSeekBar.setProgress(0);
        cardioSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                if(mData.getCardio().equalsIgnoreCase(getResources().getString(R.string.cardiobike))){
                    runtime = MIN_CARDIO_BIKE + (arg1);
                }else{
                    runtime = MIN_CARDIO + (arg1);
                }

                Log.d(TAG, "onProgressChanged: Circumference: " + curlups);
                cardioTotalLBL.setText(formatTime(runtime));

                cardiochanged = true;
                calculateScore();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        toggleCardio();//set initially to 1.5 Mile Run
        updateUI();

    }

    private void toggleCardio(){
        String[] cardioOptions = cardioHelper.getNextCardioOptions();
        cardioLBL.setText(cardioOptions[0]);
        mData.setCardio(cardioOptions[0]);
        cardiochanged = true;
        String imageChoice = cardioOptions[1];
        switch(imageChoice){
            case "1": cardioImageView.setImageResource(R.drawable.icon_cardio_run);
                cardioCaloriesLBL.setVisibility(View.INVISIBLE);
                break;
            case "2": cardioImageView.setImageResource(R.drawable.icon_cardio_swim);
                cardioCaloriesLBL.setVisibility(View.INVISIBLE);
                break;
            case "3": cardioImageView.setImageResource(R.drawable.icon_cardio_bike);
                cardioCaloriesLBL.setVisibility(View.VISIBLE);
                break;
            case "4": cardioImageView.setImageResource(R.drawable.icon_cardio_elliptical);
                cardioCaloriesLBL.setVisibility(View.VISIBLE);
                break;
        }
        initCardioBar();
        calculateScore();
    }



    private void updateUI() {

        weight = mData.getWeight();

        initPushupBar();
        initCurlupBar();
        initCardioBar();
        pushupTotalLBL.setText(pushups + "");
        curlupTotalLBL.setText(curlups + "");
        cardioTotalLBL.setText(formatTime(runtime));
        ageTextView.setText(mData.getAge() + "Yrs");
        if(mData.isMale){
            genderImageView.setImageResource(R.drawable.icon_male);
        }else{
            genderImageView.setImageResource(R.drawable.icon_female);
        }
        calculateScore();
    }


    private void initPushupBar() {
        int totalSpan = (MAX_PUSHUP - MIN_PUSHUP);

        int[] pushupRanges = mData.getScoreArrays(this).get(0);
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
        progressItem.color = R.color.prt_failure;
        progressItemList.add(progressItem);

        //Probationary
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (probationarySpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((probationarySpan / totalSpan) * 100);
        progressItem.color = R.color.prt_probationary;
        progressItemList.add(progressItem);

        //Satisfactory
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (satisfactorySpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((satisfactorySpan / totalSpan) * 100);
        progressItem.color = R.color.prt_satisfactory;
        progressItemList.add(progressItem);

        //Good
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (goodSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((goodSpan / totalSpan) * 100);
        progressItem.color = R.color.prt_good;
        progressItemList.add(progressItem);

        //Excellent
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (excellentSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((excellentSpan / totalSpan) * 100);
        progressItem.color = R.color.prt_excellent;
        progressItemList.add(progressItem);

        //Outstanding
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.prt_outstanding;
        progressItemList.add(progressItem);


        pushupSeekBar.initData(progressItemList);
        pushupSeekBar.invalidate();


    }

    private void initCurlupBar() {
        Log.d(TAG, "initCurlupBar: ");
        int totalSpan = (MAX_CURLUP - MIN_CURLUP);

        int[] curlupRanges = mData.getScoreArrays(this).get(1);

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
        progressItem.color = R.color.prt_failure;
        progressItemList.add(progressItem);

        //Probationary
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((probationarySpan / totalSpan) * 100);
        progressItem.color = R.color.prt_probationary;
        progressItemList.add(progressItem);

        //Satisfactory
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((satisfactorySpan / totalSpan) * 100);
        progressItem.color = R.color.prt_satisfactory;
        progressItemList.add(progressItem);

        //Good
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((goodSpan / totalSpan) * 100);
        progressItem.color = R.color.prt_good;
        progressItemList.add(progressItem);

        //Excellent
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((excellentSpan / totalSpan) * 100);
        progressItem.color = R.color.prt_excellent;
        progressItemList.add(progressItem);

        //Outstanding
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.prt_outstanding;
        progressItemList.add(progressItem);


        curlupSeekBar.initData(progressItemList);
        curlupSeekBar.invalidate();

        Log.d(TAG, "initCurlupBar: " + progressItemList.size());

    }

    private void initCardioBar() {
        Log.d(TAG, "initCardioBar: ");
        int minCardio;

        if(mData.getCardio().equalsIgnoreCase(getResources().getString(R.string.cardiobike))){
            minCardio = MIN_CARDIO_BIKE;

        }else{
            minCardio = MIN_CARDIO;
        }


        int totalSpan = (MAX_CARDIO - minCardio);
        cardioSeekBar.setMax(MAX_CARDIO - minCardio);
        cardioSeekBar.invalidate();

        int[] cardioRanges = mData.getScoreArrays(this).get(2);

        //int[] situpMale50 = {29, 30, 32, 37, 44, 63, 71, 76, 77, 78, 84, 85};
        progressItemList = new ArrayList<ProgressItem>();

        float outstandingSpan = (float) cardioRanges[9] - minCardio;//TODO
        float excellentSpan = (cardioRanges[6] - minCardio - outstandingSpan);//TODO
        float goodSpan = (cardioRanges[2] - minCardio - outstandingSpan - excellentSpan);//TODO
        float satisfactorySpan = cardioRanges[1] - minCardio - outstandingSpan - excellentSpan - goodSpan;//TODO
        float probationarySpan = cardioRanges[0] - minCardio - outstandingSpan - excellentSpan - goodSpan - satisfactorySpan;//TODO

        //Outstanding
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((outstandingSpan / totalSpan) * 100);
        progressItem.color = R.color.prt_outstanding;
        progressItemList.add(progressItem);

        //Excellent
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((excellentSpan / totalSpan) * 100);
        progressItem.color = R.color.prt_excellent;
        progressItemList.add(progressItem);

        //Good
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((goodSpan / totalSpan) * 100);
        progressItem.color = R.color.prt_good;
        progressItemList.add(progressItem);

        //Satisfactory
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((satisfactorySpan / totalSpan) * 100);
        progressItem.color = R.color.prt_satisfactory;
        progressItemList.add(progressItem);

        //Probationary
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((probationarySpan / totalSpan) * 100);
        progressItem.color = R.color.prt_probationary;
        progressItemList.add(progressItem);

        //Failure
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.prt_failure;
        progressItemList.add(progressItem);


        cardioSeekBar.initData(progressItemList);
        cardioSeekBar.invalidate();

        Log.d(TAG, "initCardioBar: " + progressItemList.size());

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


    private void calculateScore() {
        ArrayList<int[]> scoresList = mData.getScoreArrays(this);
        int[] pushupScores = scoresList.get(0);
        int[] curlupScores = scoresList.get(1);
        int[] cardioScores = scoresList.get(2);
        int totalScore = 0;
        int pushupScore = 0;
        int curlupScore = 0;
        int cardioScore = 0;
        boolean pushupsFailed = true;
        boolean curlupsFailed = true;
        boolean cardioFailed = true;

        if (pushupchanged) {
            for (int i = 11; i >= 0; i--) {
                if (pushups >= pushupScores[i]) {
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
                if (curlups >= curlupScores[i]) {
                    totalScore += mData.Scores[i];
                    curlupScore = mData.Scores[i];
                    curlupScoreLBL.setText(getCategory(curlupScore));
                    curlupsFailed = false;
                    break;
                }
            }
            if (curlupsFailed) {
                curlupScoreLBL.setText(R.string.score_failure);
            }
        }

        if (cardiochanged) {
            for (int i = 11; i >= 0; i--) {
                if (runtime <= cardioScores[i]) {
                    totalScore += mData.Scores[i];
                    cardioScore = mData.Scores[i];
                    cardioScoreLBL.setText(getCategory(cardioScore));
                    cardioFailed = false;
                    break;
                }
            }
            if (cardioFailed) {
                cardioScoreLBL.setText(R.string.score_failure);
            }
        }


        TextView finalScoreTextView = (TextView) findViewById(R.id.prt_total_score_text_view);
        //GradientDrawable backgroundCircleDrawable = (GradientDrawable) finalScoreTextView.getBackground();

        if(pushupsFailed || curlupsFailed || cardioFailed){
            finalScoreTextView.setText(getCategory(0));
            //backgroundCircleDrawable.setColor(getCategoryColor(0));
        }else{
            finalScoreTextView.setText(getCategory(totalScore / 3));
            //backgroundCircleDrawable.setColor(getCategoryColor(totalScore / 3));
        }

        if(cardioHelper.getCurrentCardioOption()[1].equals("3")){
            calcBike();
        }else if(cardioHelper.getCurrentCardioOption()[1].equals("4")){
            newCalcElliptical();
            Log.d(TAG, "calculateScore: Elliptical");
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


    public int getCategoryColor(int score) {
        int scoreColor = R.color.prt_failure;

        if ((score) < 45) {
            scoreColor = R.color.prt_failure;
        } else if ((score) < 50) {
            scoreColor = R.color.prt_probationary;
        } else if ((score) < 55) {
            scoreColor = R.color.prt_satisfactory;
        } else if ((score) < 60) {
            scoreColor = R.color.prt_satisfactory;
        } else if ((score) < 65) {
            scoreColor = R.color.prt_good;
        } else if ((score) < 70) {
            scoreColor = R.color.prt_good;
        } else if ((score) < 75) {
            scoreColor = R.color.prt_good;
        } else if ((score) < 80) {
            scoreColor = R.color.prt_excellent;
        } else if ((score) < 85) {
            scoreColor = R.color.prt_excellent;
        } else if ((score) < 90) {
            scoreColor = R.color.prt_excellent;
        } else if ((score) < 95) {
            scoreColor = R.color.prt_outstanding;
        } else if ((score) < 100) {
            scoreColor = R.color.prt_outstanding;
        } else if ((score) >= 100) {
            scoreColor = R.color.prt_outstanding;
        }


        return ContextCompat.getColor(this, scoreColor);

    }

    private boolean changed() {
        boolean changed = false;
        //TODO is this supposed to only return if everything changed?
        if (cardiochanged && curlupchanged && pushupchanged) {
            changed = true;
        }
        return changed;
    }

    private String formatTime(int totalSeconds) {
        String minutesTXT, secondsTXT;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        if (minutes < 10) {
            minutesTXT = "0" + Integer.toString(minutes);
        } else {
            minutesTXT = Integer.toString(minutes);
        }
        if (seconds < 10) {
            secondsTXT = "0" + Integer.toString(seconds);
        } else {
            secondsTXT = Integer.toString(seconds);
        }
        String formattedTimeString = minutesTXT + ":" + secondsTXT;

        //return formattedTimeString;
        return totalSeconds + " / " + formattedTimeString;
    }

    private void calcBike(){
        float cal = 0;
        float biketime = runtime;
        biketime = biketime/60;
        Log.d(TAG, "calcBike: Biketime: " + biketime);
        if(mData.isMale){

            float x = new Float(4.087);
            float y = new Float(6.296);
            //bikeTime = 6.296 + 4.087 * weight / calories;
            cal = (x * weight) / (biketime - y);

            cal = cal;
            Log.d(TAG, "calcBike: " + cal);
            calories = (int) cal;

        }else{
            float x = new Float(4.087);
            float y = new Float(8.066);
           // bikeTime = 6.296 + 4.087 * weight / calories + 1.77;
            cal = (x * weight)/(biketime-y);
        }



        cardioCaloriesLBL.setText((int) cal + " Calories");
    }

    private int calcRawRunTime(int calories, int weight){
        double myCalories = (double) calories;

        //TODO Add rest of ellipticals here.
        double myWeight = weight * 0.45359237; // convert to kg
        double ER = 2.413 * myWeight;
        double EER = (double) myCalories / 12.0;
        double T = ER / EER;

        int totalSeconds = (int) (T*60);
        return totalSeconds;
    }

    private void newCalcElliptical(){
        float eliptimeMins = 0;
        if(mData.isMale){
            eliptimeMins = new Float(runtime-68);
        }else{
            eliptimeMins = new Float(runtime-135);
        }

        eliptimeMins = eliptimeMins/60;
        float kgWeight = new Float(weight);
        kgWeight = kgWeight * new Float(0.45359237);
        float x = new Float(28.956);
        float cal = x * kgWeight / eliptimeMins;
        String cardioString = cardioHelper.getCurrentCardioOption()[0];
        if(cardioString.equalsIgnoreCase(getResources().getString(R.string.cardioellip95xi))){
            cal -= Float.valueOf(13);
        }else if(cardioString.equalsIgnoreCase(getResources().getString(R.string.cardioellipe916))){
            cal -= new Float(20);
        }else if(cardioString.equalsIgnoreCase(getResources().getString(R.string.cardioellipefx556))){
            cal -= new Float(7);
        }

        cardioCaloriesLBL.setText((int) cal + " Calories");
    }




}
