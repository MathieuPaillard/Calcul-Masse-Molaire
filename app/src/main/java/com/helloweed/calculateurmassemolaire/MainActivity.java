package com.helloweed.calculateurmassemolaire;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    //private AdView mAdView2;



    //pub intercitielle debut

    // pub intercitielle fin

   /* private AdView mAdView2;
    private AdView mAdView3;*/

    private Button mButtonMasseMolaire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });




        // PUB BANNER
        //Banner 1
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = new AdView(MainActivity.this);
        adView.setAdSize(AdSize.LARGE_BANNER);
        //ca-app-pub-3940256099942544~3347511713 exemple
        //ca-app-pub-3660114368289468/7900167598 real ID

        adView.setAdUnitId("ca-app-pub-3660114368289468/7900167598");
        mAdView = findViewById(R.id.adView);
        mAdView.loadAd(adRequest);


/*        //Benner 2
        AdRequest adRequest2 = new AdRequest.Builder().build();
        AdView adView2 = new AdView(MainActivity.this);
        adView2.setAdSize(AdSize.BANNER);
        adView2.setAdUnitId("ca-app-pub-3660114368289468/2588131166");
        mAdView2 = findViewById(R.id.adView2);
        mAdView2.loadAd(adRequest2);
*/












// PUB BANNER
// PUB Intersitiel
        // Create the "retry" button, which tries to show an interstitial between game plays.


        mButtonMasseMolaire = findViewById(R.id.button_MASSE_MOLAIRE);
        mButtonMasseMolaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent molaireActivityIntent = new Intent(MainActivity.this, CalculMasseMolaireActivity.class);
                startActivity(molaireActivityIntent);
            }
        });


    }
// PUB Intersitiel





}