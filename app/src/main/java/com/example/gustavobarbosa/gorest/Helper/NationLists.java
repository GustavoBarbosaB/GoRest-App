package com.example.gustavobarbosa.gorest.Helper;

import com.example.gustavobarbosa.gorest.Model.Nation;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Gustavo Barbosa on 02/11/2017.
 */

public class NationLists {

    private static NationLists nation;
    private static List<Nation> rest;
    private static List<Nation> visited;

    private NationLists(List<Nation> rest)
    {
            this.rest = rest;
            refreshListDB();
    }

    public static NationLists init(List<Nation> rest){
        if(nation==null) {
            NationDbHelper.init(getApplicationContext());//inicia o helper para conexão com o BD
            return nation = new NationLists(rest);
        }
        return nation;
    }

    public static NationLists getInstance(){
        return nation;
    }

    private static List<Nation> getNationsDB() {
        return NationDbHelper.getInstance().getAllNations();
    }

    public synchronized List<Nation> getNationsVisited()
    {
        return visited;
    }

    /*
        Remove a nação selecionada
     */
    public synchronized void removeNationsDB(int i)
    {
        NationDbHelper.getInstance().delete(visited.get(i));
    }

    public synchronized void insertNation(Nation n)
    {
        NationDbHelper.getInstance().createOrUpdate(n);
    }

    /*
     Método criado para pegar a lista de nações salvas na Main
   */
    public synchronized List<Nation> getNationsRest() {
        return rest;
    }

    public synchronized  void refreshListDB()
    {
        visited = getNationsDB();
        compareNations();
    }

    /*
     Método para adicionar as datas nos paises já visitados
     Isso é feito pois caso um país seja retirado, ou adicionado não ocorrerá problemas
     com nosso código, apesar da abordagem ser mais custosa do que simplesmente pegar
     o país por posição na listaRest
  */
    private  static void compareNations() {

        Boolean t;

        for(Nation res : rest ) {
            t=false;//começa como falso dizendo que não existe
            for (Nation vis:visited)
            {
                if(res.getName().equals(vis.getName())) {//testa se o nome de um é igual ao de outro
                    res.setDate(vis.getDate());//coloca a data como sendo a salva na DB
                    t=true;//marca como existente
                    break;
                }
            }

            if(!t)//se não encontrado
                res.setDate(null);
        }

    }



}
