package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class AreasInteresse extends AppCompatActivity {

    private TextView educacao, filosofia, artes, marketing, financas, direito, negocios, informatica,
            arquitetura, ciencias, saude, psicologia;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;
    String userid;

    Button avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_interesse);
        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userid = auth.getCurrentUser().getUid();
        final DocumentReference documentReference = fstore.collection("candidatos").document(userid);
        final Map<String, Object> mapuser = new HashMap<>();

        avancar = (Button) findViewById(R.id.continuar);

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AreasInteresse.this, Perfil.class);
                AreasInteresse.this.startActivity(intent);
                AreasInteresse.this.onPause();
            }
        });

        educacao = (TextView) findViewById(R.id.educacao);
        filosofia = (TextView) findViewById(R.id.filosofia);
        arquitetura = (TextView) findViewById(R.id.arquitetura);
        artes = (TextView) findViewById(R.id.artes);
        negocios = (TextView) findViewById(R.id.negocios);
        ciencias = (TextView) findViewById(R.id.ciencias);
        direito = (TextView) findViewById(R.id.direito);
        financas = (TextView) findViewById(R.id.financas);
        informatica = (TextView) findViewById(R.id.informatica);
        marketing = (TextView) findViewById(R.id.marketing);
        psicologia = (TextView) findViewById(R.id.psicologia);
        saude = (TextView) findViewById(R.id.saude);


        final ImageButton imagem1= findViewById(R.id.imagem1);
        final ImageButton imagem2= findViewById(R.id.imagem2);
        final ImageButton imagem3= findViewById(R.id.imagem3);
        final ImageButton imagem4= findViewById(R.id.imagem4);
        final ImageButton imagem5= findViewById(R.id.imagem5);
        final ImageButton imagem6= findViewById(R.id.imagem6);
        final ImageButton imagem7= findViewById(R.id.imagem7);
        final ImageButton imagem8= findViewById(R.id.imagem8);
        final ImageButton imagem9= findViewById(R.id.imagem9);
        final ImageButton imagem10= findViewById(R.id.imagem10);
        final ImageButton imagem11= findViewById(R.id.imagem11);
        final ImageButton imagem12= findViewById(R.id.imagem12);

        imagem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem1.setAlpha(0.9f);
                mapuser.put("interesses0", educacao.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });
            }
        });

        imagem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem2.setAlpha(0.9f);
                mapuser.put("interesses1", filosofia.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem3.setAlpha(0.9f);
                mapuser.put("interesses2", arquitetura.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });
            }
        });

        imagem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem4.setAlpha(0.9f);
                mapuser.put("interesses3", artes.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem5.setAlpha(0.9f);
                mapuser.put("interesses4", negocios.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem6.setAlpha(0.9f);
                mapuser.put("interesses5", ciencias.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem7.setAlpha(0.9f);
                mapuser.put("interesses6", direito.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem8.setAlpha(0.9f);
                mapuser.put("interesses7", financas.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem9.setAlpha(0.9f);
                mapuser.put("interesses8", informatica.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem10.setAlpha(0.9f);
                mapuser.put("interesses9", marketing.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem11.setAlpha(0.9f);
                mapuser.put("interesses10", psicologia.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });

        imagem12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem12.setAlpha(0.9f);
                mapuser.put("interesses11", saude.getText());
                documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Funcionou!");
                    }
                });

            }
        });



    }
}
