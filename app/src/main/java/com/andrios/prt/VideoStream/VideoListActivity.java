package com.andrios.prt.VideoStream;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.andrios.prt.AndriosData;
import com.andrios.prt.R;

public class VideoListActivity extends Activity {

    private RecyclerView recyclerView;
    private VideoRecyclerAdapter mAdapter;
    private static final String TAG = "VideoListActivity: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        recyclerView = (RecyclerView) findViewById(R.id.video_recycler_view);
        assert recyclerView != null;

        mAdapter = new VideoRecyclerAdapter(AndriosData.getVideos());
        recyclerView.setAdapter(mAdapter);
        Log.d(TAG, "onCreate: ");
    }
}
