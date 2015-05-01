package com.heschlie.criminalintent;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by heschlie on 4/28/15.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private Context mContext;

    public Crime(Context context) {
        mId = UUID.randomUUID();
        mDate = new Date();
        mContext = context.getApplicationContext();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        java.text.DateFormat df = DateFormat.getLongDateFormat(mContext);
        return DateFormat.format("EEEE", mDate) +", " + df.format(mDate);
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
