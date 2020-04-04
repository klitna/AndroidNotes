package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private String [] array_noms;
    private int [] array_n1;
    private int [] array_n2;
    private int [] array_n3;
    private int [] array_final;
    private int contador;


    public RecyclerViewAdapter(Context con, String[] arrN, int[]arrN1, int[]arrN2, int[]arrN3, int[]arrFi, int cont){
        array_noms = arrN;
        array_n1 = arrN1;
        array_n2 = arrN2;
        array_n3 = arrN3;
        array_final=arrFi;
        contador=cont;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.etiquetaNom.setText(array_noms[position]);
        if(array_n1[position]>0){
            holder.nota1.setText(Integer.toString(array_n1[position]));
            holder.nota2.setText(Integer.toString(array_n2[position]));
            holder.nota3.setText(Integer.toString(array_n3[position]));
            float mediana= Math. round(((float)(array_n1[position]+array_n2[position]+array_n3[position])/(float)3)* (float)10)/(float)10;
            holder.media.setText(Float.toString(mediana));


        }
    }

   /* if(descFinal==false) {
        descFinal = true;
        array_final=deMenorMayor(array_final);
    }
                else {
        descFinal = false;
        array_final=deMayorMenor(array_final);
    }*/





   public int[] deMenorMayor(int[] arraylist) {
        for(int i = 1; i < arraylist.length; i++) {
            for(int j = 0; j < (arraylist.length - i); j++) {
                if(arraylist[j] > arraylist[j+1]){
                    int aux = arraylist[j];
                    arraylist[j] = arraylist[j + 1];
                    arraylist[j + 1] = aux;
                }
            }
        }
        return arraylist;
    }

    public int[] deMayorMenor(int[] arraylist) {
        for(int i = 1; i < arraylist.length; i++) {
            for(int j = 0; j < (arraylist.length - i); j++) {
                if(arraylist[j] < arraylist[j+1]){
                    int aux = arraylist[j];
                    arraylist[j] = arraylist[j + 1];
                    arraylist[j + 1] = aux;
                }
            }
        }
        return arraylist;
    }
    @Override
    public int getItemCount() {
        return contador;
    }

    public void ordenar(){

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView etiquetaNom;
        TextView nota1;
        TextView nota2;
        TextView nota3;
        TextView media;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaNom = itemView.findViewById(R.id.userName);
            nota1 = itemView.findViewById(R.id.nota1);
            nota2 = itemView.findViewById(R.id.nota2);
            nota3 = itemView.findViewById(R.id.nota3);
            media=itemView.findViewById(R.id.Final);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}