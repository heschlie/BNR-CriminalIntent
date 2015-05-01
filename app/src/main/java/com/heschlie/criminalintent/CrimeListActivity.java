package com.heschlie.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by heschlie on 4/30/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
