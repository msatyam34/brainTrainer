package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score =0;
    int noOfQuestions =0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    boolean flag = false;

    public void playAgain(View view){
            flag =true;

            score = 0;
            noOfQuestions = 0;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));
            timerTextView.setText("30s");
            playAgainButton.setVisibility(View.INVISIBLE);
            newQuestion();
            new CountDownTimer(30100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    resultTextView.setText("Done!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    flag = false;

                }
            }.start();





    }




    public void chooseAnswer (View view){
        if(flag) {

            if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct :)");
                score++;
            } else {
                resultTextView.setText("Wrong :(");
            }
            noOfQuestions++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));
            newQuestion();
        }

    }


    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();


        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);

            }
            else{
                int wrongAnswer = rand.nextInt(41);

                while(wrongAnswer==a+b){
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }



    public void start(View view){
        flag = true;
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.timerTextView));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView = findViewById(R.id.sumTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        goButton = findViewById(R.id.goButton);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton.setVisibility(View.VISIBLE);

        gameLayout.setVisibility(View.INVISIBLE);




    }
}