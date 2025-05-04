package com.example.nationalanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnShow;
    View layoutMenu, layoutMenuBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnShow = findViewById(R.id.btnShow);
        layoutMenu = findViewById(R.id.layoutMenu);
        layoutMenuBackground = findViewById(R.id.layoutMenuBackground);

        Animation fromBottomToTopAnim = AnimationUtils.loadAnimation(this, R.anim.from_bottom_to_top);
        Animation fromTopToBottomAnim = AnimationUtils.loadAnimation(this, R.anim.from_top_to_bottom);
        btnShow.setOnClickListener(v->{
            layoutMenu.clearAnimation();
            layoutMenu.setVisibility(View.VISIBLE);
            layoutMenuBackground.setVisibility(View.VISIBLE);
            layoutMenu.startAnimation(fromBottomToTopAnim);
            setMarginBot(50);
        });
        layoutMenuBackground.setOnClickListener( v -> {
            layoutMenu.clearAnimation();
            layoutMenu.startAnimation(fromTopToBottomAnim);
            setMarginBot(0);
        });
        fromTopToBottomAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutMenuBackground.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void setMarginBot(int margin) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) layoutMenu.getLayoutParams();
        params.setMargins(0,0,0, margin);
        layoutMenu.setLayoutParams(params);
    }

    public void GoToScrollActivity(View view) {
        startActivity(new Intent(getApplicationContext(), ScrollAnimActivity.class));
    }
}