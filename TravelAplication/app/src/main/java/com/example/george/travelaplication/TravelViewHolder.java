package com.example.george.travelaplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TravelViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mBoldTextView;
    public TextView mNormalTextView;
    public TravelViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView =itemView.findViewById(R.id.imageview_travel);
        mBoldTextView = itemView.findViewById(R.id.bold_textview_travel);
        mNormalTextView = itemView.findViewById (R.id.normal_textview_travel);
    }
}
