package br.edu.insper.al.anaccf5.preparoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Identificacao extends AppCompatActivity {

    private FirebaseFirestore fstore;
    private FirebaseUser user;

    String numeroid;
    String cpf;
    String dataNascimento;
    String nacionalidade;
    String estadoCivil;
    String genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identificacao);
        final EditText CPF = findViewById(R.id.cpf);
        final EditText nascimento = findViewById(R.id.data_nascimento);
        final EditText nacionalidadeUser = findViewById(R.id.nacionalidade);
        final EditText eCivil = findViewById(R.id.estado_civil);
        final EditText generoUser = findViewById(R.id.genero);
        Button avancar= findViewById(R.id.continuar);
        Button voltar= findViewById(R.id.voltar);
        Button preecherDepois=findViewById(R.id.preencherDepois);

        fstore = FirebaseFirestore.getInstance();



        final Calendar myCalendar = Calendar.getInstance();
        final EditText edittext= (EditText) findViewById(R.id.data_nascimento);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                edittext.setText(sdf.format(myCalendar.getTime()));
            }



        };

        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Identificacao.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Identificacao.this, InformacoesPessoais.class);
                startActivity(intent);
                Identificacao.this.onPause();

            }
        });
        preecherDepois.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Identificacao.this, Perfil.class);
                startActivity(intent);
                Identificacao.this.onPause();

            }
        });

        avancar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cpf = CPF.getText().toString().trim();
                dataNascimento = nascimento.getText().toString().trim();
                nacionalidade = nacionalidadeUser.getText().toString().trim();
                estadoCivil = eCivil.getText().toString().trim();
                genero = generoUser.getText().toString().trim();

                if  (cpf.isEmpty() || dataNascimento.isEmpty() || nacionalidade.isEmpty() || estadoCivil.isEmpty() || genero.isEmpty()){
                    alert("Preencha os dados obrigatórios");
                } else {

                    numeroid = user.getUid();
                    DocumentReference documentReference = fstore.collection("candidatos").document(numeroid);
                    Map<String,Object> mapuser = new HashMap<>();
                    mapuser.put("cpf", cpf);
                    mapuser.put("data de nascimento", dataNascimento);
                    mapuser.put("nacionalidade", nacionalidade);
                    mapuser.put("estado civil", estadoCivil);
                    mapuser.put("genero", genero);
                    documentReference.update(mapuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Funcionou!");
                        }
                    });
                    Intent intent = new Intent(Identificacao.this, FormacaoAcademica.class);
                    startActivity(intent);
                    Identificacao.this.onPause();
                }
            }
        });
    }

    private void alert(String msg) {
        Toast.makeText(Identificacao.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        numeroid = user.getUid();
        verificaUser();
    }

    private void verificaUser() {
        if (user == null){
            finish();
        }
    }
}
