package com.example.usuario.contactosfragmentos.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.usuario.contactosfragmentos.R;

import java.util.ArrayList;
import java.util.Comparator;

public class Adaptador  extends ArrayAdapter<Contacto> {
    private Context ctx;
    private int res;
    private LayoutInflater lInflator;
    private ArrayList<Contacto> valores;


    static class ViewHolder {
        public TextView tvSuperior, tvInferior, telf;
        public ImageView iv;
        public ImageView boton;
        public LinearLayout rlDatos;
    }

    public Adaptador(Context context, int resource, ArrayList<Contacto> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.res = resource;
        this.valores = objects;
        this.lInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gv = new ViewHolder();
        if(convertView==null){
            convertView = lInflator.inflate(res, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tvSuperior);
            gv.tvSuperior = tv;
            tv = (TextView) convertView.findViewById(R.id.tvInferior);
            gv.tvInferior = tv;
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
            gv.iv = iv;

            convertView.setTag(gv);
        } else {
            gv = (ViewHolder) convertView.getTag();
        }

        gv.iv.setTag(position);
        gv.tvSuperior.setText("" + valores.get(position).getNombre());
        gv.tvInferior.setText("" + valores.get(position).getPrimerNumero());

        return convertView;
    }
}
