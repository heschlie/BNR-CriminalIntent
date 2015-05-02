package com.heschlie.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import java.util.Date;

/**
 * Created by heschlie on 5/2/15.
 */
public class PickerDialogFragment extends DialogFragment {
    private static final String TAG = "PickerDialogFragment";
    private static final String DIALOG_DATE = "date";

    private Date mDate;

    public static PickerDialogFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(DatePickerFragment.EXTRA_DATE, date);

        PickerDialogFragment fragment = new PickerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(DatePickerFragment.EXTRA_DATE);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.picker_title)
                .setItems(R.array.date_picker_array, new DialogInterface.OnClickListener() {
                    FragmentManager fm = getActivity().getSupportFragmentManager();

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                DatePickerFragment dateDialog = DatePickerFragment.newInstance(mDate);
                                dateDialog.setTargetFragment(getTargetFragment(), CrimeFragment.REQUEST_DATE);
                                dateDialog.show(fm, DIALOG_DATE);
                                break;
                            case 1:
                                break;
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }
}
