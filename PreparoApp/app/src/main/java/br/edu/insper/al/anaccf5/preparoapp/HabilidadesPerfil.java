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
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class HabilidadesPerfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView le_situacaoHab1, le_situacaoHab2, le_situacaoHab3, le_situacaoHab4, le_situacaoHab5, le_situacaoHab6,
            le_situacaoHab7, le_name;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    CardView card;
    LinearLayout ll;


    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades_perfil);

//        le_situacaoHab1 = (TextView) findViewById(R.id.Situacao_Habilidade1);
//        le_situacaoHab2 = (TextView) findViewById(R.id.Situacao_Habilidade2);
//        le_situacaoHab3 = (TextView) findViewById(R.id.Situacao_Habilidade3);
//        le_situacaoHab4 = (TextView) findViewById(R.id.Situacao_Habilidade4);
//        le_situacaoHab5 = (TextView) findViewById(R.id.Situacao_Habilidade5);
//        le_situacaoHab6 = (TextView) findViewById(R.id.Situacao_Habilidade6);
//        le_situacaoHab7 = (TextView) findViewById(R.id.Situacao_Habilidade7);
        le_name = (TextView) findViewById(R.id.nomeUser);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = auth.getCurrentUser().getUid();


//        card = (CardView) findViewById(R.id.cardhabib);
        ll = (LinearLayout) findViewById(R.id.ll);
        mContext = getApplicationContext();

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
        final ArrayList <String> possuiHab= new ArrayList<>();



        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                for (String lo_chico : documentSnapshot.getData().keySet()) {
                    if (lo_chico.length() > 10) {
                        if (lo_chico.substring(0, 10).equals("habilidade")) {
                            if (!documentSnapshot.getString(lo_chico).equals("Nenhum item selecionado")) {
                                possuiHab.add(lo_chico.toString());

                                LinearLayout.LayoutParams param_habilidade_titulo = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                param_habilidade_titulo.setMargins(0, 10, 0, 0);


                                TextView habilidade = new TextView(mContext);
                                habilidade.setTextSize(18);
                                if (lo_chico.equals("habilidade1")){
                                    habilidade.setText("Estatística:");
                                }
                                if (lo_chico.equals("habilidade2")){
                                    habilidade.setText("Modelagem:");
                                }
                                if (lo_chico.equals("habilidade3")){
                                    habilidade.setText("Modelagem financeira:");
                                }
                                if (lo_chico.equals("habilidade4")){
                                    habilidade.setText("R:");
                                }
                                if (lo_chico.equals("habilidade5")){
                                    habilidade.setText("Python:");
                                }
                                if (lo_chico.equals("habilidade6")){
                                    habilidade.setText("AI:");
                                }
                                if (lo_chico.equals("habilidade7")){
                                    habilidade.setText("Machine Learning:");
                                }
                                habilidade.setLayoutParams(param_habilidade_titulo);
                                habilidade.setTextColor(getResources().getColor(R.color.colorPrimary));
                                habilidade.setTypeface(null, Typeface.BOLD);

                                LinearLayout.LayoutParams params_nivel = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                params_nivel.setMargins(0, 10, 0, 0);


                                TextView nivel = new TextView(mContext);
                                nivel.setTextSize(18);
                                nivel.setText(documentSnapshot.getString(lo_chico));
                                nivel.setLayoutParams(params_nivel);
                                nivel.setTextColor(getResources().getColor(R.color.colorPrimary));

                                ll.addView(habilidade);
                                ll.addView(nivel);
                            }
                        }
                    }
                }
                if(possuiHab.isEmpty()){

                    LinearLayout.LayoutParams params_nivel = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT );
                    params_nivel.setMargins(0, 300, 0, 0);

                    TextView nivel2 = new TextView(mContext);
                    nivel2.setTextSize(18);
                    nivel2.setText("Nenhuma habilidade foi selecionada!");
                    nivel2.setLayoutParams(params_nivel);
                    nivel2.setGravity(Gravity.CENTER);
                    nivel2.setTypeface(null, Typeface.BOLD);
                    nivel2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    ll.addView(nivel2);



                }



//                if (documentSnapshot.contains("AI")) {
//
//                        if (!documentSnapshot.getString("AI").equals("Nenhum item selecionado")) {
//                            le_situacaoHab6.setText(documentSnapshot.getString("AI"));
//                        }
//                        if (!documentSnapshot.getString("python").equals("Nenhum item selecionado")) {
//                            le_situacaoHab5.setText(documentSnapshot.getString("python"));
//                        }
//                        if (!documentSnapshot.getString("estatistica").equals("Nenhum item selecionado")) {
//                            le_situacaoHab1.setText(documentSnapshot.getString("estatistica"));
//                        }
//                        if (!documentSnapshot.getString("modelagem").equals("Nenhum item selecionado")) {
//                            le_situacaoHab2.setText(documentSnapshot.getString("modelagem"));
//                        }
//                        if (!documentSnapshot.getString("R").equals("Nenhum item selecionado")) {
//                            le_situacaoHab4.setText(documentSnapshot.getString("R"));
//                        }
//                        if (!documentSnapshot.getString("modelagem financeira").equals("Nenhum item selecionado")) {
//                            le_situacaoHab3.setText(documentSnapshot.getString("modelagem financeira"));
//                        }
//                        if (!documentSnapshot.getString("machine learning").equals("Nenhum item selecionado")) {
//                            le_situacaoHab7.setText(documentSnapshot.getString("machine learning"));
//                        }
//                    }
//
                    le_name.setText(documentSnapshot.getString("nome"));

            }
        });
    }

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
                Intent intent0 = new Intent(HabilidadesPerfil.this, Perfil.class);
                startActivity(intent0);
                HabilidadesPerfil.this.onPause();
                break;
            case R.id.nav_vagas:
                Intent intent = new Intent(HabilidadesPerfil.this, Vagas.class);
                startActivity(intent);
                HabilidadesPerfil.this.onPause();
                break;
            case R.id.nav_sair:
                Intent i = new Intent(HabilidadesPerfil.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
            case R.id.nav_sobre:
                Intent intent1 = new Intent(HabilidadesPerfil.this, SobreNos.class);
                startActivity(intent1);
                HabilidadesPerfil.this.onPause();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
