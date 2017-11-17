package com.example.gustavobarbosa.gorest.Rest;

import com.example.gustavobarbosa.gorest.Model.Nation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Gustavo Barbosa on 29/10/2017.
 */

public interface InterfaceApi {
    @GET("all?fields=name;flag;capital;area;population")
    Call<List<Nation>> getListNation();
}
