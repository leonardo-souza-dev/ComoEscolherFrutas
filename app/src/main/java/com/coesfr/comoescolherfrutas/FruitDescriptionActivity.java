package com.coesfr.comoescolherfrutas;

import android.content.Intent;
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

public class FruitDescriptionActivity extends AppCompatActivity {

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit_description);

        //setar toolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.fruit_description_toolbar);
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
        FruitEntry myObject = null;
        savedInstanceState = getIntent().getExtras();
        if (savedInstanceState != null) {
            jsonMyObject = savedInstanceState.getString("fruitEntry");
            myObject = new Gson().fromJson(jsonMyObject, FruitEntry.class);
        }

        //setando titulo da fruta
        TextView tituloTextView = (TextView) findViewById(R.id.fruit_title);
        tituloTextView.setText(myObject.getTitleUpCase());
        //setando imagem da fruta
        ImageView imagemImageView = (ImageView) findViewById(R.id.fruit_image);
        imagemImageView.setImageDrawable(getResources().getDrawable(myObject.getImage()));
        //setando dica da fruta
        TextView dicaTextView = (TextView) findViewById(R.id.fruit_tip);
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

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, MainActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FruitDescriptionActivity.this, MainActivity.class);
        FruitDescriptionActivity.this.startActivity(intent);
        finish();
    }
}