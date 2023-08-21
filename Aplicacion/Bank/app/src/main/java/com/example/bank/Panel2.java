package com.example.bank;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

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

                        String rol = jsonObject.getString("Rol");

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


    }

}
