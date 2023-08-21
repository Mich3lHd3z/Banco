package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Ultrasonicos extends AppCompatActivity {
    private static final String BROKER_URL = "tcp://18.118.188.12";
    private static final String CLIENT_ID = "your_client_id";
    private MqttHandler mqttHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultrasonicos);
    }
    public void onRegresarClick(View view) {
        // Iniciar la actividad del menú principal
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
    // Agrega aquí los métodos para las funciones onClick
    public void toggleUltraPrincipalAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","U_P_E");
        // Litzy Registro BD
        subscribeToTopic("U_P_E");
        _Insert("U_P_E"," Ultra Abierta");

    }
    public void toggleUltraPrincipalCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","U_P_A");
        subscribeToTopic("U_P_A");
        // Litzy Registro BD
        _Insert("U_P_A","Ultra Cerrada");
    }
    public void toggleUltraPasilloAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","U_PL_E");
        // Litzy Registro BD
        subscribeToTopic("U_PL_E");
        _Insert("U_PL_E"," Ultra Abierta");

    }
    public void toggleUltraPasilloCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","U_PL_A");
        subscribeToTopic("U_PL_A");
        // Litzy Registro BD
        _Insert("U_PL_A","Ultra Cerrada");
    }
    private void _Insert(final String _Clave,final String _Descripcion) {

        String url = "https://proyectos123tra.000webhostapp.com/Banco/api.php";

        StringRequest postResquest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Ultrasonicos.this, "RESULTADO POST = " + response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Clave", _Clave);
                params.put("Descripcion", _Descripcion);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postResquest);
    }


    @Override
    protected void onDestroy() {
        mqttHandler.disconnect();
        super.onDestroy();

    }
    private void publishMessage(String topic, String message){
        Toast.makeText(this, "Publishing message: " + message, Toast.LENGTH_SHORT).show();
        mqttHandler.publish(topic,message);
    }
    private void subscribeToTopic(String topic){
        Toast.makeText(this, "Subscribing to topic "+ topic, Toast.LENGTH_SHORT).show();
        mqttHandler.subscribe(topic);
    }

}


