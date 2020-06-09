package br.edu.insper.al.anaccf5.preparoapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Vagas extends AppCompatActivity {

    ScrollView sv;
    LinearLayout ll;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vagas);

        sv = (ScrollView) findViewById(R.id.sv);
        ll = (LinearLayout) findViewById(R.id.ll);

        mContext = getApplicationContext();

        for(int i=0;i<5;i++){

            CardView card = new CardView(mContext);

//            CardView.LayoutParams layoutParams = (CardView.LayoutParams) card.getLayoutParams();

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            card.setLayoutParams(params);

            card.setContentPadding(30, 30, 40, 30);
            card.setMaxCardElevation(5);
            card.setCardElevation(9);

            card.setRadius(15);

            ll.addView(card);

            TextView tv = new TextView(this);
            tv.setText("Exemplo Linha djjfkdjfd " + Integer.toString(i));
            tv.setTextSize(30);

            card.addView(tv);

            TextView tv2 = new TextView(this);
            tv2.setTextSize(15);

            ll.addView(tv2);

        }
    }
}