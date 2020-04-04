package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity {

    String [] array_noms;
    int [] array_n1;
    int [] array_n2;
    int [] array_n3;
    int contador;
    EditText not1, not2, not3, nom;
    private Button desar;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        nom = findViewById(R.id.form_txt_nom);
        not1 = findViewById(R.id.form_txt_n1);
        not2 = findViewById(R.id.form_txt_n2);
        not3 = findViewById(R.id.form_txt_n3);


        Intent intent = getIntent();
        array_noms = intent.getStringArrayExtra("array_noms");
        array_n1 = intent.getIntArrayExtra("array_n1");
        array_n2 = intent.getIntArrayExtra("array_n2");
        array_n3 = intent.getIntArrayExtra("array_n3");
        contador = intent.getIntExtra("contador", 0);



        final Button button = findViewById(R.id.btnDesar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean desa_ok = afegir(v);
                if (desa_ok) {
                    EditText form_txt_nom = findViewById(R.id.form_txt_nom);
                    EditText form_txt_n1 = findViewById(R.id.form_txt_n1);
                    EditText form_txt_n2 = findViewById(R.id.form_txt_n2);
                    EditText form_txt_n3 = findViewById(R.id.form_txt_n3);

                    String nom = form_txt_nom.getText().toString();
                    int n1 = Integer.parseInt(form_txt_n1.getText().toString());
                    int n2 = Integer.parseInt(form_txt_n2.getText().toString());
                    int n3 = Integer.parseInt(form_txt_n3.getText().toString());

                    array_noms[contador] = nom;
                    array_n1[contador] = n1;
                    array_n2[contador] = n2;
                    array_n3[contador] = n3;
                    contador++;

                    form_txt_nom.setText("");
                    form_txt_n1.setText("");
                    form_txt_n2.setText("");
                    form_txt_n3.setText("");


                }
            }
        });
    }


    public boolean afegir(View v){
        if(validarInput())
        {
            Toast.makeText(this, "La informació s'ha desat correctament!", Toast.LENGTH_SHORT).show();
        }
        return validarInput();
    }

    public boolean validarInput(){
        boolean ok=true;
        String n1, n2, n3, nom_inpt;
        n1=not1.getText().toString();
        n2=not2.getText().toString();
        n3=not3.getText().toString();
        nom_inpt=nom.getText().toString();

        if(nom_inpt.isEmpty())
        {
            nom.setError("Camp buit!");
            ok=false;
        }

        if(n1.isEmpty())
        {
            not1.setError("Camp buit!");
            ok=false;
        }

        else if(Integer.parseInt(n1)<0 || Integer.parseInt(n1)>10 )
        {
            not1.setError("Nota incorrecta!");
            ok=false;
        }

        if(n2.isEmpty())
        {
            not2.setError("Camp buit!");
            ok=false;
        }

        else if(Integer.parseInt(n2)<0 || Integer.parseInt(n2)>10 )
        {
            not2.setError("Nota incorrecta!");
            ok=false;
        }


        if(n3.isEmpty())
        {
            not3.setError("Camp buit!");
            ok=false;
        }

       else if(Integer.parseInt(n3)<0 || Integer.parseInt(n3)>10 )
        {
            not3.setError("Nota incorrecta!");
            ok=false;
        }
        return ok;
    }

    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();

        returnIntent.putExtra("array_noms", array_noms);
        returnIntent.putExtra("array_n1", array_n1);
        returnIntent.putExtra("array_n2", array_n2);
        returnIntent.putExtra("array_n3", array_n3);
        returnIntent.putExtra("contador", contador);

        setResult(RESULT_OK, returnIntent);
        finish();
        }
    }


