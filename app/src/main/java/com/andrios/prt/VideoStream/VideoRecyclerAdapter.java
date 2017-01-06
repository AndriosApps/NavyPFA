package com.andrios.prt.VideoStream;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrios.prt.R;

import java.util.ArrayList;

/**
 * Created by Corey on 1/3/2017.
 */

public class VideoRecyclerAdapter
        extends RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder> {

    private final ArrayList<VideoObject> mValues;
    public VideoRecyclerAdapter(ArrayList<VideoObject> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(holder.mItem.getTitle());
        holder.mUrlView.setText(mValues.get(position).getUrl());
        holder.mImageView.setImageResource(holder.mItem.getThumbResource());

        //TODO Download thumbnails. http://stackoverflow.com/questions/4317665/how-to-get-thumbnail-for-video-in-my-sdcard-android-data-mypackage-files-folder

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Not required in scope of project
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void add(ArrayList<VideoObject> data) {
        mValues.clear();
        mValues.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        mValues.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mUrlView;
        public final ImageView mImageView;
        public VideoObject mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title_text_view);
            mImageView = (ImageView) view.findViewById(R.id.video_cover_image_view);
            mUrlView = (TextView) view.findViewById(R.id.url_text_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mUrlView.getText() + "'";
        }
    }
}