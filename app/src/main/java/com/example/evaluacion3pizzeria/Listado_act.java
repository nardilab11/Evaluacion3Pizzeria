package com.example.evaluacion3pizzeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Pizza;

public class Listado_act extends AppCompatActivity {

    private ListView lvListaPizzas;
    private ArrayList<Pizza> listaPizzasArrayList = new ArrayList();

    FirebaseDatabase firebase;
    DatabaseReference dbRef;

    Pizza pizzaSelec = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lvListaPizzas = findViewById(R.id.lvListaPizzas);

        InicializarDB();

        lvListaPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pizzaSelec = (Pizza)adapterView.getItemAtPosition(i);
            }
        });

        dbRef.child("Pizzas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //Snapshot es el evento/entidad
                for(DataSnapshot op : snapshot.getChildren()){ //Children serían todos los objetos del tipo del snapshot
                    Pizza p = op.getValue(Pizza.class); //El objeto tiene toda la consulta
                    listaPizzasArrayList.add(p); //Se añaden todos los objetos de la consulta
                    ArrayAdapter adapterListaPizzas = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, listaPizzasArrayList);
                    lvListaPizzas.setAdapter(adapterListaPizzas);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void InicializarDB(){
        FirebaseApp.initializeApp(this);
        firebase = FirebaseDatabase.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    public void EliminarPizza(View view){
        if(pizzaSelec == null){
            Toast.makeText(getBaseContext(), "Error, seleccione primero la pizza a eliminar", Toast.LENGTH_LONG).show();
        }else{
            Pizza p = new Pizza();
            p.setId(pizzaSelec.getId());
            pizzaSelec = null;
            dbRef.child("Pizzas").child(p.getId()).removeValue();

            Toast.makeText(getBaseContext(), "Se eliminó correctamente", Toast.LENGTH_LONG).show();

            //Limpiar la lista: mientras haya datos siempre entra al Listener, por lo que el
            // setAdapter no es suficiente (siempre setea la lista que no se ha vaciado, por eso
            // se usa el clear() en la lista, pero si no hay datos (por ejemplo cuando se elimina el
            // último elemento) no entra al listener, por lo que no setea la lista vacía, por lo que
            // es necesario hacer el setAdapter(null)
            listaPizzasArrayList.clear();
            lvListaPizzas.setAdapter(null);
        }
    }
}