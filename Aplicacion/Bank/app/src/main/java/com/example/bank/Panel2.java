package com.example.bank;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Panel2 extends AppCompatActivity {

    private static final String BROKER_URL = "tcp://18.118.188.12";
    private static final String CLIENT_ID = "your_client_id";
    private MqttHandler mqttHandler;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        textView = findViewById(R.id.textView);
    }

   // public void toggleLogBoveda_Cerrar(View view) {

        //subscribeToTopic("Clave");
        // Litzy Registro BD
        //Insert("Clave","Boveda se Cerrar");
    //}


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


    public class P_LOGIN extends AppCompatActivity {

        TextView textView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_panel2);

            Button sendButton = findViewById(R.id.sendButton);
            textView= findViewById(R.id.textView);


            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _Consulta(textView.getText().toString());
                }
            });

        }


        private void _Consulta(final String _Clave) {
            String url = "https://proyectos123tra.000webhostapp.com/Banco/boveda.php?Valor=" + _Clave;

            StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        String rol = jsonObject.getString("Clave");

                        // Verificar si 'rol' no está vacío
                        if (!rol.isEmpty()) {
                            // Si 'rol' no está vacío, puedes realizar las acciones deseadas aquí
                            mqttHandler = new MqttHandler();
                            mqttHandler.connect(BROKER_URL, CLIENT_ID);
                            publishMessage("DEMO", "P_B_A");

                        } else {
                            // Si 'rol' está vacío, realiza otras acciones
                            mqttHandler = new MqttHandler();
                            mqttHandler.connect(BROKER_URL, CLIENT_ID);
                            publishMessage("DEMO","Z_B_ZE");


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error", error.getMessage());
                }
            });

            Volley.newRequestQueue(this).add(postRequest);
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

}
