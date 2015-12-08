package com.example.borja.agendaconfragmentos.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.borja.agendaconfragmentos.R;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by borja on 14/10/2015.
 */
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
        this.ctx = context;//actividad
        this.res = resource;//layout del item
        this.valores = objects;//lista de valores
        this.lInflator = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    public boolean borrar(int position) {
        try {
            valores.remove(position);
            this.notifyDataSetChanged();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1
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

    public Comparator<Contacto> StringAscComparator = new Comparator<Contacto>() {

        @Override
        public int compare(Contacto c1, Contacto c2) {
            if(c1.getNombre().compareTo(c2.getNombre())>0)
                return 1;
            if(c1.getNombre().compareTo(c2.getNombre())<0)
                return -1;
            return 0;
        }
    };

    //Comparator para orden descendiente
    public Comparator<Contacto> StringDescComparator = new Comparator<Contacto>() {

        @Override
        public int compare(Contacto c1, Contacto c2) {
            if(c1.getNombre().compareTo(c2.getNombre())<0)
                return 1;
            if(c1.getNombre().compareTo(c2.getNombre())>0)
                return -1;
            return 0;
        }
    };

    public String sacarNumeros(ArrayList <String> lista){
        String x="";
        for (int i=0; i<lista.size();i++){
            x=x+lista.get(i)+"\n";
        }
        return x;
    }
}
