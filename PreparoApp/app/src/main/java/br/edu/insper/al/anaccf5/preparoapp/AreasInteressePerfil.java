package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class AreasInteressePerfil extends AppCompatActivity {

    private TextView os_interesses,le_name;

    String estudos = "";

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_interesses_perfil);

        os_interesses = (TextView) findViewById(R.id.interesse);
        le_name = (TextView) findViewById(R.id.nomeUser);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = auth.getCurrentUser().getUid();


        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {


                for (String lo_chico : documentSnapshot.getData().keySet()) {
                    if (lo_chico.length() > 8) {
                        if (lo_chico.substring(0, 9).equals("interesse")) {
                            estudos += " " + (documentSnapshot.getString(lo_chico)) + ",";
                        }
                    }
                }


                le_name.setText(documentSnapshot.getString("nome").substring(0, 1).toUpperCase() + documentSnapshot.getString("nome").substring(1));
                os_interesses.setText(estudos.substring(0, estudos.length() - 1));

            }
        });

    }
}