package com.example.gustavobarbosa.gorest.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gustavobarbosa.gorest.Adapter.NationAdapterRecycler;
import com.example.gustavobarbosa.gorest.Helper.NationDbHelper;
import com.example.gustavobarbosa.gorest.Helper.NationLists;
import com.example.gustavobarbosa.gorest.MainActivity;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.example.gustavobarbosa.gorest.R;
import com.example.gustavobarbosa.gorest.RecyclerListener.RecyclerClick_Listener;
import com.example.gustavobarbosa.gorest.RecyclerListener.RecyclerTouchListener;
import com.example.gustavobarbosa.gorest.Toolbar.Toolbar_ActionMode_Callback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitedFragment extends Fragment {

    private static RecyclerView recyclerView;
    private static View view;
    private static NationAdapterRecycler adapter;
    private android.support.v7.view.ActionMode mActionMode;

    public VisitedFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_visited,container,false);
        populateRecyclerView();
        implementRecyclerViewClickListeners();
        return view;
    }

    private void implementRecyclerViewClickListeners() {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerClick_Listener() {
            @Override
            public void onClick(View view, int position) {
                //If ActionMode not null select item
                if (mActionMode != null)
                    onListItemSelect(position);
                else {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("NATION", NationLists.getInstance().getNationsVisited().get(position));

                        Fragment fragment = new DescriptionFragment();
                        fragment.setArguments(bundle);

                    MainActivity.getFragment().beginTransaction().replace(R.id.frame, fragment).addToBackStack("HomeFragment").commit();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                //Select item on long click
                onListItemSelect(position);
            }
        }));
    }

    private void populateRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView = view.findViewById(R.id.recyclerNations);

        /*
        nationList = new ArrayList<>();

        Nation a = new Nation();
        a.setName("Canadá");
        Date date = new Date();
        date.setDate(04);
        date.setMonth(10);
        date.setYear(1996);
        a.setDate(date);
        nationList.add(a);

        a = new Nation();
        a.setName("Canadá");
        date = new Date();
        date.setDate(03);
        date.setMonth(12);
        date.setYear(1956);
        a.setDate(date);
        nationList.add(a);

        a = new Nation();
        a.setName("Groelandia");
        date = new Date();
        date.setDate(02);
        date.setMonth(10);
        date.setYear(1999);
        a.setDate(date);
        nationList.add(a);

        a = new Nation();
        a.setName("Brasil");
        date = new Date();
        date.setDate(05);
        date.setMonth(11);
        date.setYear(1993);
        a.setDate(date);
        nationList.add(a);

        */

        adapter = new NationAdapterRecycler(NationLists.getInstance().getNationsVisited(),getContext());

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }
    /*
        metodo de seleção de itens da lista
    */

    private void onListItemSelect(int position) {
        adapter.toggleSelection(position);//Alterna a seleção

        boolean hasCheckedItems = adapter.getSelectedCount() > 0;//Verifica se já há itens selecionados ou não

        if (hasCheckedItems && mActionMode == null)
            // há itens selecionados, inicie o actionMode
            mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(new Toolbar_ActionMode_Callback(getContext(), adapter, NationLists.getInstance().getNationsVisited(), false));
        else if (!hasCheckedItems && mActionMode != null) {
            // não há itens selecionados, termine o actionMode
            mActionMode.finish();
            setNullToActionMode();
        }

        if (mActionMode != null)
            //define a contagem de quantos itens estão selecionados
            mActionMode.setTitle(String.valueOf(adapter
                    .getSelectedCount()) + " selected");


    }

    //define o actionMode null após usar
    public void setNullToActionMode() {
        if (mActionMode != null)
            mActionMode = null;
    }

    //Delete selected rows
    public  void deleteRows() {
        SparseBooleanArray selected = adapter
                .getSelectedIds();//Pega os ids selecionados

        //Faz um loop em todos os ids selecionados
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //Se o id estiver presente remova
                NationLists.getInstance().removeNationsDB(selected.keyAt(i));

            }
        }

        NationLists.getInstance().refreshListDB();
        adapter.refreshAdapter();
        Toast.makeText(getActivity(), selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();//Show Toast
        mActionMode.finish();//Finish action mode after use
        setNullToActionMode();
    }


}
