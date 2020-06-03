package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class AreasInteresse extends AppCompatActivity {

    private TextView educacao, filosofia, artes, marketing, financas, direito, negocios, informatica,
            arquitetura, ciencias, saude, psicologia;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;

    String userid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_interesse);
        Button irperfil = findViewById(R.id.ir_perfil);

        Button voltar = findViewById(R.id.voltar);
        Button avancar = findViewById(R.id.continuar);

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AreasInteresse.this, Perfil.class);
                AreasInteresse.this.startActivity(intent);
                AreasInteresse.this.onPause();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AreasInteresse.this, Olimpiadas.class);
                AreasInteresse.this.startActivity(intent);
                AreasInteresse.this.onPause();
            }
        });


//                Intent intent = new Intent(AreasInteresse.this, Perfil.class);
//                AreasInteresse.this.startActivity(intent);
//                AreasInteresse.this.onPause();
                auth = FirebaseAuth.getInstance();

                educacao = (TextView) findViewById(R.id.educacao);
                filosofia = (TextView) findViewById(R.id.filosofia);
                artes = (TextView) findViewById(R.id.artes);
                marketing = (TextView) findViewById(R.id.marketing);
                financas = (TextView) findViewById(R.id.financas);
                direito = (TextView) findViewById(R.id.direito);
                negocios = (TextView) findViewById(R.id.negocios);
                arquitetura = (TextView) findViewById(R.id.arquitetura);
                informatica = (TextView) findViewById(R.id.informatica);
                ciencias = (TextView) findViewById(R.id.ciencias);
                saude = (TextView) findViewById(R.id.saude);
                psicologia = (TextView) findViewById(R.id.psicologia);

                fstore = FirebaseFirestore.getInstance();
                userid = auth.getCurrentUser().getUid();
                avancar = (Button) findViewById(R.id.continuar);

                final DocumentReference documentReference = fstore.collection("candidatos").document(userid);
                final Map<String, Object> mapuser = new HashMap<>();

                educacao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mapuser.put("interesses", educacao.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                filosofia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses1", filosofia.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                artes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses2", artes.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                marketing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses3", marketing.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                financas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses4", financas.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                direito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses5", direito.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                negocios.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses6", negocios.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                arquitetura.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses7", arquitetura.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                informatica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses8", informatica.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                ciencias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses9", ciencias.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                saude.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses10", saude.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });

                psicologia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mapuser.put("interesses11", psicologia.getText());
                        documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Funcionou!");
                            }
                        });

                    }
                });


                avancar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(AreasInteresse.this, Perfil.class);
                        startActivity(intent);
                        AreasInteresse.this.onPause();

                    }
                });


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

            }
        });

        imagem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem2.setAlpha(0.9f);

            }
        });

        imagem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem3.setAlpha(0.9f);

            }
        });

        imagem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem4.setAlpha(0.9f);

            }
        });

        imagem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem5.setAlpha(0.9f);

            }
        });

        imagem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem6.setAlpha(0.9f);

            }
        });

        imagem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem7.setAlpha(0.9f);

            }
        });

        imagem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem8.setAlpha(0.9f);

            }
        });

        imagem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem9.setAlpha(0.9f);

            }
        });

        imagem10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem10.setAlpha(0.9f);

            }
        });

        imagem11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem11.setAlpha(0.9f);

            }
        });

        imagem12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem12.setAlpha(0.9f);

            }
        });

    }

}

