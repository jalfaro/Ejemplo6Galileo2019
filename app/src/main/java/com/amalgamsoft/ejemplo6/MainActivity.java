package com.amalgamsoft.ejemplo6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amalgamsoft.ejemplo6.data.Contacto;
import com.amalgamsoft.ejemplo6.utilities.DBUtility;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGrabar, btnVer;
    private EditText txtNombre;
    private EditText txtTelefon;
    private RadioGroup rgSexo;
    private DBUtility conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGrabar = findViewById(R.id.btnGrabar);
        txtNombre = findViewById(R.id.etNombre);
        txtTelefon = findViewById(R.id.etTelefono);
        rgSexo = findViewById(R.id.rgSexo);
        btnGrabar.setOnClickListener(this);
        btnVer = findViewById(R.id.btnVer);
        btnVer.setOnClickListener(this);
    }

    private void cleanScreen() {
        txtTelefon.setText("");
        txtNombre.setText("");
        ((RadioButton)rgSexo.getChildAt(0)).setChecked(true);
        txtNombre.requestFocus();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGrabar) {
            Contacto contacto = new Contacto();
            String nombre = txtNombre.getText().toString();
            String telefono = txtTelefon.getText().toString();
            int sexo = ((RadioButton)rgSexo.getChildAt(0)).isChecked()?0:1;//Masculino - 0 y femenino 1
            conn = ((App)getApplication()).getConn();
            contacto.setNombre(nombre);
            contacto.setSexo(sexo);
            contacto.setTelefono(telefono);
            conn.insertContato(contacto);
            cleanScreen();

        } else if (v.getId() == R.id.btnVer) {
            Intent intent = new Intent(this, ListContactosActivity.class);
            startActivity(intent);
        }
    }
}
