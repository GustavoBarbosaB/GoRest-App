package com.example.gustavobarbosa.gorest.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavobarbosa.gorest.Fragments.DescriptionFragment;
import com.example.gustavobarbosa.gorest.Helper.NationLists;
import com.example.gustavobarbosa.gorest.MainActivity;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.example.gustavobarbosa.gorest.R;

import java.util.List;


/**
 * Created by Gustavo Barbosa on 01/11/2017.
 */

public class NationAdapterRecycler extends RecyclerView.Adapter{

    private List<Nation> nationList;
    private Context context;
    private SparseBooleanArray mSelectedItemsIds;


    public NationAdapterRecycler(List<Nation> listNation, Context context){
        this.nationList = listNation;
        this.context = context;
        mSelectedItemsIds = new SparseBooleanArray();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_design,parent,false);


        return new NationViewHolderRecycler(view);
}

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        NationViewHolderRecycler holder = (NationViewHolderRecycler) viewHolder;
        Nation nation = nationList.get(position);

        holder.nome.setText(nation.getName());
        holder.data.setText(nation.getDate().getDate()+"/"
                            +nation.getDate().getMonth()+"/"
                            +nation.getDate().getYear());

        /** altera a cor do recycler view quando selecionado **/
        holder.itemView
                .setBackgroundColor(mSelectedItemsIds.get(position) ? 0x9934B5E4
                        : Color.TRANSPARENT);


    }

    @Override
    public int getItemCount() {
        return nationList.size();
    }

    public class NationViewHolderRecycler extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public final TextView nome;
        public final TextView data;

        public NationViewHolderRecycler(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeTxt);
            data = itemView.findViewById(R.id.txtVisited);
            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

        }
    }
    /***
     * Methods required for do selections, remove selections, etc.
     */

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }


    //Remove selected selections
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    public void refreshAdapter()
    {
        if(nationList!=null)
            nationList.clear();
        nationList  = NationLists.getInstance().getNationsVisited();
        notifyDataSetChanged();
    }

}
