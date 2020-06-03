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

public class InformacoesPessoais extends AppCompatActivity {

    private FirebaseFirestore fstore;
    private FirebaseUser user;

    String numeroid;
    String name;
    String lastname;
    String phone;
    String git;
    String link;

    Map<String,Object> mapuser = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_pessoais);
        final EditText nome = findViewById(R.id.nome);
        final EditText sobrenome = findViewById(R.id.sobrenome);
        final EditText telefone = findViewById(R.id.telefone);
        final EditText github = findViewById(R.id.github);
        final EditText linkedin = findViewById(R.id.linkedin);
        Button avancar= findViewById(R.id.continuar);
        fstore = FirebaseFirestore.getInstance();


        avancar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = nome.getText().toString().trim();
                lastname = sobrenome.getText().toString().trim();
                phone = telefone.getText().toString().trim();
                git = github.getText().toString().trim();
                link = linkedin.getText().toString().trim();

                if  (name.isEmpty() || lastname.isEmpty() || phone.isEmpty()){
                    alert("Preencha os dados obrigatórios");
                } else {

                    numeroid = user.getUid();
                    DocumentReference documentReference = fstore.collection("candidatos").document(numeroid);
                    mapuser.put("nome", name);
                    mapuser.put("sobrenome", lastname);
                    mapuser.put("telefone", phone);
                    if (git.isEmpty()){
                        git = " ";
                    }
                    if (link.isEmpty()){
                        link = " ";
                    }
                    mapuser.put("git", git);
                    mapuser.put("linkedin", link);
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("deu bom");
                        }
                    });
                    Intent intent = new Intent(InformacoesPessoais.this, Identificacao.class);
                    startActivity(intent);
                    InformacoesPessoais.this.onPause();
                }
            }
        });
    }

    private void alert(String msg) {
        Toast.makeText(InformacoesPessoais.this,msg,Toast.LENGTH_SHORT).show();
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
