package com.amalgamsoft.ejemplo6.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.amalgamsoft.ejemplo6.R;
import com.amalgamsoft.ejemplo6.data.Contacto;

import java.util.List;

public class ContactoAdapter extends ArrayAdapter<Contacto> {
    private Context context;
    private List<Contacto> list;
    private int resource;
    public ContactoAdapter( @NonNull Context context, @NonNull List<Contacto> objects) {
        super(context, R.layout.item_contacto_layout, objects);
        this.context = context;
        this.list = objects;
        this.resource = R.layout.item_contacto_layout;
    }

    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(resource, null);
        }
        if (list.get(position) != null) {
            TextView nombre, telefono;
            nombre = v.findViewById(R.id.txtNombreContacto);
            telefono = v.findViewById(R.id.txtTelefonoContacto);
            nombre.setText(list.get(position).getNombre());
            telefono.setText(list.get(position).getTelefono());
        }
        return v;
    }
}
