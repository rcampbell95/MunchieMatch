package team9.munchiematch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener{

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startButton;
    private Button pauseButton; // this one is mine
    public long startTime = 30 * 1000;
    private final long interval = 1 * 1000;
    public TextView text;
    public EditText timerInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        startButton = (Button) this.findViewById(R.id.startTimer);
        startButton.setOnClickListener(this);
        //pauseButton = (Button) this.findViewById(R.id.pauseTimer); // mine
        //pauseButton.setOnClickListener(this); // mine
        timerInputText = (EditText) this.findViewById(R.id.timerInput);
        text = (TextView) this.findViewById(R.id.elapsedTime);
        text.setText(text.getText() + String.valueOf(startTime / 1000));
    }

     @Override
     public void onClick (View v) {

         startTime = Long.parseLong(timerInputText.getText().toString()); // alright it worked saved user input into startTime
         countDownTimer = new MyCountDownTimer(startTime, interval);

         if (!timerHasStarted){
             countDownTimer.start();
             timerHasStarted = true;
             startButton.setText("PAUSE");
         }
         else{
             countDownTimer.cancel();
             timerHasStarted = false;
             startButton.setText("RESET");
         }
     }


/**
    public void onClick(View v) {
        // do something when the button is clicked
        // Yes we will handle click here but which button clicked??? We don't know so we make switch cases
        switch (v.getId() {
            case R.id.startTimer:
                // do something when the startTimer is clicked
                if (!timerHasStarted) {
                    countDownTimer.start();
                    timerHasStarted = true;
                    startButton.setText("RESET");
                }
                else {
                    break; // do nothing
                }
                break;
            case R.id.pauseTimer:
                    countDownTimer.cancel();
                    timerHasStarted = false;
                    startButton.setText("START");
                // do something when the resetTimer is clicked
                break;
            default:
                break;
        }
    }
 */

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish(){
            text.setText("Time's Up!");
        }
        @Override
        public void onTick(long millisUntilfinished){
            text.setText("" + millisUntilfinished / 1000);
        }
    }

    Intent intent = getIntent(); //For MainActivity to Link to This Activity

}
