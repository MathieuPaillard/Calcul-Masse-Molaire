package com.helloweed.calculateurmassemolaire;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3;

    private Button mButtonMasseMolaire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this,new OnInitializationCompleteListener(){
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);


        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mAdView = findViewById(R.id.adView);
        mAdView.loadAd(adRequest);
        mAdView2 = findViewById(R.id.adView2);
        mAdView2.loadAd(adRequest);
        mAdView3 = findViewById(R.id.adView3);
        mAdView3.loadAd(adRequest);



        mButtonMasseMolaire = findViewById(R.id.button_MASSE_MOLAIRE);
        mButtonMasseMolaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent molaireActivityIntent = new Intent(MainActivity.this, CalculMasseMolaireActivity.class);
                startActivity(molaireActivityIntent);
            }
        });

    }
}