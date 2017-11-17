package com.example.gustavobarbosa.gorest.Helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavobarbosa.gorest.DAO.NationDAO;
import com.example.gustavobarbosa.gorest.Model.Nation;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Gustavo Barbosa on 02/11/2017.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "nation.db";
    private static final int VERSION_DB = 2;

    private Dao<Nation,String> nationDao = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    /*
     Cria uma nova tabela
   */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Nation.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Atualiza a tabela do banco
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Nation.class,true);//Deleta a tabela e TRUE(ignora os erros)
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     Pega a Nation no BD através do objeto DAO
   */
    public Dao<Nation,String> getNation() throws SQLException{
        if(nationDao == null)
            nationDao = getDao(Nation.class);

        return nationDao;
    }

    @Override
    public void close(){
        super.close();
    }
}
