package com.coesfr.comoescolherfrutas;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static Activity fa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fa = this;

        //setar toolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.fruit_main_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setBackgroundColor(Color.parseColor("#009004"));

        //setar banner
        AdView mAdView = (AdView) findViewById(R.id.adViewMain);
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        AdRequest adRequest = adRequestBuilder.addTestDevice(getResources().getString(R.string.device_id)).build();
        mAdView.loadAd(adRequest);

        setListView();
    }

    private void setListView(){

        final ListView newsEntryListView = (ListView) findViewById(R.id.list);
        final FruitEntryAdapter fruitEntryAdapter = new FruitEntryAdapter(this, R.layout.fruit_entry_list_item);
        newsEntryListView.setAdapter(fruitEntryAdapter);
        // Populate the list, through the adapter
        for(final FruitEntry entry : getNewsEntries()) {
            fruitEntryAdapter.add(entry);
        }
    }

    private List<FruitEntry> getNewsEntries() {
        // Normally this would come from some asynchronous fetch into a data source
        // such as a sqlite database, or an HTTP request
        final List<FruitEntry> entries = new ArrayList<FruitEntry>();

        entries.add(new FruitEntry("ABACAXI", R.drawable.abacaxi, R.string.tip_abacaxi));
        entries.add(new FruitEntry("BANANA", R.drawable.banana, R.string.tip_banana));
        entries.add(new FruitEntry("LARANJA", R.drawable.laranja, R.string.tip_laranja));
        entries.add(new FruitEntry("MAMÃO", R.drawable.mamao, R.string.tip_mamao));
        entries.add(new FruitEntry("MANGA", R.drawable.manga, R.string.tip_manga));
        entries.add(new FruitEntry("MARACUJÁ", R.drawable.maracuja, R.string.tip_maracuja));
        entries.add(new FruitEntry("MELANCIA", R.drawable.melancia, R.string.tip_melancia));
        entries.add(new FruitEntry("PÊRA", R.drawable.pera, R.string.tip_pera));
        return entries;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}