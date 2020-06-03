package br.edu.insper.al.anaccf5.preparoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firestore.admin.v1beta1.Progress;

public class Curriculo extends AppCompatActivity {

    Button selectFile, upload;
    TextView notification;
    Uri pdfUri;
    FirebaseStorage storage;
    FirebaseDatabase database;
//    ProgressDialog progressDialog;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculo);
        Button avancar = findViewById(R.id.continuar);
        Button voltar = findViewById(R.id.voltar);

        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Curriculo.this, Localizacao.class);
                startActivity(intent);
                Curriculo.this.onPause();
            }
        });

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Curriculo.this, Habilidades.class);
                startActivity(intent);
                onPause();

            }

            ;
        });

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        selectFile = findViewById(R.id.botao_curriculo);
        upload = findViewById(R.id.botao_upload);
        notification = findViewById(R.id.mensagem);
        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Curriculo.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectPdf();
                } else
                    ActivityCompat.requestPermissions(Curriculo.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }

        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdfUri != null)
                uploadFile(pdfUri);
                else
                    Toast.makeText(Curriculo.this,"Escolha um arquivo", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void uploadFile(Uri pdfUri) {
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setTitle("Fazendo Upload");
//        progressDialog.setProgress(0);
//        progressDialog.show();

        final String fileName = System.currentTimeMillis() + "";
        StorageReference storageReference = storage.getReference();
        storageReference.child("Uploads").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                DatabaseReference myRef = database.getReference();
                                myRef.child(fileName).setValue(url)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Curriculo.this, "File is uploaded", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(Curriculo.this, "File upload failed", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Curriculo.this, "Erro no upload", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
//                progressDialog.setProgress(currentProgress);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
        else
            Toast.makeText(Curriculo.this,"Por favor conceda o acesso aos seus arquivos",Toast.LENGTH_SHORT).show();
    }

    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            notification.setText("Arquivo selecionado: " + data.getData().getLastPathSegment());
        } else {
            Toast.makeText(Curriculo.this, "Por favor escolha seu arquivo", Toast.LENGTH_SHORT).show();
        }
    }

}
