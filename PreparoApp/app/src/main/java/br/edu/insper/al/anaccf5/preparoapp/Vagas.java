package br.edu.insper.al.anaccf5.preparoapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Vagas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vagas);


        //criação da ScrollView
        ScrollView sv = new ScrollView(this);
        FrameLayout.LayoutParams lpsv = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        sv.setBackgroundResource(R.drawable.grad_bg);
        sv.setLayoutParams(lpsv);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lpll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lpll);
        sv.addView(ll);

        for(int i=0;i<50;i++){

            CardView cardView = new CardView(this);
            CardView.LayoutParams lpcard = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            cardView.setLayoutParams(lpcard);
            ll.addView(cardView);

//            TextView tv = new TextView(this);
//            tv.setText("Exemplo Linha " + Integer.toString(i));
//            tv.setTextSize(30);
//
//            ll.addView(tv);

        }

        setContentView(sv);

    }
}