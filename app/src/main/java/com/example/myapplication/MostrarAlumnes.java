package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MostrarAlumnes extends AppCompatActivity {
    String [] array_noms;
    int [] array_n1;
    int [] array_n2;
    int [] array_n3;
    int [] array_final;
    int contador;
    boolean descFinal=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_alumnes);

        Intent intent = getIntent();
        array_noms = intent.getStringArrayExtra("array_noms");
        array_n1 = intent.getIntArrayExtra("array_n1");
        array_n2 = intent.getIntArrayExtra("array_n2");
        array_n3 = intent.getIntArrayExtra("array_n3");
        array_final = intent.getIntArrayExtra("array_final");
        contador = intent.getIntExtra("contador", 0);



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, array_noms, array_n1, array_n2, array_n3, array_final, contador);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));

        //nomDown

    }
}
