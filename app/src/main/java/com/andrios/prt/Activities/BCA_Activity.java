package com.andrios.prt.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andrios.prt.Adapters.HeightAdapter;
import com.andrios.prt.Adapters.HeightListViewAdapter;
import com.andrios.prt.Adapters.WaistAdapter;
import com.andrios.prt.Adapters.WeightAdapter;
import com.andrios.prt.AndriosData;
import com.andrios.prt.R;

import java.util.ArrayList;

public class BCA_Activity extends Activity {
    private RecyclerView heightRecyclerView;
    private RecyclerView.Adapter heightAdapter;
    private RecyclerView.LayoutManager heightLayoutManager;

    private RecyclerView weightRecyclerView;
    private RecyclerView.Adapter weightAdapter;
    private RecyclerView.LayoutManager weightLayoutManager;

    private RecyclerView waistRecyclerView;
    private RecyclerView.Adapter waistAdapter;
    private RecyclerView.LayoutManager waistLayoutManager;

    AndriosData mData = new AndriosData();
    HeightListViewAdapter heightListViewAdapter;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bca_activity_update);

        setHeightAdapter();
        setWeightAdapter();
        setWaistAdapter();

    }

    private void setWeightAdapter() {
        weightRecyclerView = (RecyclerView) findViewById(R.id.weight_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        weightRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        weightLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        weightRecyclerView.setLayoutManager(weightLayoutManager);

        ArrayList<Integer> heightList = new ArrayList<>();

        // specify an adapter (see also next example)
        weightAdapter = new WeightAdapter();
        weightRecyclerView.setAdapter(weightAdapter);
    }

    private void setHeightAdapter() {

        heightRecyclerView = (RecyclerView) findViewById(R.id.height_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        heightRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        heightLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        heightRecyclerView.setLayoutManager(heightLayoutManager);

        ArrayList<Integer> heightList = new ArrayList<>();
        for (int i = 50; i < 90; i++){
            heightList.add(new Integer(i));
        }
        // specify an adapter (see also next example)
        heightAdapter = new HeightAdapter(heightList);
        heightRecyclerView.setAdapter(heightAdapter);

    }

    private void setWaistAdapter() {

        waistRecyclerView = (RecyclerView) findViewById(R.id.waist_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        waistRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        waistLayoutManager = new LinearLayoutManager(this, HORIZONTAL, false);
        waistRecyclerView.setLayoutManager(waistLayoutManager);

        ArrayList<Integer> waistList = new ArrayList<>();
        for (int i = 15; i < 55; i++){
            waistList.add(new Integer(i));
        }
        // specify an adapter (see also next example)
        waistAdapter = new WaistAdapter(waistList);
        waistRecyclerView.setAdapter(waistAdapter);

    }


}
