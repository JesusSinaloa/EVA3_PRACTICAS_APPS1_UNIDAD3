package com.example.eduardo.eva3_6_handler_post;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    TextView numeros;

    Handler hManejador = new Handler();
    //RUNNABLES 1 --> TRABAJO PESADO EN SEGUNDO PLANO


    Runnable rSegundoPlano = new Runnable() {
        @Override
        public void run() {
            while (true){
                hManejador.post(rRunnableUI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };
    int iNum = 1;
    //RUNNABLES 2 --> TRABAJO LIGERO Y TRABAJO EN LA UI
    Runnable rRunnableUI = new Runnable() {
        @Override
        public void run() {
            //AQUI SI PODEMOS INTERACTUAR CON LA INTERFAZ GRAFICA
            numeros.append((iNum++) + " - ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        numeros = findViewById(R.id.txtViewNumeros);

        Thread tHilo = new Thread(rSegundoPlano);
        tHilo.start();
    }
}
