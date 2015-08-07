package com.heschlie.criminalintent;

import android.content.Context;
import android.text.format.DateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by heschlie on 4/28/15.
 */
public class Crime {
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";
    private static final String JSON_PHOTO = "photo";
    private static final String JSON_SUSPECT =  "suspect";

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private Context mContext;
    private Photo mPhoto;
    private String mSuspect;

    public Crime(Context context) {
        mId = UUID.randomUUID();
        mDate = new Date();
        mContext = context.getApplicationContext();
    }

    public Crime(Context c, JSONObject json) throws JSONException {
        mContext = c;
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_TITLE)) {
            mTitle = json.getString(JSON_TITLE);
        }
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
        if (json.has(JSON_PHOTO)) {
            mPhoto = new Photo(json.getString(JSON_PHOTO));
        }
        if (json.has(JSON_SUSPECT)) {
            mSuspect = json.getString(JSON_SUSPECT);
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, mDate.getTime());
        if (mPhoto != null) {
            json.put(JSON_PHOTO, mPhoto);
        }
        json.put(JSON_SUSPECT, mSuspect);
        return json;
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

    public Date getDate() {
        return mDate;
    }

    public String getDateString() {
        java.text.DateFormat df = DateFormat.getLongDateFormat(mContext);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return DateFormat.format("EEEE", mDate) +", " + df.format(mDate) +
                ", " + sdf.format(mDate);
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

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo photo) {
        mPhoto = photo;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
