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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Habilidades extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore fstore;
    private FirebaseUser user;
    String numeroid;

    String estatistica;
    String modelagem;
    String mod_fin;
    String hab_R;
    String python;
    String ai;
    String m_learning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);
        Button avancar = findViewById(R.id.continuar);
        Button voltar= findViewById(R.id.voltar);
        fstore = FirebaseFirestore.getInstance();
        Button preencherDepois= findViewById(R.id.preencherDepois);

        final Spinner spinner1 = findViewById(R.id.spinner_1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        final Spinner spinner2 = findViewById(R.id.spinner_2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        final Spinner spinner3 = findViewById(R.id.spinner_3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);

        final Spinner spinner4 = findViewById(R.id.spinner_4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(this);

        final Spinner spinner5 = findViewById(R.id.spinner_5);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(this);

        final Spinner spinner6 = findViewById(R.id.spinner_6);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        spinner6.setOnItemSelectedListener(this);

        final Spinner spinner7 = findViewById(R.id.spinner_7);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.classificacao_habilidades,android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
        spinner7.setOnItemSelectedListener(this);



        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Habilidades.this, Curriculo.class);
                startActivity(intent);
                Habilidades.this.onPause();
            }
        });





        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estatistica = spinner1.getSelectedItem().toString();
                modelagem = spinner2.getSelectedItem().toString();
                mod_fin = spinner3.getSelectedItem().toString();
                hab_R = spinner4.getSelectedItem().toString();
                python = spinner5.getSelectedItem().toString();
                ai = spinner6.getSelectedItem().toString();
                m_learning = spinner7.getSelectedItem().toString();

                numeroid = user.getUid();
                DocumentReference documentReference = fstore.collection("candidatos").document(numeroid);
                Map<String,Object> mapuser = new HashMap<>();
                mapuser.put("estatistica", estatistica);
                mapuser.put("modelagem", modelagem);
                mapuser.put("modelagem financeira", mod_fin);
                mapuser.put("R", hab_R);
                mapuser.put("python", python);
                mapuser.put("AI", ai);
                mapuser.put("machine learning", m_learning);
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });
                Intent intent = new Intent(Habilidades.this, Olimpiadas.class);
                startActivity(intent);
                Habilidades.this.onPause();

            };
        });


        preencherDepois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estatistica = spinner1.getSelectedItem().toString();
                modelagem = spinner2.getSelectedItem().toString();
                mod_fin = spinner3.getSelectedItem().toString();
                hab_R = spinner4.getSelectedItem().toString();
                python = spinner5.getSelectedItem().toString();
                ai = spinner6.getSelectedItem().toString();
                m_learning = spinner7.getSelectedItem().toString();

                numeroid = user.getUid();
                DocumentReference documentReference = fstore.collection("candidatos").document(numeroid);
                Map<String,Object> mapuser = new HashMap<>();
                mapuser.put("estatistica", estatistica);
                mapuser.put("modelagem", modelagem);
                mapuser.put("modelagem financeira", mod_fin);
                mapuser.put("R", hab_R);
                mapuser.put("python", python);
                mapuser.put("AI", ai);
                mapuser.put("machine learning", m_learning);
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });
                Intent intent = new Intent(Habilidades.this, Perfil.class);
                startActivity(intent);
                Habilidades.this.onPause();

            };
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
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
