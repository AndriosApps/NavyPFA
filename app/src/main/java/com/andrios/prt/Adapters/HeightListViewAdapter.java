/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.andrios.prt.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andrios.prt.Classes.Instruction;
import com.andrios.prt.R;

import java.util.ArrayList;

/**
 * {@link HeightListViewAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Instruction} objects.
 */
public class HeightListViewAdapter extends BaseAdapter {
    private static final String LOCATION_SEPARATOR = "of";
    private LayoutInflater mInflator;
    private ArrayList<Integer> instructionList;
    public static final String TAG = "InstructionLVAdapter";
    private Context context;

    public HeightListViewAdapter(Context context) {
        super();

        instructionList = new ArrayList<>();
        for (int i = 40; i < 90; i++){
            instructionList.add(new Integer(i));
        }
        this.context = context;


    }


    @Override
    public int getCount() {
        return instructionList.size();
    }

    @Override
    public Integer getItem(int i) {
        return instructionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_item_height, parent, false);
        }

        Integer heightInches = instructionList.get(position);

        Integer heightFeet = heightInches/12;
        Integer heightIncchesRemainder = heightInches%12;


        TextView ssicTextView = (TextView) listItemView.findViewById(R.id.height_text);
        ssicTextView.setText(heightFeet + "'" + heightIncchesRemainder + '"');


            ssicTextView.setTextSize(32);


        return listItemView;
    }




    public void clear(){
        instructionList.clear();
    }


}