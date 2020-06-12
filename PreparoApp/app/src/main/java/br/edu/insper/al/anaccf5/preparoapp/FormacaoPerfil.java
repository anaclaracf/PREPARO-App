package br.edu.insper.al.anaccf5.preparoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class FormacaoPerfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView le_curso,le_insituicao, le_processograd, le_situacao, le_name;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao_perfil);

        le_curso = (TextView) findViewById(R.id.le_curso);
        le_insituicao = (TextView) findViewById(R.id.li_instituicao);
        le_processograd = (TextView) findViewById(R.id.processoGrad);
        le_situacao = (TextView) findViewById(R.id.Situation);
        le_name = (TextView) findViewById(R.id.nomeUser);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = auth.getCurrentUser().getUid();

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


        final DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                le_name.setText(documentSnapshot.getString("nome").substring(0,1).toUpperCase() + documentSnapshot.getString("nome").substring(1));


                if (documentSnapshot.contains("curso")) {
                    le_curso.setText(documentSnapshot.getString("curso").substring(0, 1).toUpperCase() + documentSnapshot.getString("curso").substring(1));
                } else {

                    le_curso.setText("");
                }
                if (documentSnapshot.contains("instituicao")) {

                    le_insituicao.setText(documentSnapshot.getString("instituicao").substring(0, 1).toUpperCase() + documentSnapshot.getString("instituicao").substring(1));

                }else {


                    le_insituicao.setText("");

                }
                if (documentSnapshot.contains("processograd")) {

                    le_processograd.setText(documentSnapshot.getString("processograd").substring(0, 1).toUpperCase() + documentSnapshot.getString("processograd").substring(1));

                }else {

                    le_processograd.setText("Nao foi preenchido");
                }
                if (documentSnapshot.contains("situacao")) {

                    le_situacao.setText(documentSnapshot.getString("situacao").substring(0, 1).toUpperCase() + documentSnapshot.getString("situacao").substring(1));

                }else {


                    le_situacao.setText("");

                }
                if (documentSnapshot.contains("instituicao")) {

                    le_situacao.setText(documentSnapshot.getString("situacao").substring(0, 1).toUpperCase() + documentSnapshot.getString("situacao").substring(1));

                }else {


                    le_situacao.setText("");

                }
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
                Intent intent = new Intent(FormacaoPerfil.this, Vagas.class);
                startActivity(intent);
                FormacaoPerfil.this.onPause();
                break;
            case R.id.nav_sair:
                Intent i = new Intent(FormacaoPerfil.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
            case R.id.nav_sobre:
                Intent intent1 = new Intent(FormacaoPerfil.this, SobreNos.class);
                startActivity(intent1);
                FormacaoPerfil.this.onPause();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}