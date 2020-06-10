package br.edu.insper.al.anaccf5.preparoapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;

import javax.annotation.Nullable;

public class Vagas extends AppCompatActivity {

    private static final String TAG = "";
    Context context;

    private FirebaseFirestore fstore;
    private FirebaseUser user;
    private FirebaseAuth auth;
    String userid;
    String interesses = "";
    String each_one = "";
    String [] each;

    ScrollView sv;
    LinearLayout ll;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vagas);

        fstore = FirebaseFirestore.getInstance();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        userid = auth.getCurrentUser().getUid();

        sv = (ScrollView) findViewById(R.id.sv);
        ll = (LinearLayout) findViewById(R.id.ll);

        mContext = getApplicationContext();



        /** PEGAR INTERESSES DA PESSOA **/
        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                for (String lo_chico : documentSnapshot.getData().keySet()) {
                    if (lo_chico.length() > 8) {
                        if (lo_chico.substring(0, 9).equals("interesse")) {
                            interesses += (documentSnapshot.getString(lo_chico)) + ",";
                        }
                    }
                }
                each = interesses.split(",");
                TextView tv = new TextView(getApplicationContext());
                for (int i = 0; i < each.length; i++) {
                    each_one += each[i];
                }
                tv.setText(each_one);
                tv.setTextSize(30);
//                card.addView(tv);
            }
        });

        /** PEGAR VAGAS **/
        final Task<QuerySnapshot> documentReference1 = fstore.collection("vagas").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        continuacao(document.getId());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

    private void continuacao(final String document) {
        DocumentReference documentReference2 = fstore.collection("vagas").document(document);
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                for (Object valores : documentSnapshot.getData().values()){
                    for (String item : each){
                        if (item.equals(valores)){
                            /** CARD **/
                            final CardView card = new CardView(mContext);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT
                            );
                            card.setLayoutParams(params);
                            card.setContentPadding(30, 30, 40, 30);
                            card.setMaxCardElevation(5);
                            card.setCardElevation(9);
                            card.setMinimumHeight(30);
                            card.setRadius(15);
                            ll.addView(card);
                            System.out.println(valores);

                            TextView tv2 = new TextView(mContext);
                            tv2.setTextSize(15);

                            ll.addView(tv2);
                        }
                    }
                }
            }
        });
    }

}
