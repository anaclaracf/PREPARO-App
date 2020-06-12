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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class CurriculoPerfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView le_name, le_frase;
    private Button le_botao;


    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

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
                le_name.setText(documentSnapshot.getString("nome").substring(0, 1).toUpperCase() + documentSnapshot.getString("nome").substring(1));
                if (documentSnapshot.contains("uriCurriculo")) {
                    le_botao.setVisibility(View.GONE);
                    le_frase.setVisibility(View.VISIBLE);


                }else{
                    le_botao.setVisibility(View.VISIBLE);
                    le_frase.setVisibility(View.GONE);


                    le_botao.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(CurriculoPerfil.this, AddcurriculoPerfil.class);
                            startActivity(intent);
                            CurriculoPerfil.this.onPause();
                        }
                    });


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
                Intent intent = new Intent(CurriculoPerfil.this, Vagas.class);
                startActivity(intent);
                CurriculoPerfil.this.onPause();
                break;
            case R.id.nav_sair:
                Intent i = new Intent(CurriculoPerfil.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
            case R.id.nav_sobre:
                Intent intent1 = new Intent(CurriculoPerfil.this, SobreNos.class);
                startActivity(intent1);
                CurriculoPerfil.this.onPause();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
