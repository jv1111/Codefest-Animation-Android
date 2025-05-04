package com.example.nationalanimation;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScrollAnimActivity extends AppCompatActivity {

    MotionLayout ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scroll_anim);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ml = findViewById(R.id.main);
        OnScrollAnimation();
    }

    int[] lastScrolly = {0};
    void OnScrollAnimation(){
        ScrollView sv = findViewById(R.id.scrollview);
        sv.getViewTreeObserver().addOnScrollChangedListener(() -> {
            int currentY = sv.getScrollY();
            if(lastScrolly[0] > currentY && ml.getCurrentState() == ml.getEndState()){
                Log.i("myTag scroll", "scrolling up");
                ml.transitionToStart();
            }else if(lastScrolly[0] < currentY && ml.getCurrentState() == ml.getStartState()){
                Log.i("myTag scroll", "scrolling down");
                ml.transitionToEnd();
            }
            lastScrolly[0] = currentY;
        });
    }
}