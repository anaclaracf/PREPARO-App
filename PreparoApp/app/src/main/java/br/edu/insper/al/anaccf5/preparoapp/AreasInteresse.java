package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AreasInteresse extends AppCompatActivity {

    Button avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_interesse);

        avancar = (Button) findViewById(R.id.continuar);

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AreasInteresse.this, Perfil.class);
                AreasInteresse.this.startActivity(intent);
                AreasInteresse.this.onPause();
            }
        });

        final ImageButton imagem1= findViewById(R.id.imagem1);
        final ImageButton imagem2= findViewById(R.id.imagem2);
        final ImageButton imagem3= findViewById(R.id.imagem3);
        final ImageButton imagem4= findViewById(R.id.imagem4);
        final ImageButton imagem5= findViewById(R.id.imagem5);
        final ImageButton imagem6= findViewById(R.id.imagem6);
        final ImageButton imagem7= findViewById(R.id.imagem7);
        final ImageButton imagem8= findViewById(R.id.imagem8);
        final ImageButton imagem9= findViewById(R.id.imagem9);
        final ImageButton imagem10= findViewById(R.id.imagem10);
        final ImageButton imagem11= findViewById(R.id.imagem11);
        final ImageButton imagem12= findViewById(R.id.imagem12);

        imagem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem1.setAlpha(0.9f);

            }
        });

        imagem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem2.setAlpha(0.9f);

            }
        });

        imagem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem3.setAlpha(0.9f);

            }
        });

        imagem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem4.setAlpha(0.9f);

            }
        });

        imagem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem5.setAlpha(0.9f);

            }
        });

        imagem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem6.setAlpha(0.9f);

            }
        });

        imagem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem7.setAlpha(0.9f);

            }
        });

        imagem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem8.setAlpha(0.9f);

            }
        });

        imagem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem9.setAlpha(0.9f);

            }
        });

        imagem10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem10.setAlpha(0.9f);

            }
        });

        imagem11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem11.setAlpha(0.9f);

            }
        });

        imagem12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem12.setAlpha(0.9f);

            }
        });



    }
}
