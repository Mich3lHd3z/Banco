package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Banco extends AppCompatActivity {

    private ListView logListView;
    private List<String> logItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        logListView = findViewById(R.id.logListView);

        // Configurar Retrofit y obtener datos de la base de datos
        // ...

        // Ejemplo de cómo podrías agregar datos a la lista
        logItems = new ArrayList<>();
        logItems.add("ID\tClave\tDescripción\tFecha y Hora");
        logItems.add("1\tClave1\tDescripción1\t" + getCurrentDateTime());
        logItems.add("2\tClave2\tDescripción2\t" + getCurrentDateTime());
        // Agregar más datos si es necesario

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Banco.this,
                android.R.layout.simple_list_item_1, logItems);

        logListView.setAdapter(adapter);

        logListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String selectedLog = logItems.get(position);
                    Toast.makeText(Banco.this, "Seleccionaste: " + selectedLog, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public void onRegresarClick(View view) {
        finish();
    }
}
