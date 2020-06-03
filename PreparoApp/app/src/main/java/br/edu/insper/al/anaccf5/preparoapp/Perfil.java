package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;


public class Perfil extends AppCompatActivity {

//    private FirebaseUser user;

    private TextView le_nome;


    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        le_nome = (TextView) findViewById(R.id.nomeUser);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                le_nome.setText(documentSnapshot.getString("nome").substring(0,1).toUpperCase() + documentSnapshot.getString("nome").substring(1));

            }
        });

//        log_out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Conexao.logout();
//                Intent intent = new Intent(Perfil.this, MainActivity.class);
//                Perfil.this.startActivity(intent);
//                Perfil.this.onPause();
//                user_email.setText("E-mail \n");
//                le_id.setText("ID \n");
//            }
//        });
    }

//    private void inicializaComponentes() {
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseAuth auth = Conexao.getFirebaseAuth();
//        user = Conexao.getFirebaseUser();
//        verificaUser();
//    }
//
//    @SuppressLint("SetTextI18n")
//    private void verificaUser() {
//        if (user == null){
//            finish();
//        } else {
//            user_email.setText("E-mail: \n".concat(user.getEmail() + "\n"));
//            le_id.setText("ID: \n".concat(user.getUid() + "\n"));
//        }
}
