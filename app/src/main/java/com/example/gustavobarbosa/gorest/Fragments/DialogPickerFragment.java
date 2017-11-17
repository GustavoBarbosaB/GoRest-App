package com.example.gustavobarbosa.gorest.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.widget.DatePicker;
import android.widget.Toast;


import com.example.gustavobarbosa.gorest.Helper.NationLists;
import com.example.gustavobarbosa.gorest.MainActivity;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.example.gustavobarbosa.gorest.R;

import java.util.Calendar;
import java.util.Date;

public class DialogPickerFragment extends DialogFragment  {
    private Nation n;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        Bundle bundle = this.getArguments();
        n = null;
        if(bundle!=null) {
            n = (Nation) bundle.getSerializable("NATION");
        }

        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               if(n!=null){
                   Date d = new Date(i,i1,i2);
                   n.setDate(d);
                   NationLists.getInstance().insertNation(n);
                   Toast.makeText(getActivity(),"Inserido com sucesso!",Toast.LENGTH_SHORT).show();
                   NationLists.getInstance().refreshListDB();
               }else
                   Toast.makeText(getActivity(),"Erro ao inserir nação!",Toast.LENGTH_SHORT).show();


                MainActivity.replaceFragment(new HomeFragment(),"HomeFragment");

            }
        },ano,mes,dia);

    }

}
/*
 implements DatePickerDialog.OnDateSetListener

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Toast.makeText(getActivity(),"Visitado em:"+i2+"/"+i1+"/"+i,Toast.LENGTH_SHORT).show();
    }
 */