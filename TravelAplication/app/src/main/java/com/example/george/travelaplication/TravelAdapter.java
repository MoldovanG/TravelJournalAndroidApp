package com.example.george.travelaplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelViewHolder> {

    private static final String TAG = "TRAVELADAPTERTAG";
    List<TravelObject> mTravelObjects;

    public TravelAdapter(List<TravelObject> mTravelObjects) {
        this.mTravelObjects = mTravelObjects;
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View travelView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.travel_layout, viewGroup, false);
        return new TravelViewHolder(travelView);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder travelViewHolder, int i) {

        TravelObject mTravel = mTravelObjects.get(i);

        travelViewHolder.mBoldTextView.setText(mTravel.getmBoldText());
        travelViewHolder.mNormalTextView.setText(mTravel.getmNormalText());

        File imgFile = new File(mTravel.getmPicture());

        if (!mTravel.getmPicture().isEmpty())
            Picasso.get().load(mTravel.getmPicture()).into(travelViewHolder.mImageView);
        Log.d(TAG, mTravel.getmPicture());
    }

    @Override
    public int getItemCount() {
        return mTravelObjects.size();
    }
}
