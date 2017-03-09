package team9.munchiematch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Vibrator;


public class TimerActivity extends AppCompatActivity implements View.OnClickListener {

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startButton;
    private Button pauseButton; // this one is mine
    public long startTime = 0* 30 * 1000;
    private final long interval = 1 * 1000;
    public TextView text;
    public EditText timerInputText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        startButton = (Button) this.findViewById(R.id.startTimer);
        startButton.setOnClickListener(this);
        timerInputText = (EditText) this.findViewById(R.id.timerInput);
        text = (TextView) this.findViewById(R.id.elapsedTime);
        //text.setText(text.getText() + String.valueOf(startTime / 1000));
        text.setText("0:00");
    }

    @Override
    public void onClick (View v) {
        startTime = 60 * 1000 * Long.parseLong(timerInputText.getText().toString());

        if (!timerHasStarted){
            countDownTimer = new MyCountDownTimer(startTime, interval);
            countDownTimer.start();
            timerHasStarted = true;
            startButton.setText("PAUSE");
        }
        else{
            countDownTimer.cancel();
            startButton.setText("NewTime");
            timerHasStarted = false;
        }
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish(){
            text.setText("Time's Up!");
            timerHasStarted = false;
            //Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            //v.vibrate(500);
        }
        @Override
        public void onTick(long millisUntilfinished){
            long timeMinutes = millisUntilfinished / 60000 % 60;
            long timeSeconds = millisUntilfinished / 1000 % 60;
            String minutes =  "" + timeMinutes;
            String seconds = "" + timeSeconds;

            if(timeSeconds <10) // Solves te no leading 0 problem in seconds
            {
                text.setText("" + minutes + ":0" + seconds);
            }
            else
            {
                text.setText("" + minutes + ":" + seconds);
            }


        }
    }

    Intent intent = getIntent(); //For MainActivity to Link to This Activity
}
