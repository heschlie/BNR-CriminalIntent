package com.heschlie.criminalintent;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by heschlie on 5/6/15.
 */
public class CriminalIntentJSONSerializer {
    private static final String TAG = "CIJSONSerializer";

    private Context mContext;
    private String mFilename;

    public CriminalIntentJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    private boolean isExternalAvailable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException{
        JSONArray array = new JSONArray();

        for (Crime c : crimes) {
            array.put(c.toJSON());
        }

        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public void saveCrimesExternal(ArrayList<Crime> crimes) throws JSONException, IOException{
        if (isExternalAvailable()) {
            JSONArray array = new JSONArray();

            for (Crime c : crimes) {
                array.put(c.toJSON());
            }

            Writer writer = null;
            try {
                File file = new File(mContext.getExternalFilesDir(null), mFilename);
                OutputStream out = new FileOutputStream(file);
                writer = new OutputStreamWriter(out);
                writer.write(array.toString());
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    public ArrayList<Crime> loadCrimes() throws IOException, JSONException {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray array = (JSONArray) new JSONTokener((jsonString.toString())).nextValue();
            for (int i = 0; i < array.length(); i++) {
                crimes.add(new Crime(mContext, array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            Log.i(TAG, "No saved crimes found");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return crimes;
    }

    public ArrayList<Crime> loadCrimesExternal() throws IOException, JSONException {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        if (isExternalAvailable()) {
            BufferedReader reader = null;
            try {
                File file = new File(mContext.getExternalFilesDir(null), mFilename);
                InputStream in = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder jsonString = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }

                JSONArray array = (JSONArray) new JSONTokener((jsonString.toString())).nextValue();
                for (int i = 0; i < array.length(); i++) {
                    crimes.add(new Crime(mContext, array.getJSONObject(i)));
                }
            } catch (FileNotFoundException e) {
                Log.i(TAG, "No saved crimes found");
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }

        return crimes;
    }
}
