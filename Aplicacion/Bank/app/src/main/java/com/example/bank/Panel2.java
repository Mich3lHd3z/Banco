package com.example.bank;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Panel2 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        textView = findViewById(R.id.textView);
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String currentText = textView.getText().toString();
        textView.setText(currentText + buttonText);
    }

    public void onClearClick(View view) {
        textView.setText("");
    }

    public void onSendClick(View view) {
        // Lógica para el botón de envío (#) si es necesario
    }

    public void onRegresarClick(View view) {
        finish(); // Finaliza la actividad actual y regresa a la actividad anterior en la pila
    }
}
