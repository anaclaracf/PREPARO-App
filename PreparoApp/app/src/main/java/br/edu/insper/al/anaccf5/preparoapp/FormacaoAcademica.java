package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FormacaoAcademica extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore fstore;
    private FirebaseUser user;

    String numeroid;
    String institution;
    String course;
    String situation;
    String processograd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao_academica);

        final EditText instituicao = findViewById(R.id.instituicao);
        final EditText curso = findViewById(R.id.curso);
        Button avancar= findViewById(R.id.seguinte);
        Button voltar= findViewById(R.id.voltar);
        Button preencherDepois = findViewById((R.id.preencherDepois));
        fstore = FirebaseFirestore.getInstance();

        final Spinner spinner = findViewById(R.id.spinner_nivel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.classificacao_formacao,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final Spinner situacao = findViewById(R.id.spinner_situacao);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.situacao,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        situacao.setAdapter(adapter2);
        situacao.setOnItemSelectedListener(this);

        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FormacaoAcademica.this, Identificacao.class);
                startActivity(intent);
                FormacaoAcademica.this.onPause();
            }
        });

        preencherDepois.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FormacaoAcademica.this, Perfil.class);
                startActivity(intent);
                FormacaoAcademica.this.onPause();

            }
        });


        avancar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                institution = instituicao.getText().toString().trim();
                course = curso.getText().toString().trim();
                situation = situacao.getSelectedItem().toString().trim();
                processograd = spinner.getSelectedItem().toString().trim();

                if (institution.isEmpty() || course.isEmpty() || situation.isEmpty() || processograd.isEmpty()){
                    alert("Preencha todos os campos");
                } else {


                    numeroid = user.getUid();
                    DocumentReference documentReference = fstore.collection("candidatos").document(numeroid);
                    Map<String, Object> mapuser = new HashMap<>();
                    mapuser.put("instituicao", institution);
                    mapuser.put("curso", course);
                    mapuser.put("situacao", situation);
                    mapuser.put("processograd", processograd);
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    Intent intent = new Intent(FormacaoAcademica.this, Localizacao.class);
                    startActivity(intent);
                    FormacaoAcademica.this.onPause();

                }

            }
        });
    }
    private void alert(String msg) {
        Toast.makeText(FormacaoAcademica.this,msg,Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
