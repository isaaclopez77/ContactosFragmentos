package com.example.borja.agendaconfragmentos.fragmentos;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.borja.agendaconfragmentos.actividades.ActividadPrincipal;
import com.example.borja.agendaconfragmentos.clases.Adaptador;
import com.example.borja.agendaconfragmentos.clases.Contacto;
import com.example.borja.agendaconfragmentos.R;
import com.example.borja.agendaconfragmentos.actividades.ActividadSecundaria;
import com.example.borja.agendaconfragmentos.clases.GetContactos;

import java.util.ArrayList;

public class Fragmento1 extends Fragment {

    View viewFragment;
    private ArrayList<Contacto> lista ;
    private ListView lv;
    private Adaptador ad;
    private GetContactos gc;

    private OnFragmentoInteraccionListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gc=new GetContactos(getActivity());

        lista = gc.getListaContactos();

        viewFragment = inflater.inflate(R.layout.fragmento1, container, false);

        lv=(ListView)viewFragment.findViewById(R.id.lv);

        ad = new Adaptador(getActivity(), R.layout.item,lista);
        lv.setAdapter(ad);
        lv.setTag(lista);

        //------

        Intent i = new Intent(getActivity(), ActividadSecundaria.class);
        i.putExtra("lista", sacarNumeros(lista.get(0).getNumero()));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ActividadSecundaria.class);

                i.putExtra("lista", sacarNumeros(lista.get(position).getNumero()));
                i.putExtra("title", lista.get(position).getNombre());
                startActivity(i);
            }
        });

        return viewFragment;
    }
    public String sacarNumeros(ArrayList<String> lista){
        String x="";

        for (int i=0; i<lista.size();i++){
            x=x+lista.get(i)+"\n";

        }
        return x;
    }

    public interface OnFragmentoInteraccionListener {
        void onFragmentoInteraccion(Contacto c);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentoInteraccionListener){
            listener = (OnFragmentoInteraccionListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }
    }


}
