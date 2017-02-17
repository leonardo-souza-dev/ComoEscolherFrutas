package com.coesfr.comoescolherfrutase;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.list)
    ListView listaDeFrutas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurarToolbar();

        configurarBanner();

        configurarListaDeFrutas();
    }

    private void configurarBanner() {
        AdView mAdView = (AdView) findViewById(R.id.adViewMain);
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        AdRequest adRequest = adRequestBuilder.addTestDevice(getResources().getString(R.string.device_id)).build();
        mAdView.loadAd(adRequest);
    }

    private void configurarToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.fruit_main_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setBackgroundColor(Color.parseColor("#009004"));
    }

    private void configurarListaDeFrutas(){

        ButterKnife.bind(this);

        List<Fruta> frutas = getNewsEntries();

        FrutaAdapter adapter = new FrutaAdapter(frutas, this);

        listaDeFrutas.setAdapter(adapter);
    }

    @OnItemClick(R.id.list)
    public void acessarFruta(int position) {
        Fruta fruta = (Fruta) listaDeFrutas.getItemAtPosition(position);

        Intent intent = new Intent(this, FrutaActivity.class);

        Bundle b = new Bundle();
        b.putString("fruta", new Gson().toJson(fruta));
        intent.putExtras(b);

        this.startActivity(intent);
    }

    private List<Fruta> getNewsEntries() {

        final List<Fruta> entries = new ArrayList<Fruta>();

        entries.add(new Fruta("ABACATE", R.drawable.abacate, R.string.tip_abacate));
        entries.add(new Fruta("ABACAXI", R.drawable.abacaxi, R.string.tip_abacaxi));
        entries.add(new Fruta("BANANA", R.drawable.banana, R.string.tip_banana));
        entries.add(new Fruta("GOIABA", R.drawable.goiaba, R.string.tip_goiaba));
        entries.add(new Fruta("LARANJA", R.drawable.laranja, R.string.tip_laranja));
        entries.add(new Fruta("MAMÃO", R.drawable.mamao, R.string.tip_mamao));
        entries.add(new Fruta("MANGA", R.drawable.manga, R.string.tip_manga));
        entries.add(new Fruta("MARACUJÁ", R.drawable.maracuja, R.string.tip_maracuja));
        entries.add(new Fruta("MELANCIA", R.drawable.melancia, R.string.tip_melancia));
        entries.add(new Fruta("PÊRA", R.drawable.pera, R.string.tip_pera));

        return entries;
    }
}