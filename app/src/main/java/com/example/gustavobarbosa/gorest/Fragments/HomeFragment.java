package com.example.gustavobarbosa.gorest.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.gustavobarbosa.gorest.Adapter.NationAdapterGrid;
import com.example.gustavobarbosa.gorest.Helper.NationLists;
import com.example.gustavobarbosa.gorest.MainActivity;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.example.gustavobarbosa.gorest.R;

import java.util.List;

/**
 * Created by Gustavo Barbosa on 29/10/2017.
 */

public class HomeFragment extends Fragment {

    GridView gridView;
    NationAdapterGrid nationAdapterGrid;
    List<Nation> nationList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = rootView.findViewById(R.id.gridView);

        nationAdapterGrid = new NationAdapterGrid(getContext(),NationLists.getInstance().getNationsRest());
        gridView.setAdapter(nationAdapterGrid);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction ft = MainActivity.getFragment().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putSerializable("NATION",NationLists.getInstance().getNationsRest().get(position));

                Fragment fragment = new DescriptionFragment();
                fragment.setArguments(bundle);

                ft.replace(R.id.frame,fragment);
                ft.addToBackStack("HomeFragment");
                ft.commit();
            }
        });

        return rootView;
    }


}