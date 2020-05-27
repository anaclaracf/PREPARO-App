package br.edu.insper.al.anaccf5.preparoapp;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Conexao {
    private static FirebaseAuth firebaseAuth;
    private static FirebaseUser firebaseUser;

    private Conexao(){

    }

    public static FirebaseAuth getFirebaseAuth(){
        if (firebaseAuth == null){
            inicializaFirebase();
        }
        return firebaseAuth;
    }

    private static void inicializaFirebase() {
        firebaseAuth = firebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    firebaseUser = user;
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public static FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }

    public static void logout(){
        firebaseAuth.signOut();
    }
}


