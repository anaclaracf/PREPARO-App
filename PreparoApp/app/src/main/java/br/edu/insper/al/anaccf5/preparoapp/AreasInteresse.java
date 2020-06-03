package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AreasInteresse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_interesse);
        Button irperfil = findViewById(R.id.ir_perfil);


        irperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AreasInteresse.this, Perfil.class);
                AreasInteresse.this.startActivity(intent);
                AreasInteresse.this.onPause();
            }
        });

    }
}
