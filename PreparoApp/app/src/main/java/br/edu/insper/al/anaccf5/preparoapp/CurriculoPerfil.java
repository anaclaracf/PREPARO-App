package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class CurriculoPerfil extends AppCompatActivity {

    private TextView le_name, le_frase;
    private Button le_botao;


    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculo_perfil);

        le_name = (TextView) findViewById(R.id.nomeUser);
        le_botao= (Button) findViewById(R.id.inserir);
        le_frase= (TextView) findViewById(R.id.jainseriu);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                le_name.setText(documentSnapshot.getString("nome").substring(0, 1).toUpperCase() + documentSnapshot.getString("nome").substring(1));
                if (documentSnapshot.contains("uriCurriculo")) {
                    le_botao.setVisibility(View.GONE);
                    le_frase.setVisibility(View.VISIBLE);


                }else{
                    le_botao.setVisibility(View.VISIBLE);
                    le_frase.setVisibility(View.GONE);


                }

            }

        });
    }
}
