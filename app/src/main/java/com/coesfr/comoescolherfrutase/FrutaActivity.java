package com.coesfr.comoescolherfrutase;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

public class FrutaActivity extends AppCompatActivity {

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruta_activity);

        //setar toolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.fruta_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setBackgroundColor(Color.parseColor("#009004"));

        //setar botao voltar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //setar banner
        AdView mAdView = (AdView) findViewById(R.id.adViewDescription);
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        AdRequest adRequest = adRequestBuilder.addTestDevice(getResources().getString(R.string.device_id)).build();
        mAdView.loadAd(adRequest);

        //receber Fruta da tela principal
        String jsonMyObject;
        Fruta myObject = null;
        savedInstanceState = getIntent().getExtras();
        if (savedInstanceState != null) {
            jsonMyObject = savedInstanceState.getString("fruta");
            myObject = new Gson().fromJson(jsonMyObject, Fruta.class);
        }

        //setando titulo da fruta
        TextView tituloTextView = (TextView) findViewById(R.id.fruta_nome);
        tituloTextView.setText(myObject.getTitleUpCase());
        //setando imagem da fruta
        ImageView imagemImageView = (ImageView) findViewById(R.id.fruta_imagem);
        imagemImageView.setImageDrawable(getResources().getDrawable(myObject.getImage()));
        //setando dica da fruta
        TextView dicaTextView = (TextView) findViewById(R.id.fruta_dica);
        dicaTextView.setText(myObject.getTip());

        // Obtain the shared Tracker instance.
        ComoEscolherFrutas application = (ComoEscolherFrutas) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Descricao")
                .setAction("Entrar")
                .setLabel(myObject.getTitleUpCase())
                .setValue(1)
                .build());
    }
}