package com.example.gustavobarbosa.gorest.Model;

import android.graphics.drawable.Drawable;

import com.example.gustavobarbosa.gorest.DAO.NationDAO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gustavo Barbosa on 29/10/2017.
 */

@DatabaseTable(tableName = Nation.TABLE_NAME, daoClass = NationDAO.class)
public class Nation implements Serializable {

    @Expose //RetroFit irá ignorar
    public static final String TABLE_NAME = "nation";


    @Expose//RetroFit irá ignorar
    public static final String FIELD_NAME = "name";
    @Expose//Retrofit irá ignorar
    public static final String FIELD_DATE = "date";
    @Expose//Retrofit irá ignorar
    public static final String FIELD_CAPITAL = "capital";
    @Expose//Retrofit irá ignorar
    public static final String FIELD_AREA = "area";
    @Expose//Retrofit irá ignorar
    public static final String FIELD_POPULATION = "population";
    @Expose//Retrofit irá ignorar
    public static final String FIELD_FLAG = "flag";

    @SerializedName("name")
    @DatabaseField(columnName = FIELD_NAME,id = true)
    private String name;

    @Expose
    @DatabaseField(columnName = FIELD_DATE)
    private Date date;

    @SerializedName("flag")
    @DatabaseField(columnName = FIELD_FLAG)
    private String flag;

    @SerializedName("capital")
    @DatabaseField(columnName = FIELD_CAPITAL)
    private String capital;

    @SerializedName("area")
    @DatabaseField(columnName = FIELD_AREA)
    private float area;

    @SerializedName("population")
    @DatabaseField(columnName = FIELD_POPULATION)
    private int population;

    public Nation(){
        //ORMLite pede
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }



}
