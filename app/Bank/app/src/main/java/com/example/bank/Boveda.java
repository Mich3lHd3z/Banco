package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Boveda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boveda);
    }

    public void toggleLogBoveda(View view) {
        // Lógica para el botón de abrir y cerrar si es necesario
    }

    public void onRegresarClick(View view) {
        // Lógica para regresar a la actividad anterior o al menú
        finish(); // Cierra esta actividad
    }
}
