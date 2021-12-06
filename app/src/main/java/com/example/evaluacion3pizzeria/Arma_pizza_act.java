package com.example.evaluacion3pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

import Objetos.CalculosPizza;

public class Arma_pizza_act extends AppCompatActivity {
    private Spinner spnPizza, spnIngre;
    private TextView tvTotalSuma;
    private CalculosPizza calculosPizza = new CalculosPizza();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arma_pizza);

        spnPizza = findViewById(R.id.spnPizza);
        spnIngre = findViewById(R.id.spnIngre);
        tvTotalSuma = findViewById(R.id.tvTotalSuma);

        ArrayAdapter adapterPizzas = new ArrayAdapter(this, android.R.layout.simple_list_item_1, calculosPizza.getTipos());
        ArrayAdapter adapterIngre = new ArrayAdapter(this, android.R.layout.simple_list_item_1, calculosPizza.getIngr());

        spnPizza.setAdapter(adapterPizzas);
        spnIngre.setAdapter(adapterIngre);
    }

    public void CalcularEntrega(View view){
        String pizzaSelec = spnPizza.getSelectedItem().toString();
        String ingreSelec = spnIngre.getSelectedItem().toString();
        String resultado = "$" + String.valueOf(calculosPizza.SumaPizzaIngre(pizzaSelec, ingreSelec)); //Si el int no se pasa a String genera error
        tvTotalSuma.setText(resultado);
    }
}