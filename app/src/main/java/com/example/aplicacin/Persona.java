package com.example.aplicacin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Persona extends AppCompatActivity {

    Button guardar;
    TextView toma, entrega, recibe;
    String vinedo, muestra, fecha, variedad, temperatura, cosecha;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        //guardar = findViewById(R.id.btn_capturar2);

        Bundle datos = this.getIntent().getExtras();
        vinedo = datos.getString("vinedo");
        muestra = datos.getString("muestra");
        fecha = datos.getString("fecha");
        variedad = datos.getString("variedad");
        temperatura = datos.getString("temperatura");
        cosecha = datos.getString("cosecha");

        String mensaje = vinedo + ", " + muestra + ", " + fecha + ", " + variedad + ", " + temperatura
                + ", " + cosecha;
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();


        //guardar.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
             //   EjecutarServicio("http://gruposeya.com/vinos/ingresar.php");
                //info.setText("Nombre: \n"+ vinedo.getText().toString()+"\n"+ "Ubicacion: \n"+ubicacion.getText().toString()+"\nInicio:\n"+inicio.getText().toString());
         //   }
       // });

        Button takePicture = findViewById(R.id.btn_capturar2);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 123);
            }
        });
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 123)
        {
            Bitmap imagen = (Bitmap) data.getExtras().get("data");
            ImageView img = findViewById(R.id.iv_foto);
            img.setImageBitmap(imagen);
            try{
                FileOutputStream fos = openFileOutput(crearNombreArchivoJPG(), Context.MODE_PRIVATE);
                imagen.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
            }
            catch(Exception e){
            }
        }
    }


    private String crearNombreArchivoJPG() {
       String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
       return fecha+".jpg";
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
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre", "vinedo");
                parametros.put("ubicacion", "ubicacion");
                parametros.put("inicio", "fecha");
                parametros.put("primera", "fecha");

                parametros.put("variedad", "variedad");
                parametros.put("temperatura", "temperatura");
                parametros.put("cosecha", "cosecha");
                parametros.put("mosto", "Vmosto");
                parametros.put("envasado", "envasado");
                parametros.put("barrica", "barrica");
                parametros.put("ntoma", "toma.getText().toString()");
                parametros.put("nentrega", "entrega.getText().toString()");
                parametros.put("nrecibe", "recibe.getText().toString()");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    public void Regresar(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
