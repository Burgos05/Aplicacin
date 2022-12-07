package com.example.aplicacin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String[] vinedos = {"Camino De Vinos", "Viñedo El Lobo", "Viñedo San Miguel", "Viñedo Cuna De Tierra",
    "Viñedo Los Arcángeles", "Bernat VINICOLA", "Bodega Viñedo San Miguel", "Tres Raíces", "Vinícola Toyan",
    "Hacienda San José Lavista", "Viñedo Dos Jacales", "Puente Josefa Viñedo", "Viñedos Pájaro Azul",
    "Viñedos San Lucas", "Viña del Cielo", "Los Remedios Rancho Y Viñedo", "Santísima Trinidad"};

    String[] muestra = {"Uva", "Mosto", "Vino"};

    TextView j_spinner_selected, j_spinner_selected2;
    Spinner j_spinner;
    Spinner j_spinner2;
    TextInputEditText fecha;
    String opcion_muestra, opcion_vinedo, opcion_fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        j_spinner = findViewById(R.id.spinner);
        j_spinner2 = findViewById(R.id.spinner2);
        //j_spinner_selected = findViewById(R.id.txt_seleccion);
        //j_spinner_selected2 = findViewById(R.id.txt_seleccion2);
        fecha = findViewById(R.id.txt_fecha);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vinedos);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, muestra);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        j_spinner.setAdapter(adapter);
        j_spinner2.setAdapter(adapter2);

        //RequestQueue rq = Volley.newRequestQueue(this);

/*
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                EjecutarServicio("http://gruposeya.com/vinos/ingresar.php");
                //info.setText("Nombre: \n"+ vinedo.getText().toString()+"\n"+ "Ubicacion: \n"+ubicacion.getText().toString()+"\nInicio:\n"+inicio.getText().toString());
            }
        });
*/

        // Listbox para el viñedo
        j_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                //Se toma el valor del listBox a una variable del tipo TextView
                opcion_vinedo = j_spinner.getSelectedItem().toString();
                //j_spinner_selected.setText(opcion_vinedo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // Listbox para el tipo de muestra
        j_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                //Se toma el valor del listBox a una variable del tipo TextView
                opcion_muestra = j_spinner2.getSelectedItem().toString();
                //j_spinner_selected2.setText(opcion_muestra);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        }


    public void elegirActividad(View view){
        if(opcion_muestra == "Uva"){
            opcion_fecha = fecha.getText().toString();
            Intent i = new Intent(this, Uva.class);
            i.putExtra("muestra", opcion_muestra);
            i.putExtra("vinedo", opcion_vinedo);
            i.putExtra("fecha", opcion_fecha);
            startActivity(i);
        }
        else if (opcion_muestra == "Mosto"){
            opcion_fecha = fecha.getText().toString();
            Intent i = new Intent(this, Mosto.class);
            i.putExtra("muestra", opcion_muestra);
            i.putExtra("vinedo", opcion_vinedo);
            i.putExtra("fecha", opcion_fecha);
            startActivity(i);
        }
        else{
            opcion_fecha = fecha.getText().toString();
            Intent i = new Intent(this, Vino.class);
            i.putExtra("muestra", opcion_muestra);
            i.putExtra("vinedo", opcion_vinedo);
            i.putExtra("fecha", opcion_fecha);
            startActivity(i);
        }
    }
}