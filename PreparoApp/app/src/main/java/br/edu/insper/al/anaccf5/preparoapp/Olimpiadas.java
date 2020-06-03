package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Olimpiadas extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private FirebaseFirestore fstore;
    private FirebaseUser user;
    String numeroid;

    String imc;
    String imo;
    String icho;
    String ioi;
    String ciic;
    String obm;
    String obq;
    String obi;
    String obf;
    String obmep;
    String obfep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olimpiadas);

        Button avancar = findViewById(R.id.continuar);
        Button voltar = findViewById(R.id.voltar);
        fstore = FirebaseFirestore.getInstance();


        final Spinner spinner8 = findViewById(R.id.spinner_8);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);
        spinner8.setOnItemSelectedListener(this);

        final Spinner spinner9 = findViewById(R.id.spinner_9);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter9);
        spinner9.setOnItemSelectedListener(this);

        final Spinner spinner10 = findViewById(R.id.spinner_10);
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner10.setAdapter(adapter10);
        spinner10.setOnItemSelectedListener(this);

        final Spinner spinner11 = findViewById(R.id.spinner_11);
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner11.setAdapter(adapter11);
        spinner11.setOnItemSelectedListener(this);

        final Spinner spinner12 = findViewById(R.id.spinner_12);
        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner12.setAdapter(adapter12);
        spinner12.setOnItemSelectedListener(this);

        final Spinner spinner13 = findViewById(R.id.spinner_13);
        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner13.setAdapter(adapter13);
        spinner13.setOnItemSelectedListener(this);

        final Spinner spinner14 = findViewById(R.id.spinner_14);
        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner14.setAdapter(adapter14);
        spinner14.setOnItemSelectedListener(this);

        final Spinner spinner15 = findViewById(R.id.spinner_15);
        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner15.setAdapter(adapter15);
        spinner15.setOnItemSelectedListener(this);

        final Spinner spinner16 = findViewById(R.id.spinner_16);
        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner16.setAdapter(adapter16);
        spinner16.setOnItemSelectedListener(this);

        final Spinner spinner17 = findViewById(R.id.spinner_17);
        ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner17.setAdapter(adapter17);
        spinner17.setOnItemSelectedListener(this);

        final Spinner spinner18 = findViewById(R.id.spinner_18);
        ArrayAdapter<CharSequence> adapter18 = ArrayAdapter.createFromResource(this,R.array.classificacao_olimpiadas,android.R.layout.simple_spinner_item);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner18.setAdapter(adapter18);
        spinner18.setOnItemSelectedListener(this);


        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                imc = spinner8.getSelectedItem().toString();
                imo = spinner9.getSelectedItem().toString();
                icho = spinner10.getSelectedItem().toString();
                ioi = spinner11.getSelectedItem().toString();
                ciic = spinner12.getSelectedItem().toString();
                obm = spinner13.getSelectedItem().toString();
                obq = spinner14.getSelectedItem().toString();
                obi = spinner15.getSelectedItem().toString();
                obf = spinner16.getSelectedItem().toString();
                obmep = spinner17.getSelectedItem().toString();
                obfep = spinner18.getSelectedItem().toString();

                numeroid = user.getUid();
                DocumentReference documentReference = fstore.collection("candidatos").document(numeroid);
                Map<String,Object> mapuser = new HashMap<>();
                mapuser.put("imc", imc);
                mapuser.put("imo", imo);
                mapuser.put("icho", icho);
                mapuser.put("ioi", ioi);
                mapuser.put("ciic", ciic);
                mapuser.put("obm", obm);
                mapuser.put("obq", obq);
                mapuser.put("obi", obi);
                mapuser.put("obf", obf);
                mapuser.put("obmep", obmep);
                mapuser.put("obfep", obfep);


                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

                Intent intent = new Intent(Olimpiadas.this, Perfil.class);
                startActivity(intent);
                Olimpiadas.this.onPause();
            };
        });

        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Olimpiadas.this, Habilidades.class);
                startActivity(intent);
                Olimpiadas.this.onPause();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        numeroid = user.getUid();
        verificaUser();
    }

    private void verificaUser() {
        if (user == null){
            finish();
        }
    }

}
