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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andrios.prt.Instruction;
import com.andrios.prt.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * {@link InstructionListViewAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Instruction} objects.
 */
public class InstructionListViewAdapter extends BaseAdapter {
    private static final String LOCATION_SEPARATOR = "of";
    private LayoutInflater mInflator;
    private ArrayList<Instruction> instructionList;
    public static final String TAG = "InstructionLVAdapter";
    private Context context;

    public InstructionListViewAdapter(Context context, ArrayList<Instruction> quakeList) {
        super();
        this.instructionList = quakeList;
        this.context = context;

    }


    @Override
    public int getCount() {
        return instructionList.size();
    }

    @Override
    public Instruction getItem(int i) {
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
                    R.layout.instruction_list_item, parent, false);
        }
        Instruction instruction = getItem(position);

        TextView ssicTextView = (TextView) listItemView.findViewById(R.id.label_ssic);
        ssicTextView.setText(instruction.getSsic());

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.label_instruction_name);
        nameTextView.setText(instruction.getName());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.label_date);
        String dateString = formatDate(new Date(instruction.getUpdateDate()));
        dateTextView.setText(dateString);

        return listItemView;
    }



    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }





    public void clear(){
        instructionList.clear();
    }

    public void add(ArrayList<Instruction> Instructions) {
        Log.d(TAG, "add: " + Instructions.size());
        for(Instruction e: Instructions){
            Log.d(TAG, "add: " + e.toString());
            instructionList.add(e);
            this.notifyDataSetChanged();
        }
    }
}