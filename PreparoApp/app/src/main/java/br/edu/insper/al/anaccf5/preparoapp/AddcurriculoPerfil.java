package br.edu.insper.al.anaccf5.preparoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class AddcurriculoPerfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Button btnSelect, btnupload;

    private TextView mFileStatus;

    private FirebaseDatabase mDatabase;

    private FirebaseStorage mStorage;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;
    String userid;

    DocumentReference documentReference;
    Map<String, Object> mapuser;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private Uri pdfUri;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcurriculo_perfil);

        btnSelect = findViewById(R.id.botao_curriculo);

        btnupload = findViewById(R.id.botao_upload);

        mFileStatus = findViewById(R.id.mensagem);

        mDatabase = FirebaseDatabase.getInstance();

        mStorage = FirebaseStorage.getInstance();


        Button concluido=findViewById(R.id.preencherDepois);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        mapuser = new HashMap<>();

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



        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(AddcurriculoPerfil.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {

                    selectPDF();

                } else {

                    ActivityCompat.requestPermissions(AddcurriculoPerfil.this, new String[]
                            {Manifest.permission.READ_EXTERNAL_STORAGE}, 5699);

                }

            }
        });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pdfUri != null) {

                    uploadPDF(pdfUri);

                } else {

                    Toast.makeText(AddcurriculoPerfil.this, "Please select a file!", Toast.LENGTH_SHORT).show();

                }

            }
        });




        concluido.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddcurriculoPerfil.this, CurriculoPerfil.class);
                startActivity(intent);
                AddcurriculoPerfil.this.finish();

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 5699 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            selectPDF();

        } else {

            Toast.makeText(this, "Please allow external storage reading!", Toast.LENGTH_SHORT).show();

        }

    }

    private void selectPDF() {

        Intent intent = new Intent();

        intent.setType("application/pdf");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 9965);

    }

    private void uploadPDF(Uri pdfUri) {

        progressDialog = new ProgressDialog(AddcurriculoPerfil.this);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDialog.setTitle("Uploading File...");

        progressDialog.setProgress(0);

        progressDialog.show();


        final String fileName = System.currentTimeMillis()+" ";

        StorageReference mySto = mStorage.getReference();

        mySto.child("Uploads").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();

                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String url = uri.toString();
                                System.out.println(url);
                                userid = auth.getCurrentUser().getUid();
                                documentReference = fstore.collection("candidatos").document(userid);

                                // INSERIR AQUI A CONEXÃO COM O NOSSO FIREBASE

                                mapuser.put("uriCurriculo", url);
                                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        System.out.println("Funcionou!");
                                        progressDialog.setTitle("Upload ready");
                                        progressDialog.dismiss();
                                    }
                                });


                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AddcurriculoPerfil.this, "ERROR! Something went wrong try again.", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                progressDialog.setProgress(currentProgress);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9965 && resultCode == RESULT_OK && data != null) {

            pdfUri = data.getData();

            mFileStatus.setText("Selected File: " + data.getData().getLastPathSegment());

        } else {

            Toast.makeText(this, "Please select a file!", Toast.LENGTH_SHORT).show();

        }

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
                Intent intent = new Intent(AddcurriculoPerfil.this, Vagas.class);
                startActivity(intent);
                AddcurriculoPerfil.this.onPause();
                break;
            case R.id.nav_sair:
                Intent i = new Intent(AddcurriculoPerfil.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
            case R.id.nav_sobre:
                Intent intent1 = new Intent(AddcurriculoPerfil.this, SobreNos.class);
                startActivity(intent1);
                AddcurriculoPerfil.this.onPause();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

