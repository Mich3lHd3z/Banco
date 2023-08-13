package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Ultrasonicos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultrasonicos);
    }

    // Agrega aquí los métodos para las funciones onClick
    public void toggleUltraPrincipal(View view) {
        // Lógica para el botón Ultra Principal (Abrir/Cerrar)
    }

    public void toggleUltraPasillo(View view) {
        // Lógica para el botón Ultra Pasillo (Abrir/Cerrar)
    }

    public void onRegresarClick(View view) {
        // Lógica para el botón Regresar
        onBackPressed(); // Esto regresará a la actividad anterior
    }
}
