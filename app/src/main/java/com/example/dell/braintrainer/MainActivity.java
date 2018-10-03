package com.example.dell.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView aTextView;
    TextView bTextView;
    TextView cTextView;
    TextView dTextView;

    TextView timerTextView;
    TextView scoreTextView;
    TextView questionTextView;

    TextView welcomeTextView;
    TextView descriptionTextView;

    Button startbutton;

    LinearLayout StartLayout;
    ConstraintLayout GameLayout;


    Random random;

    int state=5;
    int questionNumber,correctAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton=(Button)findViewById(R.id.startButton);
        aTextView=(TextView)findViewById(R.id.atextView);
        bTextView=(TextView)findViewById(R.id.btextView);
        cTextView=(TextView)findViewById(R.id.ctextView);
        dTextView=(TextView)findViewById(R.id.dtextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        questionTextView=(TextView)findViewById(R.id.questionTextView);
        welcomeTextView=(TextView)findViewById(R.id.welcomeTextView);
        descriptionTextView=(TextView)findViewById(R.id.descriptionTextView);
        random=new Random();
        StartLayout=(LinearLayout)findViewById(R.id.StartLayout);
        GameLayout=(ConstraintLayout)findViewById(R.id.GameLayout);





    }


    public void updateOption(){
        questionNumber++;
        int temprandom = random.nextInt(4);
        int optionConfuse=random.nextInt(20);
        int num1 = random.nextInt(40);
        int num2 = random.nextInt(40);
        int ans=num1+num2;
        questionTextView.setText(Integer.toString(num1)+" + "+Integer.toString(num2)+" = ?");
        if (temprandom==0){
            aTextView.setText(Integer.toString(ans));
            bTextView.setText(Integer.toString((ans+optionConfuse)));
            cTextView.setText(Integer.toString((ans+optionConfuse)));
            dTextView.setText(Integer.toString((ans-optionConfuse)));
            state=0;
        }
        if (temprandom==1){
            bTextView.setText(Integer.toString(ans));
            cTextView.setText(Integer.toString((ans+optionConfuse)));
            aTextView.setText(Integer.toString((ans+optionConfuse)));
            dTextView.setText(Integer.toString((ans-optionConfuse)));
            state=1;
        }
        if (temprandom==2){
            cTextView.setText(Integer.toString(ans));
            dTextView.setText(Integer.toString((ans+optionConfuse)));
            aTextView.setText(Integer.toString((ans+optionConfuse)));
            bTextView.setText(Integer.toString((ans-optionConfuse)));
            state=2;
        }
        if (temprandom==3){
            dTextView.setText(Integer.toString(ans));
            aTextView.setText(Integer.toString((ans+optionConfuse)));
            bTextView.setText(Integer.toString((ans+optionConfuse)));
            cTextView.setText(Integer.toString((ans-optionConfuse)));
            state=3;
        }






    }



    public void optionClick(View view){
        int getTag;

        getTag= Integer.parseInt(view.getTag().toString());
        if (getTag==state){
            correctAns++;
            updateOption();
            updateScore();
        }
        else {
            updateOption();
            updateScore();
        }
    }

    public void startButton(View view){
        correctAns=0;
        questionNumber=0;
        updateOption();
        updateScore();
        StartLayout.setVisibility(View.INVISIBLE);
        GameLayout.setVisibility(View.VISIBLE);
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                updateTimer((int) ((millisUntilFinished+100)/1000));
            }

            @Override
            public void onFinish() {
                StartLayout.setVisibility(View.VISIBLE);
                GameLayout.setVisibility(View.INVISIBLE);
                welcomeTextView.setText("Score : "+Integer.toString(correctAns)+"/"+Integer.toString(questionNumber));
                startbutton.setText("Play Again");

            }
        }.start();


    }

    public void updateTimer(int second){
        String secondsString;
        if (second<=9){
            secondsString = "0"+Integer.toString(second);
        }else{
            secondsString=Integer.toString(second);
        }
        timerTextView.setText(secondsString);
    }



    public void updateScore(){
        scoreTextView.setText(Integer.toString(correctAns)+"/"+Integer.toString(questionNumber));
    }

}
