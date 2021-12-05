package com.example.evaluacion3pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Model.Pizza;

public class Gestion_act extends AppCompatActivity {

    EditText etNomPizza, etPrecioPizza, etLocalPizza;
    Button btnAnadirPizza;

    FirebaseDatabase firebase;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        etNomPizza = findViewById(R.id.etNomPizza);
        etPrecioPizza = findViewById(R.id.etPrecioPizza);
        etLocalPizza = findViewById(R.id.etLocalPizza);
        btnAnadirPizza = findViewById(R.id.btnAnadirPizza);

        InicializarDB();

        btnAnadirPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNomPizza.getText().toString().isEmpty() || etPrecioPizza.getText().toString().isEmpty() || etLocalPizza.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Error, ingrese todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    Pizza p = new Pizza();
                    p.setId(UUID.randomUUID().toString());
                    p.setNombre(etNomPizza.getText().toString());
                    p.setPrecio(etPrecioPizza.getText().toString());
                    p.setLocalizacion(etLocalPizza.getText().toString());

                    dbRef.child("Pizzas").child(p.getId()).setValue(p); //Si no existe la tabla la crea

                    Toast.makeText(getBaseContext(), "Añadió la pizza correctamente", Toast.LENGTH_LONG).show();

                    etNomPizza.setText("");
                    etPrecioPizza.setText("");
                    etLocalPizza.setText("");
                }
            }
        });
    }

    public void InicializarDB(){
        FirebaseApp.initializeApp(this);
        firebase = FirebaseDatabase.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
    }
}