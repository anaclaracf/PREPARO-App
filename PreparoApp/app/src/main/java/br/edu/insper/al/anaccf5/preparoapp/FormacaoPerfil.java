package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class FormacaoPerfil extends AppCompatActivity {

    private TextView le_curso,le_insituicao, le_processograd, le_situacao;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao_perfil);

        le_curso = (TextView) findViewById(R.id.le_curso);
        le_insituicao = (TextView) findViewById(R.id.li_instituicao);
        le_processograd = (TextView) findViewById(R.id.processoGrad);
        le_situacao = (TextView) findViewById(R.id.Situation);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                le_curso.setText(documentSnapshot.getString("curso").substring(0, 1).toUpperCase() + documentSnapshot.getString("curso").substring(1));
                le_insituicao.setText(documentSnapshot.getString("instituicao").substring(0, 1).toUpperCase() + documentSnapshot.getString("instituicao").substring(1));
                le_processograd.setText(documentSnapshot.getString("processograd").substring(0, 1).toUpperCase() + documentSnapshot.getString("processograd").substring(1));
                le_situacao.setText(documentSnapshot.getString("situacao").substring(0, 1).toUpperCase() + documentSnapshot.getString("situacao").substring(1));

            }
        });
    }
}