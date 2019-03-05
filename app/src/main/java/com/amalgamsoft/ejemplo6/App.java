package com.amalgamsoft.ejemplo6;

import android.app.Application;

import com.amalgamsoft.ejemplo6.utilities.DBUtility;

public class App extends Application {
    private DBUtility conn;

    @Override
    public void onCreate() {
        super.onCreate();
        conn = new DBUtility(this);
    }

    public DBUtility getConn() {
        if (conn == null) {
            conn = new DBUtility(this);
        }
        return conn;
    }
}
