package com.example.aplicacin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Vino extends AppCompatActivity {

    Button capturar;
    String vinedo, muestra, fecha;
    TextView fechaEnvasado, tiempo;

    public void Regresar(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vino);

        capturar = findViewById(R.id.btn_capturarVino);
        fechaEnvasado = findViewById(R.id.txt_fechaEnvasado);
        tiempo = findViewById(R.id.txt_tiempo);

        Bundle datos = this.getIntent().getExtras();
        vinedo = datos.getString("vinedo");
        muestra = datos.getString("muestra");
        fecha = datos.getString("fecha");

        String mensaje = vinedo + ", " + muestra + ", " + fecha;
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

        capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EjecutarServicio("http://gruposeya.com/vinos/ingresar.php");
                //info.setText("Nombre: \n"+ vinedo.getText().toString()+"\n"+ "Ubicacion: \n"+ubicacion.getText().toString()+"\nInicio:\n"+inicio.getText().toString());
            }
        });
    }

    private void EjecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre", vinedo);
                parametros.put("ubicacion", "ubicacion");
                parametros.put("inicio", fecha);
                parametros.put("primera", fecha);

                parametros.put("variedad", "variedad");
                parametros.put("temperatura", "temperatura");
                parametros.put("cosecha", "cosecha");
                parametros.put("mosto", "Vmosto");
                parametros.put("envasado", fechaEnvasado.getText().toString());
                parametros.put("barrica", tiempo.getText().toString());
                parametros.put("ntoma", "toma");
                parametros.put("nentrega", "entrega");
                parametros.put("nrecibe", "recibe");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}