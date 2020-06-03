package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Curriculo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculo);
        Button avancar = findViewById(R.id.continuar);
        Button voltar= findViewById(R.id.voltar);


        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Curriculo.this, Localizacao.class);
                startActivity(intent);
                Curriculo.this.onPause();
            }
        });

        Button perfil6= findViewById(R.id.ir_perfil6);

        perfil6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Curriculo.this, Perfil.class);
                Curriculo.this.startActivity(intent);
                Curriculo.this.onPause();
            }
        });


        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Curriculo.this, Habilidades.class);
                startActivity(intent);
                onPause();

            };
        });
    };
}
