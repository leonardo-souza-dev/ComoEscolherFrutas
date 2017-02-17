package com.coesfr.comoescolherfrutase;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public  class FrutaAdapter extends BaseAdapter {

    private final List<Fruta> frutas;
    private final Activity activity;

    public FrutaAdapter(List<Fruta> frutas, Activity activity) {
        this.frutas = frutas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return frutas.size();
    }

    @Override
    public Object getItem(int position) {
        return frutas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.fruit_entry_list_item, parent, false);

        Fruta fruta = frutas.get(position);

        TextView nome = (TextView) view.findViewById(R.id.nome_fruta);
        ImageView imagem = (ImageView) view.findViewById(R.id.imagem_fruta);

        nome.setText(fruta.getTitleUpCase());
        imagem.setImageResource(fruta.getImage());

        return view;
    }
}