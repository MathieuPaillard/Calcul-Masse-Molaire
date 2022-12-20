package com.helloweed.calculateurmassemolaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button mButtonMasseMolaire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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