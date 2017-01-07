package com.andrios.prt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.andrios.prt.Classes.Profile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class NewBcaActivity extends Activity implements Observer {

    private static final String TAG = "NewBcaActivity";
    private static final int MIN_HEIGHT = 51;
    private static final int MAX_HEIGHT = 86;
    private static final int MIN_WEIGHT = 90;
    private static final int MAX_WEIGHT = 300;
    private static final int MIN_CIRCUM = 20;
    private static final int MAX_CIRCUM = 55;
    private static final int MAX_NECK = 25;
    private static final int MIN_NECK = 10;
    private static final int MAX_HIPS = 55;
    private static final int MIN_HIPS = 10;

    private CustomSeekBar heightSeekBar;
    private CustomSeekBar weightSeekBar;
    private CustomSeekBar circumferenceSeekBar;
    private CustomSeekBar neckSeekBar;
    private CustomSeekBar hipsSeekBar;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem progressItem;
    private TextView heightTextView;
    private TextView weightTextView;
    private TextView neckTextView;
    private TextView hipsTextView;

    private TextView circumferenceTextView;
    private TextView bodyFatPercentTextView;
    private ImageView passHeightWeightImageView;
    private ImageView passCircumferenceImageView;
    private ImageView passBodyfatImageView;
    private ImageView genderImageView;

    private CardView circumferenceCardView;
    private CardView bodyfatCardView;
    private LinearLayout hipsLayout;

    private boolean passHeightWeight;
    private boolean passCircumference;
    private boolean passBodyfat;

    private AndriosData mData;
    private float remainderSpan;
    private double circumference = 20.0, neck = 15.0, hips = 36.0;


    int height = 69, weight = 100, age = 17;
    private double MAX_BODYFAT_MALE = 26;
    private double MAX_BODYFAT_FEMALE = 36;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bca);

        setConnection();
        updateUI();
    }

    private void setConnection() {
        getExtras();

        heightTextView = (TextView) findViewById(R.id.height_text_view);
        weightTextView = (TextView) findViewById(R.id.weight_text_view);
        neckTextView = (TextView) findViewById(R.id.neck_text_view);
        hipsTextView = (TextView) findViewById(R.id.hips_text_view);
        circumferenceTextView = (TextView) findViewById(R.id.circumferance_text_view);
        bodyFatPercentTextView = (TextView) findViewById(R.id.bodyfat_percent_text_view);

        passHeightWeightImageView = (ImageView) findViewById(R.id.pass_height_weight_image_view_right);
        passCircumferenceImageView = (ImageView) findViewById(R.id.pass_circumference_image_view_right);
        passBodyfatImageView = (ImageView) findViewById(R.id.pass_bodyfat_image_view_right);
        genderImageView = (ImageView) findViewById(R.id.gender_image_view);
        genderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.setGender(!mData.getGender());

                updateUI();
            }
        });

        circumferenceCardView = (CardView) findViewById(R.id.circumference_card_view);

        bodyfatCardView = (CardView) findViewById(R.id.bodyfat_card_view);
        hipsLayout = (LinearLayout) findViewById(R.id.hips_layout);

        heightSeekBar = (CustomSeekBar) findViewById(R.id.height_seek_bar);
        heightSeekBar.setMax(MAX_HEIGHT - MIN_HEIGHT);
        heightSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                height = arg1 + MIN_HEIGHT;
                heightTextView.setText(formatInches(height));
                updateUI();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        weightSeekBar = (CustomSeekBar) findViewById(R.id.weight_seek_bar);
        weightSeekBar.setMax(MAX_WEIGHT - MIN_WEIGHT);
        weightSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                weight = arg1 + MIN_WEIGHT;
                Log.d(TAG, "onProgressChanged: " + weight);
                weightTextView.setText(weight + "lbs");

                updateUI();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        circumferenceSeekBar = (CustomSeekBar) findViewById(R.id.circumferance_seek_bar);
        circumferenceSeekBar.setMax((MAX_CIRCUM - MIN_CIRCUM) * 2);
        circumferenceSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                double decimal = arg1 % 2;
                circumference = MIN_CIRCUM + (arg1 / 2) + decimal / 2;
                Log.d(TAG, "onProgressChanged: Circumference: " + circumference);
                circumferenceTextView.setText(circumference + "\"");
                updateUI();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        neckSeekBar = (CustomSeekBar) findViewById(R.id.neck_seek_bar);
        neckSeekBar.setMax((MAX_NECK - MIN_NECK) * 2);
        neckSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                double decimal = arg1 % 2;
                neck = MIN_NECK + (arg1 / 2) + decimal / 2;
                Log.d(TAG, "onProgressChanged: Neck: " + neck);
                neckTextView.setText(neck + "\"");
                calculateBodyfat(circumference, neck, hips, height);
                updateUI();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        hipsSeekBar = (CustomSeekBar) findViewById(R.id.hips_seek_bar);
        hipsSeekBar.setMax((MAX_HIPS - MIN_HIPS) * 2);
        hipsSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                double decimal = arg1 % 2;
                hips = MIN_HIPS + (arg1 / 2) + decimal / 2;
                Log.d(TAG, "onProgressChanged: Hips: " + hips);
                hipsTextView.setText(hips + "\"");
                updateUI();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        heightSeekBar.setProgress(height - MIN_HEIGHT);
        weightSeekBar.setProgress(weight - MIN_WEIGHT);
        circumferenceSeekBar.setProgress((int) (circumference * 2) - MIN_CIRCUM);
        neckSeekBar.setProgress((int) neck - MIN_NECK);
        hipsSeekBar.setProgress((int) hips - MIN_HIPS);
    }

    private void updateUI() {

        if(mData.isMale){
            genderImageView.setImageResource(R.drawable.icon_male);
            initWeightSeekBar(mData.getWeightMale()[height - MIN_HEIGHT]);
        }else{
            genderImageView.setImageResource(R.drawable.icon_female);
            initWeightSeekBar(mData.getWeightFemale()[height - MIN_HEIGHT]);
        }
        calcHeightWeight();
        initHeightSeekBar();

        if (passHeightWeight) {
            passHeightWeightImageView.setImageResource(R.drawable.pass_icon);
            circumferenceCardView.setVisibility(View.GONE);
            bodyfatCardView.setVisibility(View.GONE);
            Log.d(TAG, "calcHeightWeight: in standards");
        } else {
            calculateCircumference();
            initCircumferenceBar();
            passHeightWeightImageView.setImageResource(R.drawable.fail_icon);
            circumferenceCardView.setVisibility(View.VISIBLE);
            Log.d(TAG, "calcHeightWeight: not in standards");

            if (passCircumference) {
                bodyfatCardView.setVisibility(View.GONE);
                passCircumferenceImageView.setImageResource(R.drawable.pass_icon);
            } else {
                passCircumferenceImageView.setImageResource(R.drawable.fail_icon);
                bodyfatCardView.setVisibility(View.VISIBLE);
                initNeckSeekBar();

                if(mData.isMale){
                    hipsLayout.setVisibility(View.GONE);
                }else{
                    initHipsSeekBar();
                    hipsLayout.setVisibility(View.VISIBLE);
                }

                if (isBodyfatPassing()) {
                    passBodyfatImageView.setImageResource(R.drawable.pass_icon);
                } else {
                    passBodyfatImageView.setImageResource(R.drawable.fail_icon);
                }
            }
        }
    }

    private void initHipsSeekBar() {
        int totalSpan = (MAX_HIPS - MIN_HIPS) * 2;

        progressItemList = new ArrayList<ProgressItem>();

        float passHips = (float) calculatePassingHips();
        Log.d(TAG, "initHipsSeekBar: PassHips " + passHips);

        float passSpan = (passHips - MIN_HIPS) * 2;

        //GREY SPAN
        progressItem = new ProgressItem();
        Log.d(TAG, "initHipsSeekBar: pass span percent " + (passSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((passSpan / totalSpan) * 100);
        progressItem.color = R.color.andrios_grey;
        progressItemList.add(progressItem);

        //RED SPAN
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.red;
        progressItemList.add(progressItem);

        hipsSeekBar.initData(progressItemList);
        hipsSeekBar.invalidate();
    }

    private double calculatePassingHips() {
        double passingBodyfat;
        if (mData.isMale) {
            passingBodyfat = MAX_BODYFAT_MALE;
        } else {
            passingBodyfat = MAX_BODYFAT_FEMALE;
        }
        double hipsTest = MAX_HIPS;
        while (passingBodyfat < calculateBodyfat(circumference, neck, hipsTest, height) && hipsTest > MIN_HIPS) {
            //Log.d(TAG, "calculatePassingHips: Hips" + hipsTest + " passing bodyfat: " + passingBodyfat + " < " + calculateBodyfat(circumference, neck, hipsTest, height));
            hipsTest -= .5;
        }
        return hipsTest;
    }


    private void initNeckSeekBar() {
        int totalSpan = (MAX_NECK - MIN_NECK) * 2;

        progressItemList = new ArrayList<ProgressItem>();

        float passNeck = (float) calculatePassingNeck();
        Log.d(TAG, "initNeckSeekBar: PassNeck " + passNeck);

        float passSpan = (passNeck - MIN_NECK) * 2;

        //RED SPAN
        progressItem = new ProgressItem();
        Log.d(TAG, "initNeckSeekBar: pass span percent " + (passSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((passSpan / totalSpan) * 100);
        progressItem.color = R.color.red;
        progressItemList.add(progressItem);

        //GREY SPAN
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.andrios_grey;
        progressItemList.add(progressItem);

        neckSeekBar.initData(progressItemList);
        neckSeekBar.invalidate();
    }



    private void initCircumferenceBar() {
        int totalSpan = (MAX_CIRCUM - MIN_CIRCUM) * 2;
        progressItemList = new ArrayList<ProgressItem>();

        float passSpan = (float) (mData.getCircumference() - MIN_CIRCUM) * 2;

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

    private void initHeightSeekBar() {
        int totalSpan = MAX_HEIGHT - MIN_HEIGHT;

        progressItemList = new ArrayList<ProgressItem>();


        //GREY SPAN
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (totalSpan);
        progressItem.color = R.color.andrios_grey;
        progressItemList.add(progressItem);


        heightSeekBar.initData(progressItemList);
        heightSeekBar.invalidate();


    }

    private void initWeightSeekBar(int failWeight) {
        float totalSpan = MAX_WEIGHT - MIN_WEIGHT;
        progressItemList = new ArrayList<ProgressItem>();


        float passSpan = (failWeight - MIN_WEIGHT);

        Log.d(TAG, "initWeightSeekBar: failWeight: " + failWeight + " pass span: " + passSpan);

        //RED SPAN
        progressItem = new ProgressItem();
        Log.d(TAG, "initWeightSeekBar: pass span percent " + (passSpan / totalSpan) * 100 + "%");
        progressItem.progressItemPercentage = ((passSpan / totalSpan) * 100);
        progressItem.color = R.color.andrios_grey;
        progressItemList.add(progressItem);

        //GREY SPAN
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (remainderSpan / totalSpan) * 100;
        progressItem.color = R.color.red;
        progressItemList.add(progressItem);


        weightSeekBar.initData(progressItemList);
        weightSeekBar.invalidate();

    }

    private boolean isBodyfatPassing() {
        double bodyfat = calculateBodyfat(circumference, neck, hips, height);
        if (mData.isMale && bodyfat < MAX_BODYFAT_MALE) {
            return true;
        } else if (!mData.isMale && bodyfat < MAX_BODYFAT_FEMALE) {
            return true;
        } else {
            return false;
        }
    }

    private double calculatePassingNeck() {
        double passingBodyfat;
        if (mData.isMale) {
            passingBodyfat = 26.0;
        } else {
            passingBodyfat = 36.0;
        }
        boolean passing = false;
        double neckTest = MIN_NECK;
        while (passingBodyfat < calculateBodyfat(circumference, neckTest, hips, height)) {
            Log.d(TAG, "calculatePassingNeck: " + neckTest);
            neckTest += .5;
        }
        return neckTest;

    }

    private double calculateBodyfat(double circumference, double neck, double hips, int height) {
        double percentFat = 0;
        if (mData.isMale) {

            //Equation derived from DoD Instruction 1308.3 Page 13
            percentFat = 86.010 * Math.log10(circumference - neck) - 70.041 * Math.log10(height) + 36.76;
            if(percentFat < MAX_BODYFAT_MALE){
                passBodyfat = true;
            }else{
                passBodyfat = false;
            }
        } else {
            percentFat = 163.205 * Math.log10(circumference + hips - neck) - 97.684 * Math.log10(height) - 78.387;
            if(percentFat < MAX_BODYFAT_FEMALE){
                passBodyfat = true;
            }else{
                passBodyfat = false;
            }
        }
        bodyFatPercentTextView.setText((int) Math.round(percentFat) + "%");
        return percentFat;
    }

    private void calcHeightWeight() {
        passHeightWeight = false;
        int maxWeight;

        if (mData.isMale) {
            maxWeight = mData.getWeightMale()[height - MIN_HEIGHT];
            initWeightSeekBar(maxWeight);

            if (weight > maxWeight) {
                passHeightWeight = false;
            } else {
                passHeightWeight = true;
            }
        } else {
            if (weight > mData.getWeightFemale()[height - MIN_HEIGHT]) {
                passHeightWeight = false;
            } else {
                passHeightWeight = true;
            }
        }
    }

    private void calculateCircumference() {
        if (circumference <= mData.getCircumference()) {
            passCircumference = true;
            passCircumferenceImageView.setImageResource(R.drawable.pass_icon);

        } else {
            passCircumference = false;
            passCircumferenceImageView.setImageResource(R.drawable.fail_icon);
        }
    }

    //returns string of type 5'9"
    private String formatInches(int inches) {
        String format = "";
        String feet = Integer.toString((int) inches / 12);
        String inchesRemaining = Integer.toString(inches % 12);
        format = feet + "'" + inchesRemaining + "\"";
        return format;
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
        age = mData.getAge();
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
    }

}
