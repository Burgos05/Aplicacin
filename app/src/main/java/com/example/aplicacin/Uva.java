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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Uva extends AppCompatActivity {

    Button guardar;
    String vinedo, muestra, fecha;
    TextView variedad, temperatura, cosecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uva);

        guardar = findViewById(R.id.btn_capturar);
        variedad = findViewById(R.id.txt_variedad);
        temperatura = findViewById(R.id.txt_temperatura);
        cosecha = findViewById(R.id.txt_cosecha);
        guardar = findViewById(R.id.btn_capturar);


        Bundle datos = this.getIntent().getExtras();
        vinedo = datos.getString("vinedo");
        muestra = datos.getString("muestra");
        fecha = datos.getString("fecha");

        String mensaje = vinedo + ", " + muestra + ", " + fecha;
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

/*

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EjecutarServicio("http://gruposeya.com/vinos/ingresar.php");
                //info.setText("Nombre: \n"+ vinedo.getText().toString()+"\n"+ "Ubicacion: \n"+ubicacion.getText().toString()+"\nInicio:\n"+inicio.getText().toString());
            }
        });
*/

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

                    parametros.put("variedad", variedad.getText().toString());
                    parametros.put("temperatura", temperatura.getText().toString());
                    parametros.put("cosecha", "cosecha");
                    parametros.put("mosto", "Vmosto");
                    parametros.put("envasado", "envasado");
                    parametros.put("barrica", "barrica");
                    parametros.put("ntoma", "toma");
                    parametros.put("nentrega", "entrega");
                    parametros.put("nrecibe", "recibe");
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    public void Regresar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Siguiente(View view){
        Intent i = new Intent(this, Persona.class);
        i.putExtra("muestra", muestra);
        i.putExtra("vinedo", vinedo);
        i.putExtra("fecha", fecha);
        i.putExtra("variedad", variedad.getText().toString());
        i.putExtra("temperatura", temperatura.getText().toString());
        i.putExtra("cosecha", cosecha.getText().toString());
        startActivity(i);
        }


    /*
    public void insertar(View v){
        String url = "http://www.gruposeya.com/vinos/ingresar.php";
        JSONObject parametros = new JSONObject();
        try {
            parametros.put("nombre", vinedo);
            parametros.put("ubicacion", variedad.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.POST,
                url,
                parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.get("respuesta").toString().equals("Nuevo registro almacenado")){
                                Toast.makeText(Uva.this, "Datos almacenados exitosamente", Toast.LENGTH_LONG).show();
                                variedad.setText("");
                            }
                            else
                                Toast.makeText(Uva.this, "Los datos no se almacenaron", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Uva.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        re.add(requerimiento);
    }
    */
}