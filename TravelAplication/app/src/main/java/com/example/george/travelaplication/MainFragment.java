package com.example.george.travelaplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private static final String TAG ="MAINFRAGMENTTAG" ;
    private RecyclerView mRecyclerView;
    private TravelAdapter mTravelAdapter;
    private List<TravelObject> mTravelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_cc5_week4_travel, parent, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerview_travel);

       AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "production")
               .allowMainThreadQueries()
               .build();
        Bundle mExtras = getArguments();
        String Email = mExtras.getString("Email");
        Log.d(TAG,Email);
       mTravelList = db.userDao().getAllTravels(Email);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext() );
        mRecyclerView.setLayoutManager(layoutManager);

        mTravelAdapter = new TravelAdapter(mTravelList);
        mRecyclerView.setAdapter(mTravelAdapter);

        return rootView;
    }

    /*private List<TravelObject> getTravelList()
    {
        List<TravelObject> myList = new ArrayList<TravelObject>();
        TravelObject obj1 = new TravelObject("https://d2gk7xgygi98cy.cloudfront.net/48-3-large.jpg","Spring 2018","Paris");
        TravelObject obj2 = new TravelObject("https://images.unsplash.com/photo-1508710985089-e985fabbb184?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2800&q=80","Fall 2017","London");
        TravelObject obj3 = new TravelObject("https://images.unsplash.com/photo-1480413258216-ff003d179d65?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2100&q=80","Autumn 2017","Viena");
        TravelObject obj4 = new TravelObject("https://images.unsplash.com/photo-1503970999490-4404449dc349?ixlib=rb-1.2.1&auto=format&fit=crop&w=1938&q=80","Summer 2018","Rome");
        TravelObject obj5 = new TravelObject("https://images.unsplash.com/photo-1528702748617-c64d49f918af?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=934&q=80","Summer 2017","Dubai");
        TravelObject obj6 = new TravelObject("https://images.unsplash.com/photo-1517660895948-131a6d6c55d7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2693&q=80","Winter 2018","The Alps");

        myList.add(obj1);
        myList.add(obj2);
        myList.add(obj3);
        myList.add(obj4);
        myList.add(obj5);
        myList.add(obj6);
            return myList;
    }*/

}
