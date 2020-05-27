package br.edu.insper.al.anaccf5.preparoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarSenha extends AppCompatActivity {

    private EditText editEmail;
    private Button reset_senha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        reset_senha = (Button) findViewById(R.id.button_recuperar);
        editEmail = (EditText) findViewById(R.id.edit_email);

        eventoClick();

    }

    private void eventoClick() {
        reset_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                if (email.isEmpty()){
                    alert("E-mail inválido");
                } else {
                    resetSenha(email);
                }
            }
        });
    }

    private void resetSenha(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(RecuperarSenha.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    alert("E-mail encaminhado");
                    finish();
                } else {
                    alert("E-mail não encontrado");
                }
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(RecuperarSenha.this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

}
