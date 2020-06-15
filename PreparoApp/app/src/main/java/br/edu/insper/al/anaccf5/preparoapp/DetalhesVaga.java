package br.edu.insper.al.anaccf5.preparoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.annotation.Nullable;

public class DetalhesVaga extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private FirebaseFirestore fstore;
    private FirebaseUser user;
    private FirebaseAuth auth;


    ScrollView sv;
    LinearLayout ll;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_vaga);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_perfil);

        fstore = FirebaseFirestore.getInstance();
        auth = Conexao.getFirebaseAuth();

        sv = (ScrollView) findViewById(R.id.sv);
        ll = (LinearLayout) findViewById(R.id.ll);

        mContext = getApplicationContext();

        String a_vaga = getIntent().getStringExtra("vaga");
        System.out.println(a_vaga);

        DocumentReference documentReference = fstore.collection("vagas").document(a_vaga);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                LinearLayout.LayoutParams param_card = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                param_card.setMargins(0, 50, 0, 50);
                param_card.width = 810;
//                param_card.height = 1150;
                param_card.gravity = Gravity.CENTER;

                final CardView card = new CardView(mContext);
                card.setLayoutParams(param_card);
                card.setContentPadding(30, 30, 30, 30);
                card.setMaxCardElevation(5);
                card.setCardElevation(9);
                card.setRadius(15);

                ll.addView(card);

                LinearLayout.LayoutParams params_foto = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params_foto.setMargins(0, 0, 0, 10);
                params_foto.width = 740;
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

                LinearLayout.LayoutParams params_estag = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params_estag.setMargins(270, 615, 0, 0);
//                params_estag.gravity

                TextView cargo = new TextView(mContext);
                cargo.setTextSize(25);
                cargo.setText(documentSnapshot.getString("nome"));
                cargo.setLayoutParams(params_estag);
                cargo.setTextColor(getResources().getColor(R.color.colorPrimary));
                cargo.setTypeface(null, Typeface.BOLD);
                cargo.setGravity(Gravity.CENTER_HORIZONTAL);



                /** TIPO DE VAGA */



                LinearLayout.LayoutParams params_tipo_titulo = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params_tipo_titulo.setMargins(0, 800, 0, 0);


                TextView tipo_titulo = new TextView(mContext);
                tipo_titulo.setTextSize(18);
                tipo_titulo.setText("Tipo de vaga: ");
                tipo_titulo.setLayoutParams(params_tipo_titulo);
                tipo_titulo.setTextColor(getResources().getColor(R.color.colorPrimary));
                tipo_titulo.setTypeface(null, Typeface.BOLD);

                LinearLayout.LayoutParams params_tipo = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params_tipo.setMargins(0, 880, 0, 0);


                TextView tipo = new TextView(mContext);
                tipo.setTextSize(18);
                tipo.setText(documentSnapshot.getString("tipo"));
                tipo.setLayoutParams(params_tipo);
                tipo.setTextColor(getResources().getColor(R.color.colorPrimary));



                /** SALARIO DA VAGA */



                LinearLayout.LayoutParams params_salario_titulo = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params_salario_titulo.setMargins(0, 1010, 0, 0);


                TextView salario_titulo = new TextView(mContext);
                salario_titulo.setTextSize(18);
                salario_titulo.setText("Remuneração: ");
                salario_titulo.setLayoutParams(params_salario_titulo);
                salario_titulo.setTextColor(getResources().getColor(R.color.colorPrimary));
                salario_titulo.setTypeface(null, Typeface.BOLD);

                LinearLayout.LayoutParams params_salario = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params_salario.setMargins(0, 1090, 0, 0);


                TextView salario = new TextView(mContext);
                salario.setTextSize(18);
                salario.setText(documentSnapshot.getString("salario"));
                salario.setLayoutParams(params_salario);
                salario.setTextColor(getResources().getColor(R.color.colorPrimary));




                /** DESCRICAO DE VAGA */



                LinearLayout.LayoutParams params_descricao_titulo = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params_descricao_titulo.setMargins(0, 1220, 0, 0);


                TextView descricao_titulo = new TextView(mContext);
                descricao_titulo.setTextSize(18);
                descricao_titulo.setText("Descrição: ");
                descricao_titulo.setLayoutParams(params_descricao_titulo);
                descricao_titulo.setTextColor(getResources().getColor(R.color.colorPrimary));
                descricao_titulo.setTypeface(null, Typeface.BOLD);

                LinearLayout.LayoutParams params_descricao = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params_descricao.setMargins(0, 1300, 0, 0);


                TextView descricao = new TextView(mContext);
                descricao.setTextSize(18);
                descricao.setText(documentSnapshot.getString("descricao"));
                descricao.setLayoutParams(params_descricao);
                descricao.setTextColor(getResources().getColor(R.color.colorPrimary));







                card.addView(foto);
                card.addView(cargo);
                card.addView(tipo_titulo);
                card.addView(tipo);
                card.addView(salario_titulo);
                card.addView(salario);
                card.addView(descricao_titulo);
                card.addView(descricao);


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

//        String a_vaga = getIntent().getStringExtra("vaga");
//        System.out.println(a_vaga);
//
//        DocumentReference documentReference = fstore.collection("vagas").document(a_vaga);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//
//                LinearLayout.LayoutParams param_card = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT
//                );
//                param_card.setMargins(0, 50, 0, 50);
//                param_card.width = 810;
//                param_card.height = 1150;
//                param_card.gravity = Gravity.CENTER;
//
//                final CardView card = new CardView(mContext);
//                card.setLayoutParams(param_card);
//                card.setContentPadding(30, 30, 30, 30);
//                card.setMaxCardElevation(5);
//                card.setCardElevation(9);
//                card.setRadius(15);
//
//                ll.addView(card);
//
//                LinearLayout.LayoutParams params_estag = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT
//                );
//                params_estag.setMargins(0, 0, 0, 0);
//                params_estag.width = 600;
//                params_estag.height = 200;
//
//                TextView cargo = new TextView(mContext);
//                cargo.setTextSize(23);
//                cargo.setText(documentSnapshot.getString("cargo"));
//                cargo.setLayoutParams(params_estag);
//                cargo.setTextColor(getResources().getColor(R.color.colorPrimary));
//                cargo.setTypeface(null, Typeface.BOLD);
//
//                card.addView(cargo);


//


            }
//        });
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_perfil:
                break;
            case R.id.nav_vagas:
                Intent intent = new Intent(DetalhesVaga.this, Vagas.class);
                startActivity(intent);
                DetalhesVaga.this.onPause();
                break;
            case R.id.nav_sair:
                Intent i = new Intent(DetalhesVaga.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
            case R.id.nav_sobre:
                Intent intent1 = new Intent(DetalhesVaga.this, SobreNos.class);
                startActivity(intent1);
                DetalhesVaga.this.onPause();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
