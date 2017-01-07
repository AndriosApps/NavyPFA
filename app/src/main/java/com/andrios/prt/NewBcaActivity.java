package com.andrios.prt;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class NewBcaActivity extends Activity {

    private static final String TAG = "NewBcaActivity";
    private static int MIN_HEIGHT = 51;
    private static int MAX_HEIGHT = 86;
    private static int MIN_WEIGHT = 90;
    private static int MAX_WEIGHT = 300;
    private static int MIN_CIRCUM = 20;
    private static int MAX_CIRCUM = 55;

    private CustomSeekBar heightSeekBar;
    private CustomSeekBar weightSeekBar;
    private CustomSeekBar circumferenceSeekBar;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem progressItem;
    private TextView heightTextView;
    private TextView weightTextView;
    private TextView circumferenceTextView;
    private ImageView passHeightWeightImageView;
    private ImageView passCircumferenceImageView;

    private CardView circumferenceCardView;

    private boolean inStandards;
    private boolean isMale = true;
    private AndriosData mData;
    private float remainderSpan;
    private double circumference = 20.0;


    int height = 69, weight = 100, age = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bca);

        heightTextView = (TextView) findViewById(R.id.height_text_view);
        weightTextView = (TextView) findViewById(R.id.weight_text_view);
        circumferenceTextView = (TextView) findViewById(R.id.circumferance_text_view);
        passHeightWeightImageView = (ImageView) findViewById(R.id.pass_height_weight_image_view);
        passCircumferenceImageView = (ImageView) findViewById(R.id.pass_circumference_image_view);
        circumferenceCardView = (CardView) findViewById(R.id.circumference_card_view);

        mData = new AndriosData();
        weightSeekBar = (CustomSeekBar) findViewById(R.id.weight_seek_bar);
        heightSeekBar = (CustomSeekBar) findViewById(R.id.height_seek_bar);
        circumferenceSeekBar = (CustomSeekBar) findViewById(R.id.circumferance_seek_bar);

        initWeightSeekBar(mData.getWeightMale()[height-MIN_HEIGHT]);
        initHeightSeekBar();
        initCircumferenceBar();
        heightSeekBar.setProgress(height-MIN_HEIGHT);
        weightSeekBar.setProgress(weight-MIN_WEIGHT);
        circumferenceSeekBar.setProgress((int) (circumference*2) - MIN_CIRCUM);
        //circumferenceSeekBar.setProgress(weight-MIN_WEIGHT);
        }

    private void initCircumferenceBar() {
        int totalSpan = (MAX_CIRCUM - MIN_CIRCUM) * 2;
        circumferenceSeekBar.setMax(totalSpan);

        progressItemList = new ArrayList<ProgressItem>();

        float passSpan = (39 - MIN_CIRCUM) * 2;

        //RED SPAN
        progressItem = new ProgressItem();
        Log.d(TAG, "initCircumSeekBar: pass span percent " + (passSpan/totalSpan)*100 +"%" );
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

        circumferenceSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                double decimal = arg1 % 2;
                circumference = MIN_CIRCUM + (arg1 / 2) + decimal / 2;
                Log.d(TAG, "onProgressChanged: Circumference: " + circumference);
                circumferenceTextView.setText(circumference + "\"");
                calculateCircumference();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }

    private void initHeightSeekBar() {
        int totalSpan = MAX_HEIGHT-MIN_HEIGHT;
        heightSeekBar.setMax(totalSpan);

        progressItemList = new ArrayList<ProgressItem>();


        //GREY SPAN
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = (totalSpan);
        progressItem.color = R.color.andrios_grey;
        progressItemList.add(progressItem);


        heightSeekBar.initData(progressItemList);
        heightSeekBar.invalidate();

        heightSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                height = arg1 + MIN_HEIGHT;
                Log.d(TAG, "onProgressChanged: " + formatInches(height));
                heightTextView.setText(formatInches(height));
                calcHeightWeight();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }

    private void initWeightSeekBar(int failWeight) {
        float totalSpan = MAX_WEIGHT-MIN_WEIGHT;
        progressItemList = new ArrayList<ProgressItem>();
        weightSeekBar.setMax(MAX_WEIGHT-MIN_WEIGHT);


        float passSpan = (failWeight - MIN_WEIGHT);

        Log.d(TAG, "initWeightSeekBar: failWeight: " + failWeight + " pass span: " + passSpan );

        //RED SPAN
        progressItem = new ProgressItem();
        Log.d(TAG, "initWeightSeekBar: pass span percent " + (passSpan/totalSpan)*100 +"%" );
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
        weightSeekBar.setOnSeekBarChangeListener(new CustomSeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                weight = arg1 + MIN_WEIGHT;
                Log.d(TAG, "onProgressChanged: " + weight);
                weightTextView.setText(weight + "lbs");
                calcHeightWeight();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }

    private void calcHeightWeight(){
        inStandards = false;
        int maxWeight;

        if(isMale){
            maxWeight = mData.getWeightMale()[height-MIN_HEIGHT];
            initWeightSeekBar(maxWeight);

            if(weight > maxWeight){
                inStandards = false;
            }else{
                inStandards = true;
            }
        }else{
            if(weight > mData.getWeightFemale()[height-MIN_HEIGHT]){
                inStandards = false;
            }else{
                inStandards = true;
            }
        }

        if(inStandards){
            passHeightWeightImageView.setImageResource(R.drawable.pass_icon);
            circumferenceCardView.setVisibility(View.GONE);
            Log.d(TAG, "calcHeightWeight: in standards");
        }else{
            passHeightWeightImageView.setImageResource(R.drawable.fail_icon);
            circumferenceCardView.setVisibility(View.VISIBLE);
            Log.d(TAG, "calcHeightWeight: not in standards");
        }
    }

    private void calculateCircumference(){
       if (circumference <= mData.getCircumference()){
            inStandards = true;
           passCircumferenceImageView.setImageResource(R.drawable.pass_icon);

        }else{
           inStandards = false;
           passCircumferenceImageView.setImageResource(R.drawable.fail_icon);
       }
    }

    //returns string of type 5'9"
    private String formatInches(int inches){
        String format = "";
        String feet = Integer.toString((int) inches / 12);
        String inchesRemaining = Integer.toString(inches % 12);
        format = feet + "'" + inchesRemaining + "\"";
        return format;
    }

}
