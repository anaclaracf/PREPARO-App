package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Habilidades extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);
        Button avancar = findViewById(R.id.continuar);

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Habilidades.this, Olimpiadas.class);
                startActivity(intent);
                onPause();
            };
        });

        Spinner spinner1 = findViewById(R.id.spinner_1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.spinner_2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        Spinner spinner3 = findViewById(R.id.spinner_3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);

        Spinner spinner4 = findViewById(R.id.spinner_4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(this);

        Spinner spinner5 = findViewById(R.id.spinner_5);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(this);

        Spinner spinner6 = findViewById(R.id.spinner_6);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        spinner6.setOnItemSelectedListener(this);

        Spinner spinner7 = findViewById(R.id.spinner_7);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
        spinner7.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
