package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonPanel = findViewById(R.id.buttonPanel);
        buttonPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPanel();
            }
        });

        Button buttonBanco = findViewById(R.id.buttonBanco);
        buttonBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBanco();
            }
        });

        Button buttonBoveda = findViewById(R.id.buttonBoveda);
        buttonBoveda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBoveda();
            }
        });

        Button buttonUltra = findViewById(R.id.buttonUltra);
        buttonUltra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUltrasonicos();
            }
        });

        Button buttonFocos = findViewById(R.id.buttonFocos);
        buttonFocos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFocos();
            }
        });

        Button buttonPuertas = findViewById(R.id.buttonPuertas);
        buttonPuertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuertas();
            }
        });
    }

    public void openPanel() {
        Intent intent = new Intent(this, Panel.class);
        startActivity(intent);
    }

    public void openBanco() {
        Intent intent = new Intent(this, Banco.class);
        startActivity(intent);
    }

    public void openBoveda() {
        Intent intent = new Intent(this, Boveda.class);
        startActivity(intent);
    }

    public void openUltrasonicos() {
        Intent intent = new Intent(this, Ultrasonicos.class);
        startActivity(intent);
    }

    public void openFocos() {
        Intent intent = new Intent(this, Focos.class);
        startActivity(intent);
    }

    public void openPuertas() {
        Intent intent = new Intent(this, Puertas.class);
        startActivity(intent);
    }

    public void exitApp(View view) {
        finishAffinity(); // Cierra todas las actividades y finaliza la aplicación
    }

}
