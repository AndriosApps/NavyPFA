package com.andrios.prt;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

public class NewBcaActivity extends Activity {

    private CustomSeekBar seekBar;
    private float totalSpan = 1500;
    private float redSpan = 200;
    private float darkGreySpan;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem progressItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bca);
        seekBar = (CustomSeekBar) findViewById(R.id.custom_seek_bar);
        initDataToSeekBar();
        }

    private void initDataToSeekBar() {
        progressItemList = new ArrayList<ProgressItem>();

        //RED SPAN
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((redSpan / totalSpan) * 100);
        progressItem.color = R.color.red;
        progressItemList.add(progressItem);

        //GREY SPAN
        progressItem = new ProgressItem();
        progressItem.progressItemPercentage = ((darkGreySpan / totalSpan) * 100);
        progressItem.color = R.color.andrios_grey;
        progressItemList.add(progressItem);


        seekBar.initData(progressItemList);
        seekBar.invalidate();
    }

}
