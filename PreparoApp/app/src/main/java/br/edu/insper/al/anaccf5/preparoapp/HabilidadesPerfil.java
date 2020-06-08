package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class HabilidadesPerfil extends AppCompatActivity {

    private TextView le_situacaoHab1, le_situacaoHab2, le_situacaoHab3, le_situacaoHab4, le_situacaoHab5, le_situacaoHab6,
            le_situacaoHab7, le_name;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades_perfil);

        le_situacaoHab1 = (TextView) findViewById(R.id.Situacao_Habilidade1);
        le_situacaoHab2 = (TextView) findViewById(R.id.Situacao_Habilidade2);
        le_situacaoHab3 = (TextView) findViewById(R.id.Situacao_Habilidade3);
        le_situacaoHab4 = (TextView) findViewById(R.id.Situacao_Habilidade4);
        le_situacaoHab5 = (TextView) findViewById(R.id.Situacao_Habilidade5);
        le_situacaoHab6 = (TextView) findViewById(R.id.Situacao_Habilidade6);
        le_situacaoHab7 = (TextView) findViewById(R.id.Situacao_Habilidade7);
        le_name = (TextView) findViewById(R.id.nomeUser);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.contains("AI")) {

                        if (!documentSnapshot.getString("AI").equals("Nenhum item selecionado")) {
                            le_situacaoHab6.setText(documentSnapshot.getString("AI"));
                        }
                        if (!documentSnapshot.getString("python").equals("Nenhum item selecionado")) {
                            le_situacaoHab5.setText(documentSnapshot.getString("python"));
                        }
                        if (!documentSnapshot.getString("estatistica").equals("Nenhum item selecionado")) {
                            le_situacaoHab1.setText(documentSnapshot.getString("estatistica"));
                        }
                        if (!documentSnapshot.getString("modelagem").equals("Nenhum item selecionado")) {
                            le_situacaoHab2.setText(documentSnapshot.getString("modelagem"));
                        }
                        if (!documentSnapshot.getString("R").equals("Nenhum item selecionado")) {
                            le_situacaoHab4.setText(documentSnapshot.getString("R"));
                        }
                        if (!documentSnapshot.getString("modelagem financeira").equals("Nenhum item selecionado")) {
                            le_situacaoHab3.setText(documentSnapshot.getString("modelagem financeira"));
                        }
                        if (!documentSnapshot.getString("machine learning").equals("Nenhum item selecionado")) {
                            le_situacaoHab7.setText(documentSnapshot.getString("machine learning"));
                        }
                    }

                    le_name.setText(documentSnapshot.getString("nome"));

            }
        });
    }

}
