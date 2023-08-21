package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Focos extends AppCompatActivity {
    private static final String BROKER_URL = "tcp://18.118.188.12";
    private static final String CLIENT_ID = "your_client_id";
    private MqttHandler mqttHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focos);
    }

    public void onRegresarClick(View view) {
        finish();
    }

    // Agrega aquí los métodos toggleFocoPrincipal, toggleFocoVentanilla, toggleFocoPasillo, toggleFocoPoste si los tienes implementados


    public void toggleFocoPrincipalAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","F_P_E");
        // Litzy Registro BD
         subscribeToTopic("F_P_E");
        _Insert("F_P_E","Foco Encendidio");

    }
    public void toggleFocoPrincipalCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","F_P_A");
        subscribeToTopic("F_P_A");
        // Litzy Registro BD
        _Insert("F_P_A","Foco Apagado");
    }

    public void toggleFocoVentanillaAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","F_V_E");
        // Litzy Registro BD
        subscribeToTopic("F_V_E");
        _Insert("F_V_E","Foco Encendidio");

    }
    public void toggleFocoVentanillaCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","F_V_A");
        subscribeToTopic("F_V_A");
        // Litzy Registro BD
        _Insert("F_V_A","Foco Apagado");
    }
    public void toggleFocoPasilloAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","F_PL_E");
        // Litzy Registro BD
        subscribeToTopic("F_PL_E");
        _Insert("F_PL_E","Foco Encendidio");

    }
    public void toggleFocoPasilloCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","F_PL_A");
        subscribeToTopic("F_PL_A");
        // Litzy Registro BD
        _Insert("F_PL_A","Foco Apagado");
    }

    public void toggleFocoPosteAbrir(View view) {
        // Esteban Servidor Rasberry
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","F_PO_E");
        // Litzy Registro BD
        subscribeToTopic("F_PO_E");
        _Insert("F_PO_E","Foco Encendidio");

    }
    public void toggleFocoPosteCerrar(View view) {
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
        publishMessage("DEMO","F_PO_A");
        subscribeToTopic("F_PO_A");
        // Litzy Registro BD
        _Insert("F_PO_A","Foco Apagado");
    }


    private void _Insert(final String _Clave, final String _Descripcion) {

        String url = "https://proyectos123tra.000webhostapp.com/Banco/api.php";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("Clave", _Clave);
            jsonBody.put("Descripcion", _Descripcion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String mensaje = response.getString("mensaje");
                            if (mensaje.equals("Registro exitoso")) {
                                // La inserción fue exitosa
                                Toast.makeText(Focos.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                            } else {
                                // Hubo un error en la inserción
                                Toast.makeText(Focos.this, "Error en el registro: " + mensaje, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                });

        Volley.newRequestQueue(this).add(jsonObjectRequest);

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

