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

public class Puertas extends AppCompatActivity {
    private static final String BROKER_URL = "tcp://18.118.188.12";
    private static final String CLIENT_ID = "your_client_id";
    private MqttHandler mqttHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puertas);
    }

    public void onRegresarClick(View view) {
        // Iniciar la actividad del menú principal
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void toggleBovedav1Abrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","P_B_A");
        // Litzy Registro BD

        _Insert("P_B_A"," Puerta Abierta");

    }
    public void toggleBovedav1Cerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","B_C");

        // Litzy Registro BD
        _Insert("B_C","Puerta Cerrada");
    }

    public void togglePuertaAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","P_V1_A");
        // Litzy Registro BD

        _Insert("P_V1_A"," Puerta Abierta");

    }
    public void togglePuertaCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","V1_C");

        // Litzy Registro BD
        _Insert("V1_C","Puerta Cerrada");
    }

    public void togglePuertaV2Abrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","P_V2_A");
        // Litzy Registro BD

        _Insert("P_V2_A"," Puerta Abierta");

    }
    public void togglePuertaV2Cerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","V2_C");

        // Litzy Registro BD
        _Insert("V2_C","Puerta Cerrada");
    }

    public void togglePuertaPAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","P_P_A");
        // Litzy Registro BD

        _Insert("P_P_A"," Puerta Abierta");

    }
    public void togglePuertaPCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","P_C");

        // Litzy Registro BD
        _Insert("P_C","Puerta Cerrada");
    }

    public void togglePuertaSAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","P_S_A");
        // Litzy Registro BD

        _Insert("P_S_A"," Puerta Abierta");

    }
    public void togglePuertaSCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","S_C");

        // Litzy Registro BD
        _Insert("S_C","Puerta Cerrada");
    }
    private void _Insert(final String _Clave,final String _Descripcion) {

        String url = "https://proyectos123tra.000webhostapp.com/Banco/api.php";

        StringRequest postResquest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Puertas.this, "RESULTADO POST = " + response, Toast.LENGTH_LONG).show();
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


