package com.andrios.prt.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.andrios.prt.Adapters.HeightListViewAdapter;
import com.andrios.prt.Adapters.MyAdapter;
import com.andrios.prt.AndriosData;
import com.andrios.prt.R;

import java.util.ArrayList;

public class BCA_Activity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    AndriosData mData = new AndriosData();
    HeightListViewAdapter heightListViewAdapter;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bca_activity_update);
        setConnections();
    }

    private void setConnections() {

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Integer> heightList = new ArrayList<>();
        for (int i = 50; i < 90; i++){
            heightList.add(new Integer(i));
        }
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(heightList);
        mRecyclerView.setAdapter(mAdapter);
/**
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
**/
    }


}
