package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    String [] array_noms = new String [50];
    int [] array_n1=new int[50];
    int [] array_n2=new int[50];
    int [] array_n3=new int[50];
    float media;
    int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView btnBuidar = findViewById(R.id.imgBuida);
        btnBuidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int aux=contador;
                for(int i=0; i<aux; i++)
                {
                    array_noms[i]="";
                }
                contador=0;
                missatgeBuidar();
            }
        });

        final ImageView btnAfegir = findViewById(R.id.imgAfegir);
        btnAfegir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAfegirUsuari();
            }
        });

        final ImageView btnMostrar = findViewById(R.id.imgMostrar);
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToLlistarUsuaris();
            }
        });


        final ImageView btnAprovar = findViewById(R.id.imgAprovar);
        btnAprovar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAprovaTothom();
                Context context = getApplicationContext();
                if(contador>0)
                    Toast.makeText(context, "Ara tots els alumnes estan aprovats!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "Encara no tens cap alumne a la llista!", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView btnModificar = findViewById(R.id.imgModificar);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToModificarAlumne();
            }
        });

        final ImageView btnAjuda = findViewById(R.id.imgAjuda);
        btnAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAjudar();
            }
        });

}

    private void goToAfegirUsuari(){

        Intent anarAct2 = new Intent(this, DisplayMessageActivity.class);
        anarAct2.putExtra("array_noms", array_noms);
        anarAct2.putExtra("contador", contador);
        anarAct2.putExtra("array_n1", array_n1);
        anarAct2.putExtra("array_n2", array_n2);
        anarAct2.putExtra("array_n3", array_n3);

        startActivityForResult(anarAct2, 1);
    }

    public void missatgeBuidar(){
        Toast.makeText(this, "S'han esborrat tots els alumnes!", Toast.LENGTH_SHORT).show();
    }

    private void goToAprovaTothom() {
        for (int i = 0; i < contador; i++) {
            if (array_n1[i] < 5)
                array_n1[i] = 5;

            if (array_n2[i] < 5)
                array_n2[i] = 5;

            if (array_n3[i] < 5)
                array_n3[i] = 5;
        }
    }


    private void goToAjudar(){
        Intent ajudar = new Intent(this, Ajuda.class);
        startActivity(ajudar);
    }
    private void goToLlistarUsuaris(){

        Intent llistarUsuaris = new Intent(this, MostrarAlumnes.class);
        llistarUsuaris.putExtra("array_noms", array_noms);
        llistarUsuaris.putExtra("contador", contador);
        llistarUsuaris.putExtra("array_n1", array_n1);
        llistarUsuaris.putExtra("array_n2", array_n2);
        llistarUsuaris.putExtra("array_n3", array_n3);

        startActivity(llistarUsuaris);
    }

    private void goToModificarAlumne(){
        Intent modificarAlumne = new Intent(this, ModificarAlumne.class);
        modificarAlumne.putExtra("array_noms", array_noms);
        modificarAlumne.putExtra("contador", contador);
        modificarAlumne.putExtra("array_n1", array_n1);
        modificarAlumne.putExtra("array_n2", array_n2);
        modificarAlumne.putExtra("array_n3", array_n3);

        startActivityForResult(modificarAlumne, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                System.out.println("result: ");
                array_noms = data.getStringArrayExtra("array_noms");
                array_n1 = data.getIntArrayExtra("array_n1");
                array_n2 = data.getIntArrayExtra("array_n2");
                array_n3 = data.getIntArrayExtra("array_n3");
                contador = data.getIntExtra("contador", 0);

            }
        }

    }

}
