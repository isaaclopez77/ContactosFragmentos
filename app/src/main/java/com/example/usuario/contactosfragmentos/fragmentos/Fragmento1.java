package com.example.usuario.contactosfragmentos.fragmentos;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.usuario.contactosfragmentos.R;
import com.example.usuario.contactosfragmentos.actividades.ActividadPrincipal;
import com.example.usuario.contactosfragmentos.actividades.ActividadSecundaria;
import com.example.usuario.contactosfragmentos.clases.Adaptador;
import com.example.usuario.contactosfragmentos.clases.Contacto;
import com.example.usuario.contactosfragmentos.clases.GetContactos;
import com.example.usuario.contactosfragmentos.util.Dialogo;
import com.example.usuario.contactosfragmentos.util.OnDialogoListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fragmento1 extends Fragment {

    View viewFragment;
    private ArrayList<Contacto> lista ;
    private ListView lv;
    private Adaptador ad;
    private GetContactos gc;
    private ImageView iv;
    private EditText etnom, etnum1, etnum2, etnum3;
    private ArrayList<String> tlfns;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gc=new GetContactos(getActivity());
        lista = gc.getListaContactos();

        viewFragment = inflater.inflate(R.layout.fragmento1, container, false);
        lv=(ListView)viewFragment.findViewById(R.id.lv);
        iv = (ImageView)viewFragment.findViewById(R.id.imageView2);

        //Si ha editado o borrado un contacto
        Intent i = getActivity().getIntent();
        Bundle b = i.getExtras();
        if(b != null) {
            Contacto c = (Contacto) b.getSerializable("valor");

            for(Iterator<Contacto> it = lista.iterator(); it.hasNext();){
                Contacto con = it.next();
                if(c.equals(con)){
                    if (b.getString("modo").equals("editar")){
                        con.set(c);
                        Toast.makeText(getActivity(),"Contacto editado",Toast.LENGTH_SHORT).show();
                    }else if (b.getString("modo").equals("borrar")) {
                        it.remove();
                        Toast.makeText(getActivity(), "Contacto borrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        ad = new Adaptador(getActivity(), R.layout.item,lista);
        lv.setAdapter(ad);
        lv.setTag(lista);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Contacto nuevo = new Contacto();
                OnDialogoListener odl = new OnDialogoListener() {
                    @Override
                    public void onPreShow(View v) {
                        etnom = (EditText) v.findViewById(R.id.etNombreAd);
                        etnum1 = (EditText) v.findViewById(R.id.etNum1Ad);
                        etnum2 = (EditText) v.findViewById(R.id.etNum2Ad);
                        etnum3 = (EditText) v.findViewById(R.id.etNum3Ad);
                        tlfns = new ArrayList<>();
                    }

                    @Override
                    public void onOkSelecter(View v) {
                        nuevo.setNombre(etnom.getText().toString());

                        if (!etnum1.getText().toString().equals("")) {
                            tlfns.add(etnum1.getText().toString());
                        }

                        if (!etnum2.getText().toString().equals("")) {
                            tlfns.add(etnum2.getText().toString());
                        }

                        if (!etnum3.getText().toString().equals("")) {
                            tlfns.add(etnum3.getText().toString());
                        }

                        if (tlfns.size() > 0) {
                            nuevo.setNumeros(tlfns);
                        }

                        lista.add(nuevo);
                        Toast.makeText(getActivity(),"Contacto añadido",Toast.LENGTH_SHORT).show();
                        ad.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelSelecter(View v) {
                        Toast.makeText(getActivity(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                };
                Dialogo d = new Dialogo(getActivity(), R.layout.alta, odl);
                d.setTitulo("Añadir");
                d.show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragmento2 fragmento = (Fragmento2) getFragmentManager().findFragmentById(R.id.fragment3);
                if (fragmento == null || !fragmento.isInLayout()) {
                    Intent i = new Intent(getActivity(), ActividadSecundaria.class);
                    i.putExtra("valor", lista.get(position));
                    startActivity(i);
                } else {
                    fragmento.update(lista.get(position));
                }
            }
        });
        return viewFragment;
    }

}
