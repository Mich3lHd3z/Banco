package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        // Call the TB() function to populate the Consulta layout
        TB();
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public void onRegresarClick(View view) {
        finish();
    }

    private void TB() {
        String url = "https://proyectos123tra.000webhostapp.com/Banco/api.php"; // URL que devuelve un arreglo de objetos JSON
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            // Consulta para mostrar datos
                            LinearLayout consultaLayout = findViewById(R.id.Consulta);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                TextView textView = new TextView(Banco.this);
                                textView.setText(" | " + jsonObject.getString("Id") +
                                        " | " + jsonObject.getString("Clave") +
                                        " | " + jsonObject.getString("Descripcion") +
                                        " | " + jsonObject.getString("Fecha_hora"));

                                consultaLayout.addView(textView);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getMessage());
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
