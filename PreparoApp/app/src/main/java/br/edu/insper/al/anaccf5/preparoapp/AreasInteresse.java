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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class AreasInteresse extends AppCompatActivity {

    private TextView educacao, filosofia, artes, marketing, financas, direito, negocios, informatica,
            arquitetura, ciencias, saude, psicologia;

    private FirebaseFirestore fstore;
    private FirebaseAuth auth;
    String userid;
    int check1= 1;
    int check2= 1;
    int check3= 1;
    int check4= 1;
    int check5= 1;
    int check6= 1;
    int check7= 1;
    int check8= 1;
    int check9= 1;
    int check10= 1;
    int check11= 1;
    int check12= 1;


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
        Button voltar= findViewById(R.id.voltar);
        Button preecherDepois=findViewById(R.id.preencherDepois);

        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AreasInteresse.this, Olimpiadas.class);
                startActivity(intent);
                AreasInteresse.this.onPause();

            }
        });

        preecherDepois.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AreasInteresse.this, Perfil.class);
                startActivity(intent);
                AreasInteresse.this.onPause();

            }
        });

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

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                for (String interes: documentSnapshot.getData().keySet()){
                    if (interes.equals("interesse0")){
                        check1 = 0;
                        imagem1.setAlpha(0.9f);
                    } else if (interes.equals("interesse1")){
                        check2 = 0;
                        imagem2.setAlpha(0.9f);
                    } if (interes.equals("interesse2")){
                        check3 = 0;
                        imagem3.setAlpha(0.9f);
                    } if (interes.equals("interesse3")){
                        check4 = 0;
                        imagem4.setAlpha(0.9f);
                    } if (interes.equals("interesse4")){
                        check5 = 0;
                        imagem5.setAlpha(0.9f);
                    } if (interes.equals("interesse5")){
                        check6 = 0;
                        imagem6.setAlpha(0.9f);
                    } if (interes.equals("interesse6")){
                        check7 = 0;
                        imagem7.setAlpha(0.9f);
                    } if (interes.equals("interesse7")){
                        check8 = 0;
                        imagem8.setAlpha(0.9f);
                    } if (interes.equals("interesse8")){
                        check9 = 0;
                        imagem9.setAlpha(0.9f);
                    } if (interes.equals("interesse9")){
                        check10 = 0;
                        imagem10.setAlpha(0.9f);
                    } if (interes.equals("interesse10")){
                        check11 = 0;
                        imagem11.setAlpha(0.9f);
                    } if (interes.equals("interesse11")) {
                        check12 = 0;
                        imagem12.setAlpha(0.9f);
                    }
                }
            }
        });

        imagem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check1==1){

                    imagem1.setAlpha(0.9f);
                    mapuser.put("interesse0", educacao.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check1=0;

                } else{

                    mapuser.put("interesse0", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check1=1;
                    imagem1.setAlpha(0.6f);


                }

            }
        });


        imagem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check2==1){

                    imagem2.setAlpha(0.9f);
                    mapuser.put("interesse1", filosofia.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check2=0;

                }else{

                    mapuser.put("interesse1", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check2=1;
                    imagem2.setAlpha(0.6f);


                }

            }
        });
        imagem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check3==1){

                    imagem3.setAlpha(0.9f);
                    mapuser.put("interesse2", arquitetura.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check3=0;

                }else{

                    mapuser.put("interesse2", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check3=1;
                    imagem3.setAlpha(0.6f);


                }

            }
        });

        imagem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check4==1){

                    imagem4.setAlpha(0.9f);
                    mapuser.put("interesse3", artes.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check4=0;

                }else{

                    mapuser.put("interesse3", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check4=1;
                    imagem4.setAlpha(0.6f);


                }

            }
        });
        imagem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check5==1){

                    imagem5.setAlpha(0.9f);
                    mapuser.put("interesse4", negocios.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check5=0;

                }else{

                    mapuser.put("interesse4", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check5=1;
                    imagem5.setAlpha(0.6f);


                }

            }
        });

        imagem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check6==1){

                    imagem6.setAlpha(0.9f);
                    mapuser.put("interesse5", ciencias.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check6=0;

                }else{

                    mapuser.put("interesse5", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check6=1;
                    imagem6.setAlpha(0.6f);


                }

            }
        });

        imagem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check7==1){

                    imagem7.setAlpha(0.9f);
                    mapuser.put("interesse6", direito.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check7=0;

                }else{

                    mapuser.put("interesse6", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check7=1;
                    imagem7.setAlpha(0.6f);


                }

            }
        });

        imagem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check8==1){

                    imagem8.setAlpha(0.9f);
                    mapuser.put("interesse7", financas.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check8=0;

                }else{

                    mapuser.put("interesse7", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check8=1;
                    imagem8.setAlpha(0.6f);


                }

            }
        });

        imagem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check9==1){

                    imagem9.setAlpha(0.9f);
                    mapuser.put("interesse8", informatica.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check9=0;

                }else{

                    mapuser.put("interesse8", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check9=1;
                    imagem9.setAlpha(0.6f);


                }

            }
        });

        imagem10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check10==1){

                    imagem10.setAlpha(0.9f);
                    mapuser.put("interesse9", marketing.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check10=0;

                }else{

                    mapuser.put("interesse9", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check10=1;
                    imagem10.setAlpha(0.6f);


                }

            }
        });

        imagem11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check11==1){

                    imagem11.setAlpha(0.9f);
                    mapuser.put("interesse10", psicologia.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check11=0;

                }else{

                    mapuser.put("interesse10", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check11=1;
                    imagem11.setAlpha(0.6f);


                }

            }
        });

        imagem12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check12==1){

                    imagem12.setAlpha(0.9f);
                    mapuser.put("interesse11", saude.getText());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    check12=0;

                }else{

                    mapuser.put("interesse11", FieldValue.delete());
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });

                    check12=1;
                    imagem12.setAlpha(0.6f);


                }

            }
        });




    }
}
