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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Localizacao extends AppCompatActivity {

    private FirebaseFirestore fstore;
    private FirebaseUser user;

    String numeroid;
    String cep;
    String endereco;
    String bairro;
    String cidade;
    String estado;
    String complemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);
        final EditText CEP = findViewById(R.id.cep);
        final EditText enderecoUser = findViewById(R.id.endereco);
        final EditText bairroUser = findViewById(R.id.bairro);
        final EditText cidadeUser = findViewById(R.id.cidade);
        final EditText estadoUser = findViewById(R.id.estado);
        final EditText complementoUser = findViewById(R.id.complemento);
        Button avancar= findViewById(R.id.continuar);
        Button voltar= findViewById(R.id.voltar);
        Button preencherDepois=findViewById(R.id.preencherDepois);
        fstore = FirebaseFirestore.getInstance();
        FirebaseAuth auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();

        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Localizacao.this, FormacaoAcademica.class);
                startActivity(intent);
                Localizacao.this.onPause();
            }
        });

        preencherDepois.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Localizacao.this, Perfil.class);
                startActivity(intent);
                Localizacao.this.onPause();

            }
        });

        avancar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cep = CEP.getText().toString().trim();
                endereco = enderecoUser.getText().toString().trim();
                bairro = bairroUser.getText().toString().trim();
                cidade = cidadeUser.getText().toString().trim();
                estado = estadoUser.getText().toString().trim();
                complemento = complementoUser.getText().toString().trim();

                numeroid = user.getUid();
                DocumentReference documentReference = fstore.collection("candidatos").document(numeroid);
                Map<String,Object> mapuser = new HashMap<>();
                mapuser.put("cep", cep);
                mapuser.put("endereco", endereco);
                mapuser.put("bairro", bairro);
                mapuser.put("cidade", cidade);
                mapuser.put("estado", estado);
                mapuser.put("complemento", complemento);
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });
                Intent intent = new Intent(Localizacao.this, Curriculo.class);
                startActivity(intent);
                Localizacao.this.onPause();

            }
        });
    }

    private void alert(String msg) {
        Toast.makeText(Localizacao.this,msg,Toast.LENGTH_SHORT).show();
    }

}
