package com.helloweed.calculateurmassemolaire;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.helloweed.calculateurmassemolaire.model.MasseMolaire;

public class CalculMasseMolaireActivity extends AppCompatActivity {
    private TextView mResultMasseMolaireGauche;
    private TextView mResultMasseMolaireMileu;
    private TextView mResultMasseMolaireDroite;
    private EditText mEditText_FORMULE_BRUTE;
    private Button mButton_Add_To_List;
    private Button mButton_Del_List;
    public String liste_des_molécules_avec_leurs_masses;
    public String liste_des_molécules;
    public String liste_des_masses;
    public String liste_des_unités;
    private TextView mTitleMasse;
    public MasseMolaire mMasseMolaire = new MasseMolaire();

    private static final String SHARED_INFO_MOLAIRE = "SHARED_INFO_MOLAIRE";
    private static final String SHARED_INFO_MOLAIRE_LISTE_ENTITES_AVEC_MASSES = "SHARED_INFO_MOLAIRE_LISTE_ENTITES_AVEC_MASSES";
    private static final String SHARED_INFO_MOLAIRE_LISTE_ENTITES = "SHARED_INFO_MOLAIRE_LISTE_ENTITES";
    private static final String SHARED_INFO_MOLAIRE_LISTE_MASSES = "SHARED_INFO_MOLAIRE_LISTE_MASSES";
    private static final String SHARED_INFO_MOLAIRE_LISTE_UNITES = "SHARED_INFO_MOLAIRE_LISTE_UNITES";


    private AdView  mAdView4;
    private AdView  mAdView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_masse_molaire);
// PUB
        MobileAds.initialize(this,new OnInitializationCompleteListener(){
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);


        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        mAdView4 = findViewById(R.id.adView4);
        mAdView4.loadAd(adRequest);
        mAdView5 = findViewById(R.id.adView5);
        mAdView5.loadAd(adRequest);

// PUB


















        mTitleMasse = findViewById(R.id.textViewTitle_Masse_Molaire);
        Button mbutton_Menu = findViewById(R.id.button_Exit_MASSE_MOLAIRE);
        mButton_Add_To_List = findViewById(R.id.button_AJOUTER_A_LA_Liste);
        mButton_Del_List = findViewById(R.id.button_SUPPRIMER_LA_Liste);
        mResultMasseMolaireGauche = findViewById(R.id.textView_Masse_Molaire_Gauche);
        mResultMasseMolaireMileu = findViewById(R.id.textView_Masse_Molaire_Milieu);
        mResultMasseMolaireDroite = findViewById(R.id.textView_Masse_Molaire_Droite);
        mEditText_FORMULE_BRUTE = findViewById(R.id.editText_FORMULE_BRUTE);
        mTitleMasse = findViewById(R.id.textViewTitle_Masse_Molaire);
        mMasseMolaire.initialisationTableau();
        mTitleMasse.setBackgroundColor(0xFF2841DF);
        mTitleMasse.setText("En attente de saisie");
        liste_des_molécules_avec_leurs_masses = getSharedPreferences(SHARED_INFO_MOLAIRE, MODE_PRIVATE).getString(SHARED_INFO_MOLAIRE_LISTE_ENTITES_AVEC_MASSES, "Aucune Info");
        liste_des_molécules = getSharedPreferences(SHARED_INFO_MOLAIRE, MODE_PRIVATE).getString(SHARED_INFO_MOLAIRE_LISTE_ENTITES, "");
        liste_des_masses = getSharedPreferences(SHARED_INFO_MOLAIRE, MODE_PRIVATE).getString(SHARED_INFO_MOLAIRE_LISTE_MASSES, "");
        liste_des_unités = getSharedPreferences(SHARED_INFO_MOLAIRE, MODE_PRIVATE).getString(SHARED_INFO_MOLAIRE_LISTE_UNITES, "");
        mResultMasseMolaireGauche.setText(liste_des_molécules);
        mResultMasseMolaireMileu.setText(liste_des_masses);
        mResultMasseMolaireDroite.setText(liste_des_unités);

        mEditText_FORMULE_BRUTE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mMasseMolaire.setReinitialisation();
                mTitleMasse.setText("La masse molaire de " + mEditText_FORMULE_BRUTE.getText().toString() + " est de " + mMasseMolaire.calculMasseMolaire(mEditText_FORMULE_BRUTE.getText().toString(), "") + " g/mol");
                mResultMasseMolaireGauche.setText("Entité                   " + mMasseMolaire.quelsAtomes(mEditText_FORMULE_BRUTE.getText().toString() + "\n" + liste_des_molécules));
                mResultMasseMolaireMileu.setText("Masse molaire " + mMasseMolaire.quelsMasses(mEditText_FORMULE_BRUTE.getText().toString() + "\n" + liste_des_masses));
                mResultMasseMolaireDroite.setText("Unité " + mMasseMolaire.quellesUnites(mEditText_FORMULE_BRUTE.getText().toString() + "\n" + liste_des_unités));
                if (mEditText_FORMULE_BRUTE.getText().toString().equals("")) {
                    mTitleMasse.setBackgroundColor(0xFF1530DA);
                    mTitleMasse.setText("En attente de saisie");
                    mResultMasseMolaireGauche.setText("Entité                   \n" + liste_des_molécules);
                    mResultMasseMolaireMileu.setText("Masse molaire \n" + liste_des_masses);
                    mResultMasseMolaireDroite.setText("Unité \n" + liste_des_unités);
                    mEditText_FORMULE_BRUTE.setHint("Veuillez saisir une formule brute");
                } else {
                    mTitleMasse.setBackgroundColor(0xFF4C9E05);
                }
            }
        });

        mbutton_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences(SHARED_INFO_MOLAIRE, MODE_PRIVATE)
                        .edit()
                        .putString(SHARED_INFO_MOLAIRE_LISTE_ENTITES, liste_des_molécules)
                        .putString(SHARED_INFO_MOLAIRE_LISTE_MASSES, liste_des_masses)
                        .putString(SHARED_INFO_MOLAIRE_LISTE_UNITES, liste_des_unités)
                        .putString(SHARED_INFO_MOLAIRE_LISTE_ENTITES_AVEC_MASSES, liste_des_molécules_avec_leurs_masses)
                        .apply();


                finish();
            }
        });

        mButton_Add_To_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleMasse.setHeight(0);
                if (mEditText_FORMULE_BRUTE.getText().toString().equals("")) {
                } else {

                    liste_des_molécules_avec_leurs_masses = liste_des_molécules_avec_leurs_masses + mEditText_FORMULE_BRUTE.getText().toString() + " " + mMasseMolaire.calculMasseMolaire(mEditText_FORMULE_BRUTE.getText().toString(), "") + " g/mol" + "\n";
                    liste_des_molécules =  mEditText_FORMULE_BRUTE.getText().toString() + "\n" + liste_des_molécules + "\n";
                    liste_des_masses =  mMasseMolaire.calculMasseMolaire(mEditText_FORMULE_BRUTE.getText().toString(), "") + "\n" + liste_des_masses + "\n";
                    liste_des_unités =  "g/mol" + "\n" + liste_des_unités + "\n";
                    mTitleMasse.setText("La masse molaire de " + mEditText_FORMULE_BRUTE.getText().toString() + " est de " + mMasseMolaire.calculMasseMolaire(mEditText_FORMULE_BRUTE.getText().toString(), "") + " g/mol");
                    mResultMasseMolaireGauche.setText(liste_des_molécules);
                    mResultMasseMolaireMileu.setText(liste_des_masses);
                    mResultMasseMolaireDroite.setText(liste_des_unités);
                    mEditText_FORMULE_BRUTE.setText("");
                }
            }
        });

        mButton_Del_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liste_des_molécules_avec_leurs_masses = "";
                liste_des_molécules = "";
                liste_des_unités = "";
                liste_des_masses = "";
                mResultMasseMolaireGauche.setText("Entité                   " + mMasseMolaire.quelsAtomes(mEditText_FORMULE_BRUTE.getText().toString()));
                mResultMasseMolaireMileu.setText("Masse molaire " + mMasseMolaire.quelsMasses(mEditText_FORMULE_BRUTE.getText().toString()));
                mResultMasseMolaireDroite.setText("Unité " + mMasseMolaire.quellesUnites(mEditText_FORMULE_BRUTE.getText().toString()));
                mTitleMasse.setText("La masse molaire de " + mEditText_FORMULE_BRUTE.getText().toString() + " est de " + mMasseMolaire.calculMasseMolaire(mEditText_FORMULE_BRUTE.getText().toString(), "") + " g/mol");
                if (mEditText_FORMULE_BRUTE.getText().toString().equals("")) {
                    mTitleMasse.setBackgroundColor(0xFF2841DF);
                    mTitleMasse.setText("En attente de saisie");
                    mEditText_FORMULE_BRUTE.setHint("Veuillez saisir une formule brute");
                } else {
                    mTitleMasse.setBackgroundColor(0x5010FF15);
                }

            }
        });

    }
}