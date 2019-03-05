package com.amalgamsoft.ejemplo6.utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amalgamsoft.ejemplo6.data.Contacto;

import java.util.ArrayList;
import java.util.List;

public class DBUtility {
    public static final String DB_NAME= "contactos.db";
    public static final int DB_VERSION = 1;
    private DBHelper conn;
    private Context context;

    public DBUtility(Context context) {
        this.conn = new DBHelper(context);
        this.context = context;
    }

    public void insertContato (Contacto contacto) {
        String query = "INSERT INTO contacto (nombre, telefono, sexo) VALUES ('" + contacto.getNombre()
                + "','" + contacto.getTelefono() + "'," + contacto.getSexo() + ")";
        Log.d("QUERY", query);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL(query);
    }

    public List<Contacto> getAllContacts() {
        List<Contacto> list = null;
        Contacto temp;
        String query = "SELECT id, nombre, telefono, sexo FROM contacto";
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            list = new ArrayList<Contacto>();
            while (!c.isAfterLast()) {
               temp = new Contacto();
               temp.setNombre(c.getString(c.getColumnIndex("nombre")));
               temp.setTelefono(c.getString(c.getColumnIndex("telefono")));
               temp.setSexo(c.getInt(c.getColumnIndex("sexo")));
               temp.setId(c.getInt(c.getColumnIndex("id")));
               list.add(temp);
               c.moveToNext();
            }
            c.close();
        }
        return list;
    }






    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper( @Nullable Context context) {
            super(context,DB_NAME, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE contacto ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +  "nombre TEXT ,"
                    + "telefono TEXT,"
                    + "sexo INTEGER )";
            db.execSQL(query);
            /***
            query = " INSERT llslld";
            db.execSQL(query);
             */
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
