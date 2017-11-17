package com.example.gustavobarbosa.gorest.Adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavobarbosa.gorest.Helper.NationLists;
import com.example.gustavobarbosa.gorest.MainActivity;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.example.gustavobarbosa.gorest.R;
import com.caverock.androidsvg.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Gustavo Barbosa on 29/10/2017.
 */

public class NationAdapterGrid extends BaseAdapter {

    List<Nation> nationList;
    Context context;
    ProgressDialog load;


    public NationAdapterGrid(@NonNull Context context, List<Nation> nations) {
        this.nationList = nations;
        this.context = context;
    }


    @Override
    public int getCount() {
        return  nationList != null ? nationList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return  nationList != null ? nationList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return  nationList != null ? i : null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        view = LayoutInflater.from(context).inflate(R.layout.grid_design, viewGroup, false);
        NationViewHolderGrid holder = new NationViewHolderGrid(view);

        Nation nation = nationList.get(i); //pega um elemento da lista de nações
        holder.textGrid.setText(nation.getName());

        if(nation.getDate()==null)
            holder.textGrid.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0); //Remove a imagem de check do grid

            return view;

    }

    public void refreshAdapter()
    {
        if(nationList!=null)
            nationList.clear();
        nationList  = NationLists.getInstance().getNationsRest();
        notifyDataSetChanged();
    }


    public class NationViewHolderGrid {
        public ImageView imgGrid;
        public TextView textGrid;


        public NationViewHolderGrid(View view)
        {
            textGrid = view.findViewById(R.id.nameNation);
            imgGrid = view.findViewById(R.id.imageNation);
        }

    }



    /*
         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = LayoutInflater.from(context).inflate(R.layout.grid_design, null); //atribui o layout a ser usado pelo gridview a cada item do grid

        Nation nation = nationList.get(i); //pega um elemento da lista de nações
        //WebView imgNation = view.findViewById(R.id.imageNation);
        TextView nameNation = view.findViewById(R.id.nameNation);

        nameNation.setText(nation.getName());

        // Picasso.with(context).load("https://restcountries.eu/data/afg.svg").into(imgNation);

        //nation.setDrawable(imgNation.getDrawable());
        //Uri uri = Uri.parse("https://restcountries.eu/data/alb.svg");
        //imgNation.setImageURI(uri);
         //new HttpImageRequestTask().execute("https://restcountries.eu/data/alb.svg");



     */


    /*

    public class HttpImageRequestTask extends AsyncTask<String, Void, Drawable> {

        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            //load = ProgressDialog.show(context, "Por favor aguarde","Baixando Imagens ...");
        }

        @Override
        protected Drawable doInBackground(String... strings) {
            SVG svg = null;

            try {
                final URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                svg = SVG.getFromInputStream(inputStream);
                inputStream.close();

                return  svg.dr;

            } catch (Exception e) {
                Log.e("TAG", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            // Update the view
            updateImageView(drawable);

        }

        @SuppressLint("NewApi")
        private void updateImageView(Drawable drawable) {
            if (drawable != null) {
                // Try using your library and adding this layer type before switching your SVG parsing
             imgNation.setImageDrawable(drawable);
            }
        }


        /*
        @Override
        protected void onPostExecute() {
            // Update the view
            if(drawable != null){
                // Try using your library and adding this layer type before switching your SVG parsing
                imgNation.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                imgNation.setImageDrawable(drawable);
            }
        }




    }
    */




}