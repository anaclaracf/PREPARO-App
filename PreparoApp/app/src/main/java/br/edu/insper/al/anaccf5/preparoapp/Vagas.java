package br.edu.insper.al.anaccf5.preparoapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

                            LinearLayout.LayoutParams param_card = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            param_card.setMargins(0, 50, 0, 50);
                            param_card.width = 810;
                            param_card.height = 1150;

                            final CardView card = new CardView(mContext);
                            card.setLayoutParams(param_card);
                            card.setContentPadding(30, 30, 30, 30);
                            card.setMaxCardElevation(5);
                            card.setCardElevation(9);
                            card.setRadius(15);
                            ll.addView(card);
                            System.out.println(valores);

                            LinearLayout.LayoutParams params_estag = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            params_estag.setMargins(115, 590, 0, 0);
                            params_estag.width = 600;
                            params_estag.height = 200;

                            TextView cargo = new TextView(mContext);
                            cargo.setTextSize(23);
                            cargo.setText(documentSnapshot.getString("cargo"));
                            cargo.setLayoutParams(params_estag);
                            cargo.setTextColor(getResources().getColor(R.color.colorPrimary));
                            cargo.setTypeface(null, Typeface.BOLD);





                            LinearLayout.LayoutParams params_rem = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            params_rem.setMargins(0, 930, 0, 0);
                            params_rem.width = 350;
                            params_rem.height = 150;

                            Button rem = new Button(mContext);
                            rem.setTextSize(13);
                            rem.setText(documentSnapshot.getString("salario"));
                            rem.setLayoutParams(params_rem);
                            rem.setBackgroundResource(R.drawable.btn_bg);
                            rem.setAllCaps(false);

                            LinearLayout.LayoutParams params_tipo = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            params_tipo.setMargins(390, 930, 0, 0);
                            params_tipo.width = 350;
                            params_tipo.height = 150;

                            Button tipo = new Button(mContext);
                            tipo.setTextSize(13);
                            tipo.setText(documentSnapshot.getString("tipo"));
                            tipo.setLayoutParams(params_tipo);
                            tipo.setBackgroundResource(R.drawable.btn_bg);
                            tipo.setAllCaps(false);

                            LinearLayout.LayoutParams params_interess = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            params_interess.setMargins(5, 760, 0, 10);
                            params_interess.width = 737;
                            params_interess.height = 150;

                            Button interesse = new Button(mContext);
                            interesse.setTextSize(15);
                            interesse.setText(documentSnapshot.getString("interesse1"));
                            interesse.setLayoutParams(params_interess);
                            interesse.setBackgroundResource(R.drawable.btn_bg);
                            interesse.setAllCaps(false);


                            LinearLayout.LayoutParams params_foto = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            params_foto.setMargins(0, 0, 0, 10);
                            params_foto.width = 780;
                            params_foto.height = 500;

                            final ImageView foto = new ImageView(mContext);
                            foto.setLayoutParams(params_foto);



                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference imageRef = storage.getReference()
                                    .child("icone")
                                    .child(documentSnapshot.getString("foto"));
                            imageRef.getBytes(1024*1024)
                                    .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                        @Override
                                        public void onSuccess(byte[] bytes) {
                                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                            foto.setImageBitmap(bitmap);
                                        }
                                    });


                            card.addView(rem);
                            card.addView(tipo);
                            card.addView(interesse);
                            card.addView(foto);
                            card.addView(cargo);




                        }
                    }
                }
            }
        });
    }

}
