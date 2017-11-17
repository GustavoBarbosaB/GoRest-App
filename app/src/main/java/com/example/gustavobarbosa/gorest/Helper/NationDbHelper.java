package com.example.gustavobarbosa.gorest.Helper;

import android.content.Context;

import com.example.gustavobarbosa.gorest.Model.Nation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Barbosa on 02/11/2017.
 */

public class NationDbHelper {
    private static NationDbHelper nationDbHelper;
    private static DataBaseHelper dataBaseHelper;

    private NationDbHelper(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }


    public static void init(Context context)
    {
        if(nationDbHelper == null)
            nationDbHelper = new NationDbHelper(context);
    }

    public static NationDbHelper getInstance(){
        return nationDbHelper;
    }

    /*
     Cria uma nação caso não exista
     synchronized é executado por uma thread por vez
   */
    private synchronized void createIfNotExists(Nation nation)
    {
        try {
            dataBaseHelper.getNation().createIfNotExists(nation);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
     Cria uma nação caso não exista, se existir somente atualiza
     synchronized é executado por uma thread por vez
   */
    public synchronized void createOrUpdate(Nation nation){
        //Cria ou atualiza caso já exista
        Nation auxNation = getNationByName(nation.getName());
        if(auxNation == null)
        {
            createIfNotExists(nation);
        }else {
            update(nation);
        }

    }

    /*
      Retorna uma nação dado um nome
      synchronized é executado por uma thread por vez
    */
    private Nation getNationByName(String name) {
        Nation n = null;

        try {
            n = dataBaseHelper.getNation().queryForId(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    /*
      Atualiza determinada nação
      synchronized é executado por uma thread por vez
    */
    private synchronized void update(Nation nation){
        try {
            dataBaseHelper.getNation().update(nation);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }



    /*
       deleta determinada nação
       synchronized é executado por uma thread por vez
     */
     public synchronized void delete(Nation nation){
        try {
            dataBaseHelper.getNation().delete(nation);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    /*
        Pega todas as nações salvas. Caso não exista nenhuma, um array vazio é retornado
     */
    public synchronized List<Nation> getAllNations(){

        try {
            return dataBaseHelper.getNation().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }



}
