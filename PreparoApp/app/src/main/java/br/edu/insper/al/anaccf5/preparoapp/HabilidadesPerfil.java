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
import android.os.Bundle;
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

    Context mContext;

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


        card = (CardView) findViewById(R.id.cardhabib);
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


        DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                for (String lo_chico : documentSnapshot.getData().keySet()) {
                    if (lo_chico.length() > 10) {
                        if (lo_chico.substring(0, 10).equals("habilidade")) {
                            if (!documentSnapshot.getString(lo_chico).equals("Nenhum item selecionado")) {

                            }
                        }
                    }
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
