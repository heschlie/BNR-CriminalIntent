package com.heschlie.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by heschlie on 6/1/2015.
 */
public class CrimeCameraActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeCameraFragment();
    }
}
