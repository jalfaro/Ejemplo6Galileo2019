package com.amalgamsoft.ejemplo6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.amalgamsoft.ejemplo6.adapters.ContactoAdapter;
import com.amalgamsoft.ejemplo6.data.Contacto;
import com.amalgamsoft.ejemplo6.utilities.DBUtility;

import java.util.List;

public class ListContactosActivity extends AppCompatActivity {
    private DBUtility conn;
    private ListView listContactos;
    private List<Contacto> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contatos_layout);
        conn = ((App)getApplication()).getConn();
        list = conn.getAllContacts();
        listContactos = findViewById(R.id.lst_contacto);
        if (list != null) {
            listContactos.setAdapter(new ContactoAdapter(this, list));
        }
    }
}
