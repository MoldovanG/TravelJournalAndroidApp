package com.example.george.travelaplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM  travelobject WHERE email = :mEmail")
    List<TravelObject> getAllTravels(String mEmail);

    @Insert
    void insertAll(TravelObject... travelObjects);
}
