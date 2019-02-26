package com.example.george.travelaplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TravelObject {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "picture")
    private String mPicture;

    @ColumnInfo(name = "bold_text")
    private String mBoldText;

    @ColumnInfo(name = "normal_text")
    private String mNormalText;

    @ColumnInfo(name = "favourite_flag")
    private boolean mFavouriteFlag;

    @ColumnInfo(name = "email")
    private String mEmail; // asa diferentiez obiectele de travel intre utilizatori

    public TravelObject(String mPicture, String mBoldText, String mNormalText, boolean mFavouriteFlag, String mEmail) {
        this.mPicture = mPicture;
        this.mBoldText = mBoldText;
        this.mNormalText = mNormalText;
        this.mFavouriteFlag = mFavouriteFlag;
        this.mEmail = mEmail;
    }


    public String getPicture() {
        return mPicture;
    }

    public String getBoldText() {
        return mBoldText;
    }

    public String getNormalText() {
        return mNormalText;
    }

    public boolean getFavouriteFlag() {
        return mFavouriteFlag;
    }

    public String getEmail() {
        return mEmail;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmPicture() {
        return mPicture;
    }

    public void setmPicture(String mPicture) {
        this.mPicture = mPicture;
    }

    public String getmBoldText() {
        return mBoldText;
    }

    public void setmBoldText(String mBoldText) {
        this.mBoldText = mBoldText;
    }

    public String getmNormalText() {
        return mNormalText;
    }

    public void setmNormalText(String mNormalText) {
        this.mNormalText = mNormalText;
    }

    public boolean ismFavouriteFlag() {
        return mFavouriteFlag;
    }

    public void setmFavouriteFlag(boolean mFavouriteFlag) {
        this.mFavouriteFlag = mFavouriteFlag;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
