package com.example.gustavobarbosa.gorest.DAO;

import com.example.gustavobarbosa.gorest.Model.Nation;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Gustavo Barbosa on 02/11/2017.
 */

public class NationDAO extends BaseDaoImpl<Nation,String> {

    protected NationDAO(ConnectionSource cs) throws SQLException {
        super(Nation.class);
        setConnectionSource(cs);
        initialize();
    }
}
