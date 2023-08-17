package com.example.bank;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;
import java.util.*;
import android.widget.ArrayAdapter;


import java.util.HashMap;
import java.util.Map;


public class Boveda extends AppCompatActivity {


    private static final String BROKER_URL = "tcp://18.118.188.12";
    private static final String CLIENT_ID = "your_client_id";
    private MqttHandler mqttHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boveda);
    }


    public void toggleLogBoveda_Abrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","Abrir");
        // Litzy Registro BD
_Insert("I_IOT_A","Boveda se Abre");

    }
    public void toggleLogBoveda_Cerrar(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","Abrir");
        // Litzy Registro BD
        _Insert("I_IOT_E","Boveda se Abre");
    }


    private void _Insert(final String _Clave,final String _Descripcion) {
        // Esteban Servidor Rasberry
//        mqttHandler = new MqttHandler();
//        mqttHandler.connect(BROKER_URL,CLIENT_ID);
//        publishMessage("DEMO",_Clave);


        String url = "https://proyectos123tra.000webhostapp.com/Banco/api.php";

        StringRequest postResquest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Boveda.this, "RESULTADO POST = " + response, Toast.LENGTH_LONG).show();
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