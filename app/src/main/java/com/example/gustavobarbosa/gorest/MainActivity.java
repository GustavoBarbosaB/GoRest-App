package com.example.gustavobarbosa.gorest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gustavobarbosa.gorest.Helper.NationLists;
import com.example.gustavobarbosa.gorest.Fragments.AccountFragment;
import com.example.gustavobarbosa.gorest.Fragments.HomeFragment;
import com.example.gustavobarbosa.gorest.Fragments.VisitedFragment;
import com.example.gustavobarbosa.gorest.Helper.NationDbHelper;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.example.gustavobarbosa.gorest.Rest.InterfaceApi;
import com.example.gustavobarbosa.gorest.Rest.NationApi;
import com.facebook.AccessToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fm;
    private InterfaceApi apiService;
    public List<Nation> listNationRest;
    private BottomNavigationView navigation;
    private ProgressDialog progress;

    /** IMPLEMENTAÇÃO DO CLIQUE NOS ITENS DA BOTTOM NAVIGATION **/
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment(),"HomeFragment");
                    return true;
                case R.id.navigation_dashboard:
                    // Abre uma transação e adiciona
                    replaceFragment(new VisitedFragment(),"VisitedFragment");
                    return true;
                case R.id.navigation_notifications:
                    // Abre uma transação e adiciona
                    replaceFragment(new AccountFragment(),"AccountFragment");
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }else {
            navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            //Pega o suporte ao frame criado
            fm = getSupportFragmentManager();

            progress = new ProgressDialog(this);

            progress.setTitle("Carregando");
            progress.setMessage("Aguarde um momento...");
            progress.setCancelable(false); // desabilita o cancelar
            progress.show();

            connectionRest();

        }

    }




    /**
     Método chamado sempre que há alguma falha no Login com o Facebook
     Redireciona a tela de login
   **/
    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     Faz a conexão com a API e consome os dados,
     é utilizado um método assincrono para fazer isso.
     --------------------------------------------------------------------------
     --------------------------------------------------------------------------
     --------------------------------------------------------------------------
     O método será substituido por uma tarefaAssincrona, de modo a ativar assim
     uma view de carregamento.
     --------------------------------------------------------------------------
     --------------------------------------------------------------------------
     --------------------------------------------------------------------------
   **/

    private void connectionRest()
    {

        /* -------- REST CONNECTION ---------------*/
        apiService = NationApi.getNation().create(InterfaceApi.class);

        final Call<List<Nation>> call = apiService.getListNation();

        call.enqueue(new Callback<List<Nation>>() {

            @Override
            public void onResponse(Call<List<Nation>> call, Response<List<Nation>> response) {

                listNationRest = response.body();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NationLists.init(listNationRest);//inicia as listas
                        addFragment(new HomeFragment(),"HomeFragment"); //Inicia o fragment
                        progress.dismiss();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Nation>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(),"Verifique sua conexão com a internet!",Toast.LENGTH_SHORT).show();
                Log.e("TAG","Erro na RESPOSTA");
            }
        });

        //---------------------------------------//
    }

    public static FragmentManager getFragment()
    {
        return fm;
    }


    public static void replaceFragment(Fragment fragment,String name) {
        FragmentTransaction  transaction = fm.beginTransaction();

        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(name);

        transaction.commit();
    }

    private void addFragment(Fragment fragment,String name) {
        fm = getSupportFragmentManager();
        FragmentTransaction  transaction = fm.beginTransaction();

        transaction.add(R.id.frame, fragment);
        transaction.addToBackStack(name);

        transaction.commit();
    }

    /**
     *  Fiz a seguinte implementação para poder adicionar novamente a cor do icone
     *  na navigationbar caso o fragment seja mudado.
     */

    @Override
    public void onBackPressed() {

        int i = fm.getBackStackEntryCount()-2; //pega a quantidade de fragments na pilha

        if(i>=0) {
            if (fm.getBackStackEntryAt(i).getName().equals("HomeFragment")) {//testa qual fragment esta na pilha
                navigation.getMenu().getItem(0).setChecked(true);
            } else if (fm.getBackStackEntryAt(i).getName().equals("VisitedFragment")) {
                navigation.getMenu().getItem(1).setChecked(true);
            } else {
                navigation.getMenu().getItem(2).setChecked(true);//adiciona cor a navigationbar no item correspondente ao fragment
            }
        }
        super.onBackPressed();

    }

}
