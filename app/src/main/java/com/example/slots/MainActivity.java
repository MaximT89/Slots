package com.example.slots;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slots.imageViewScroling.IEventEnd;
import com.example.slots.imageViewScroling.ImageViewScrolling;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements IEventEnd {

    Button btn_down;
    ImageViewScrolling image1, image2, image3;
    TextView txt_score;

    int count_done = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        image1.setEventEnd(this);
        image2.setEventEnd(this);
        image3.setEventEnd(this);

        btn_down.setOnClickListener(v -> {

            btn_down.setEnabled(false);
            btn_down.setClickable(false);

            if(Common.SCORE >= 50){

                // Вот тут нужно выбрать конкретную картинку
                image1.setValueRandom(new Random().nextInt(6),
                        new Random().nextInt((15 - 5) + 1) + 5);

                image2.setValueRandom(new Random().nextInt(6),
                        new Random().nextInt((15 - 5) + 1) + 5);

                image3.setValueRandom(new Random().nextInt(6),
                        new Random().nextInt((15 - 5) + 1) + 5);

                Common.SCORE -= 50;

                txt_score.setText("Ваш счет : " + Common.SCORE);

            }else {
                Toast.makeText(this, "You not enough money", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void initView() {
        btn_down = findViewById(R.id.btn_down);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        txt_score = findViewById(R.id.txt_score);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void eventEnd(int result, int count) {

        btn_down.setEnabled(true);
        btn_down.setClickable(true);

        if(count_done < 2){
            count_done++;
        }else {

            count_done = 0;

            if(image1.getValue() == image2.getValue() && image2.getValue() == image3.getValue()){

                Toast.makeText(this, "You win big prize", Toast.LENGTH_SHORT).show();
                Common.SCORE += 1000000;

                txt_score.setText("Ваш счет : " + Common.SCORE);
            }

        }
    }
}