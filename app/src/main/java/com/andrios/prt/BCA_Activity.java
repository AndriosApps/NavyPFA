package com.andrios.prt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class BCA_Activity extends Activity {

    HeightListViewAdapter heightListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bca_activity_update);
        setConnections();
    }

    private void setConnections() {

        final ListView instructionListView = (ListView) findViewById(R.id.height_list_view);

        // Create a new {@link ArrayAdapter} of earthquakes
        heightListViewAdapter = new HeightListViewAdapter(this);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        instructionListView.setAdapter(heightListViewAdapter);

        instructionListView.setClickable(true);
        instructionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                int height = instructionListView.getHeight();
                int itemHeight = instructionListView.getChildAt(0).getHeight();
                instructionListView.setSelectionFromTop(position, height/2 - itemHeight/2);
                TextView tv=(TextView)view.findViewById(R.id.height_text);
                tv.setTextSize(48);
            }
        });

    }


}
