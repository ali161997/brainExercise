package com.example.alihashem.brainexcersize;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView show_sum;
    Button button0,button1,button2,button3;
    TextView Scor_text_view;
    int first_number;
    int second_number;
    int correctPosition;
    int correctAnswer;
    int Wrong_answer;
    TextView TimerTextView;
    Button PlayAgain;
    TextView result;
    android.support.v7.widget.GridLayout gridLayout;
    int Score=0;
    int numberOfQuestion=0;
    ArrayList<Integer>results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimerTextView=(TextView)findViewById(R.id.timerTextView);
        PlayAgain=(Button)findViewById(R.id.play_agian);
        PlayAgain.setVisibility(View.INVISIBLE);
        gridLayout=(android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);
        show_sum=(TextView)findViewById(R.id.QTextView) ;
        result=(TextView)findViewById(R.id.resultTextView);
        Scor_text_view=(TextView)findViewById(R.id.ScoreTextView);
        button0=(Button)findViewById(R.id.result0);
        button1=(Button)findViewById(R.id.result1);
        button2=(Button)findViewById(R.id.result2);
        button3=(Button)findViewById(R.id.result3);
        results=new ArrayList<Integer>();
        newQuestion();
        Timer();



    }
    public void PlayAgain(View view)
    {
        Score=0;
        numberOfQuestion=0;
        TimerTextView.setText("30");
        Scor_text_view.setText(Score+" /"+numberOfQuestion);
        gridLayout.setVisibility(View.VISIBLE);
        newQuestion();
        result.setText("");

        PlayAgain.setVisibility(View.INVISIBLE);
        Timer();
    }
    public void Timer()
    {
        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimerTextView.setText(""+millisUntilFinished/1000);


            }

            @Override
            public void onFinish() {
                result.setText("Done");
                MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.horn);
                mediaPlayer.start();
                gridLayout.setVisibility(View.INVISIBLE);
                PlayAgain.setVisibility(View.VISIBLE);

            }
        }.start();
    }
    public void getResult(View view)
    {
        if(Integer.toString(correctPosition).equals(view.getTag()))
        {
            result.setText("Correct");
            Score++;

        }
        else
        {
            result.setText("Wrong");
        }
        numberOfQuestion++;
        Scor_text_view.setText(Score+" /"+numberOfQuestion);
        newQuestion();



    }
    public void newQuestion()
    {
        Random rand=new Random();
        first_number= rand.nextInt(21);
        second_number=rand.nextInt(21);
        correctAnswer=first_number+second_number;
        correctPosition=rand.nextInt(4);
        show_sum.setText(first_number+" +"+second_number);
        results.clear();
        for(int i=0;4>i;i++)
        {
            if(i==correctPosition)
            {

                results.add(correctAnswer);
            }
            else
            {
                Wrong_answer=rand.nextInt(41);
                while(Wrong_answer==correctAnswer)
                {
                    Wrong_answer=rand.nextInt(41);
                }
                results.add(Wrong_answer);

            }


        }
        button0.setText(Integer.toString(results.get(0)));
        button1.setText(Integer.toString(results.get(1)));
        button2.setText(Integer.toString(results.get(2)));
        button3.setText(Integer.toString(results.get(3)));




    }
}
