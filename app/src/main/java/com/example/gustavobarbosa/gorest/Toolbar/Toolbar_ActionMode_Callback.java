package com.example.gustavobarbosa.gorest.Toolbar;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gustavobarbosa.gorest.Adapter.NationAdapterRecycler;
import com.example.gustavobarbosa.gorest.Fragments.VisitedFragment;
import com.example.gustavobarbosa.gorest.MainActivity;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.example.gustavobarbosa.gorest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Barbosa on 01/11/2017.
 */

public class Toolbar_ActionMode_Callback implements ActionMode.Callback {

    private Context context;
    private NationAdapterRecycler recyclerView_adapter;
    private List<Nation> message_models;
    private boolean isListViewFragment;

    public Toolbar_ActionMode_Callback(Context context, NationAdapterRecycler recyclerView_adapter, List<Nation> message_models, boolean isListViewFragment) {
        this.context = context;
        this.recyclerView_adapter = recyclerView_adapter;
        this.message_models = message_models;
        this.isListViewFragment = isListViewFragment;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.actionbar, menu);//Inflar o menu actionmode
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        if (Build.VERSION.SDK_INT < 11) {
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_delete), MenuItemCompat.SHOW_AS_ACTION_NEVER);
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_cancel), MenuItemCompat.SHOW_AS_ACTION_NEVER);
        } else {
            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_cancel).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }


    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                ((VisitedFragment)MainActivity.getFragment().findFragmentById(R.id.frame)).deleteRows();//deletar linhas selecionadas
                break;
            case R.id.action_cancel:
                ((VisitedFragment)MainActivity.getFragment().findFragmentById(R.id.frame)).setNullToActionMode();
                mode.finish();//Finaliza o action mode
                break;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
            recyclerView_adapter.removeSelection();  //remove toda a seleção

    }
}
