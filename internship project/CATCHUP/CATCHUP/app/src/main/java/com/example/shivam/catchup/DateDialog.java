package com.example.shivam.catchup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    EditText edittext10;
    public DateDialog(View view)
    {
        edittext10=(EditText) view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int Month=calendar.get(Calendar.MONTH);
        int date=calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,Month,date);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        String dat= day+"/"+(month+1)+"/"+year;
        edittext10.setText(dat);
    }



}
