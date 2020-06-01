package br.edu.insper.al.anaccf5.preparoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {
    public static final String TAG = "TAG";
    private FirebaseAuth auth;
    private FirebaseFirestore fstore;
    String userID;
    String email_usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        final EditText email = findViewById(R.id.email);
        final EditText senha = findViewById(R.id.senha);
        final EditText confirmation = findViewById(R.id.confirmation_senha);
        Button cadastrar = findViewById(R.id.button_cadastro);
        fstore = FirebaseFirestore.getInstance();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_usuario = email.getText().toString().trim();
                String senha_usuario = senha.getText().toString().trim();
                String confirmation_senha = confirmation.getText().toString().trim();
                if (confirmation_senha.equals(senha_usuario)){
                    if  (email_usuario.isEmpty() || senha_usuario.isEmpty()){
                        alert("Dados insuficientes");
                    } else {
                        criarUser(email_usuario, senha_usuario);
                    }
                } else {
                    alert("As senhas divergem");
                }
            }
        });
    }

    private void criarUser(String email, String senha) {
        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            alert(("Usuário cadastrado com sucesso"));

                            userID = auth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("candidatos").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("email", email_usuario);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    System.out.println("deu bom");
                                }
                            });

                            Intent intent = new Intent(Cadastro.this, Perfil.class);
                            Cadastro.this.startActivity(intent);
                            Cadastro.this.onPause();
                        } else {
                            alert("Erro de cadastro");
                        }
                    }
                });
    }

    private void alert(String msg){
        Toast.makeText(Cadastro.this,msg,Toast.LENGTH_SHORT).show();
    }


    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
