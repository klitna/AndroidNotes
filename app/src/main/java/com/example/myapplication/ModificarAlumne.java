package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarAlumne extends AppCompatActivity {
    String [] array_noms;
    //String nom_mod;
    int [] array_n1;
    int [] array_n2;
    int [] array_n3;
    int contador;
    int pos;
    EditText not1, not2, not3, nom, nom_modif;
    EditText nouNom, nouNota1, nouNota2, nouNota3;
    Button desar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_alumne);

        nouNom=findViewById(R.id.nouNom);
        nouNota1=findViewById(R.id.nouNota1);
        nouNota2=findViewById(R.id.nouNota2);
        nouNota3=findViewById(R.id.nouNota3);
        desar=findViewById(R.id.desarNou);

        nom_modif=findViewById(R.id.editNom);


        Intent intent = getIntent();

        array_noms = intent.getStringArrayExtra("array_noms");
        array_n1 = intent.getIntArrayExtra("array_n1");
        array_n2 = intent.getIntArrayExtra("array_n2");
        array_n3 = intent.getIntArrayExtra("array_n3");
        contador = intent.getIntExtra("contador", 0);

        final Button buscar=findViewById(R.id.btnBuscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canviar(v))
                {
                    nouNom.setVisibility(View.VISIBLE);
                    nouNota1.setVisibility(View.VISIBLE);
                    nouNota2.setVisibility(View.VISIBLE);
                    nouNota3.setVisibility(View.VISIBLE);
                    desar.setVisibility(View.VISIBLE);

                    nom_modif.setVisibility(View.INVISIBLE);
                    buscar.setVisibility(View.INVISIBLE);

                    nouNom.setText(array_noms[pos]);
                    nouNota1.setText(Integer.toString(array_n1[pos]));
                    nouNota2.setText(Integer.toString(array_n2[pos]));
                    nouNota3.setText(Integer.toString(array_n3[pos]));

                    goToCanviar();
                }
            }
        });

    }

    public boolean desarNouAlumne(View v){
        if(validarDesar())
        {
            Toast.makeText(this, "La informació s'ha desat correctament!", Toast.LENGTH_SHORT).show();
        }
        return validarDesar();
    }

    public boolean validarDesar(){
        boolean ok=true;
        String n1, n2, n3, nom_inpt;
        n1=nouNota1.getText().toString();
        n2=nouNota2.getText().toString();
        n3=nouNota3.getText().toString();
        nom_inpt=nouNom.getText().toString();

        if(nom_inpt.isEmpty())
        {
            nouNom.setError("Nom no pot ser buit!");
            ok=false;
        }

        if(n1.isEmpty())
        {
            nouNota1.setError("Nota deu de tenir un valor.!");
            ok=false;
        }

        else if(Integer.parseInt(n1)<0 || Integer.parseInt(n1)>10 )
        {
            nouNota1.setError("Nota incorrecta!");
            nouNota1.setText("");
            ok=false;
        }

        if(n2.isEmpty())
        {
            nouNota2.setError("Nota deu de tenir un valor.!");
            ok=false;
        }

        else if(Integer.parseInt(n2)<0 || Integer.parseInt(n2)>10 )
        {
            nouNota2.setError("Nota incorrecta!");
            nouNota2.setText("");
            ok=false;
        }


        if(n3.isEmpty())
        {
            nouNota3.setError("Nota deu de tenir un valor.!");
            ok=false;
        }

        else if(Integer.parseInt(n3)<0 || Integer.parseInt(n3)>10 )
        {
            nouNota3.setError("Nota incorrecta!");
            nouNota3.setText("");
            ok=false;
        }

        return ok;
    }

    public void goToCanviar(){
        desar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desarNouAlumne(v)) {
                    String nom = nouNom.getText().toString();
                    int n1 = Integer.parseInt(nouNota1.getText().toString());
                    int n2 = Integer.parseInt(nouNota2.getText().toString());
                    int n3 = Integer.parseInt(nouNota3.getText().toString());

                    array_noms[pos] = nom;
                    array_n1[pos] = n1;
                    array_n2[pos] = n2;
                    array_n3[pos] = n3;

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
        });
    }

    public boolean canviar(View v){
        if(validarInput())
        {
            Toast.makeText(this, "S'ha trobat l'alumne!", Toast.LENGTH_SHORT).show();
        }
        return validarInput();
    }

    public boolean validarInput(){
        boolean ok;
        String nom_inpt=nom_modif.getText().toString();
        if(nom_inpt.isEmpty())
        {
            nom_modif.setError("Camp buit!");
            ok=false;
        }

        else {
            ok=false;
            for (int i = 0; i < contador && !ok; i++) {
                System.out.println(array_noms[i]);
                if (array_noms[i].equals(nom_inpt)) {
                    ok = true;
                    pos=i;
                }
            }
            if(!ok)
                nom_modif.setError("Alumne no existeix!");
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
