package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Focos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focos);
    }

    public void onRegresarClick(View view) {
        finish();
    }

    // Agrega aquí los métodos toggleFocoPrincipal, toggleFocoVentanilla, toggleFocoPasillo, toggleFocoPoste si los tienes implementados
}
