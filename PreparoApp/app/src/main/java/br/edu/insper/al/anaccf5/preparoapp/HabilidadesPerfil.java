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

    private TextView le_Hab1,le_Hab2, le_situacaoHab1, le_situacaoHab2, le_name;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades_perfil);

        le_Hab1 = (TextView) findViewById(R.id.Habilidade1);
        le_Hab2 = (TextView) findViewById(R.id.Habilidade2);
        le_situacaoHab1 = (TextView) findViewById(R.id.Situacao_Habilidade1);
        le_situacaoHab2 = (TextView) findViewById(R.id.Situacao_Habilidade2);
        le_name = (TextView) findViewById(R.id.nomeUser);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = auth.getCurrentUser().getUid();

//        DocumentReference documentReference1 = fstore.collection("candidatos").document(userid);
//        documentReference1.

        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (!documentSnapshot.getString("AI").equals("Nenhum item selecionado")){
                    le_Hab2.setText("AI");
                    le_situacaoHab2.setText(documentSnapshot.getString("AI").substring(0, 1).toUpperCase() + documentSnapshot.getString("AI").substring(1));
                }
                if (!documentSnapshot.getString("python").equals("Nenhum item selecionado")){
                    le_Hab1.setText("Python");
                    le_situacaoHab1.setText(documentSnapshot.getString("python").substring(0, 1).toUpperCase() + documentSnapshot.getString("python").substring(1));
                }

//                for (String lo_chico : documentSnapshot.getData().keySet()){
//                    if ()
//                }



//                if (documentSnapshot.getString("python").equals("Nenhum item selecionado")){
//                    Intent intent = new Intent(HabilidadesPerfil.this, HabilidadesPerfilVazia.class);
//                    HabilidadesPerfil.this.startActivity(intent);
//                    HabilidadesPerfil.this.onPause();
//                }

                le_name.setText(documentSnapshot.getString("nome").substring(0, 1).toUpperCase() + documentSnapshot.getString("nome").substring(1));
            }
        });
    }

}


