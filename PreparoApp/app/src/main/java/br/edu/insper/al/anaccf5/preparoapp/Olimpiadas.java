package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Olimpiadas extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olimpiadas);

        Button avancar = findViewById(R.id.continuar);

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Olimpiadas.this, Perfil.class);
                startActivity(intent);
                onPause();
            };
        });

        Spinner spinner8 = findViewById(R.id.spinner_8);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);
        spinner8.setOnItemSelectedListener(this);

        Spinner spinner9 = findViewById(R.id.spinner_9);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter9);
        spinner9.setOnItemSelectedListener(this);

        Spinner spinner10 = findViewById(R.id.spinner_10);
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner10.setAdapter(adapter10);
        spinner10.setOnItemSelectedListener(this);

        Spinner spinner11 = findViewById(R.id.spinner_11);
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner11.setAdapter(adapter11);
        spinner11.setOnItemSelectedListener(this);

        Spinner spinner12 = findViewById(R.id.spinner_12);
        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner12.setAdapter(adapter12);
        spinner12.setOnItemSelectedListener(this);

        Spinner spinner13 = findViewById(R.id.spinner_13);
        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner13.setAdapter(adapter13);
        spinner13.setOnItemSelectedListener(this);

        Spinner spinner14 = findViewById(R.id.spinner_14);
        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner14.setAdapter(adapter14);
        spinner14.setOnItemSelectedListener(this);

        Spinner spinner15 = findViewById(R.id.spinner_15);
        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner15.setAdapter(adapter15);
        spinner15.setOnItemSelectedListener(this);

        Spinner spinner16 = findViewById(R.id.spinner_16);
        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner16.setAdapter(adapter16);
        spinner16.setOnItemSelectedListener(this);

        Spinner spinner17 = findViewById(R.id.spinner_17);
        ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner17.setAdapter(adapter17);
        spinner17.setOnItemSelectedListener(this);

        Spinner spinner18 = findViewById(R.id.spinner_18);
        ArrayAdapter<CharSequence> adapter18 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner18.setAdapter(adapter18);
        spinner18.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
