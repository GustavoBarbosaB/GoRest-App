package com.example.gustavobarbosa.gorest.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gustavobarbosa.gorest.Helper.NationLists;
import com.example.gustavobarbosa.gorest.MainActivity;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.example.gustavobarbosa.gorest.R;

/**
 * Created by Gustavo Barbosa on 31/10/2017.
 */

public class DescriptionFragment extends Fragment {

    private Button visited;
    private Nation n;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.description, container, false);
        Bundle bundle = this.getArguments();
        n = null;
        if(bundle!=null) {
            n = (Nation) bundle.getSerializable("NATION");
        }

        // -------------- Pega referencia de todos os textView ----------
        TextView name = rootView.findViewById(R.id.txtName);
        TextView capital = rootView.findViewById(R.id.txtcapital);
        TextView area = rootView.findViewById(R.id.txtarea);
        TextView population = rootView.findViewById(R.id.txtpopulation);
        TextView visitdate = rootView.findViewById(R.id.txtvisitdate);
        //--------------------------------------------------------------

        if(n!=null) {
            name.setText(n.getName());
            capital.setText(n.getCapital());
            area.setText(String.valueOf(n.getArea()));
            population.setText(String.valueOf(n.getPopulation()));

            if (n.getDate() != null) {//testa se a data é nula, se for é porque nunca foi visitado
                visitdate.setText(n.getDate().getDate()+"/"+n.getDate().getMonth()+"/"+n.getDate().getYear());
            }else{
                visitdate.setText("not visited");
               // visitdate.setVisibility(View.GONE);
            }

        }

        visited = rootView.findViewById(R.id.visitedButton);

        visited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });

        return rootView;
    }
/*
    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.getFragment().beginTransaction().remove(this);
    }
*/
    public void showTimePickerDialog(View v) {

        FragmentTransaction ft = MainActivity.getFragment().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NATION", n);
        DialogFragment newFragment = new DialogPickerFragment();
        newFragment.setArguments(bundle);

        newFragment.show(MainActivity.getFragment(), "timePicker");
    }
}
