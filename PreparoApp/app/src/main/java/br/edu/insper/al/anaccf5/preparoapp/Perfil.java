package br.edu.insper.al.anaccf5.preparoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FirebaseUser user;
    private TextView user_email, le_id;
    private Button log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//        inicializaComponentes();

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conexao.logout();
                Intent intent = new Intent(Perfil.this, MainActivity.class);
                Perfil.this.startActivity(intent);
                Perfil.this.onPause();
                user_email.setText("E-mail \n");
                le_id.setText("ID \n");
            }
        });
    }

//    @Override
//    public boolean OnNavigationItemSelected(@NonNull MenuItem item);
//    private void inicializaComponentes() {
//        user_email = (TextView) findViewById(R.id.email_perfil);
//        le_id = (TextView) findViewById(R.id.id_perfil);
//        log_out = (Button) findViewById(R.id.logout);
//    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();
    }

    @SuppressLint("SetTextI18n")
    private void verificaUser() {
        if (user == null){
            finish();
        } else {
            user_email.setText("E-mail: \n".concat(user.getEmail() + "\n"));
            le_id.setText("ID: \n".concat(user.getUid() + "\n"));
        }
    }

}
