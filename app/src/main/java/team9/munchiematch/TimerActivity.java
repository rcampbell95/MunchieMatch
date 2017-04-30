package team9.munchiematch;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.media.RingtoneManager;
import android.media.Ringtone;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    public boolean isPaused = false;
    private Button startButton;
    private Button resetButton;
    public long startTime = 30 * 1000;
    public long pauseTime = 0;
    private final long interval = 1 * 1000;
    public TextView text;
    public EditText timerInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        startButton = (Button) this.findViewById(R.id.startTimer);
        startButton.setOnClickListener(this);
        //startButton.setEnabled(false); // Initially disable start button until a time is entered
        resetButton = (Button) this.findViewById(R.id.resetTimer);
        resetButton.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.elapsedTime);
        text.setText("0:00");
        timerInputText = (EditText) this.findViewById(R.id.timerInput);
    }

    public long getStartTime(){
        String inputText = timerInputText.getText().toString();
        if (inputText.isEmpty() || inputText == "" || inputText == null || inputText.length() == 0) {
            return 0;
        }
        else {
            return 60 * 1000 * Long.parseLong(inputText);
        }
    }

    public String timeToString (long startTime){
        long timeMinutes = startTime / 60000 % 60;
        String minutes = "" + timeMinutes;
        return "" + minutes + ":00";
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.startTimer:
                startTime = getStartTime();
                if (!timerHasStarted) { // if timer has not started...
                    if (!isPaused) {
                        countDownTimer = new MyCountDownTimer(startTime, interval);
                    } else {
                        countDownTimer = new MyCountDownTimer(pauseTime, interval);
                        isPaused = false;
                    }
                    countDownTimer.start();
                    timerHasStarted = true;
                    startButton.setText("Pause"); // so we want to call start again to resume...
                    // but we need to save current time
                    // countDownTimer.pause();

                } else { // Resumes timer
                    isPaused = true;
                    countDownTimer.cancel();
                    timerHasStarted = false;
                    startButton.setText("Resume");
                }

                break;
            case R.id.resetTimer:
                startButton.setText("Start");
                startTime = getStartTime();
                countDownTimer.cancel();
                text.setText(timeToString(startTime));
                isPaused = false;
                timerHasStarted = false;
                break;
        }
    }

    private void playDefaultNotificationSound() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            text.setText("Time's Up!");
            playDefaultNotificationSound();
            timerHasStarted = false;
        }
        @Override
        public void onTick(long millisUntilfinished) {
            long timeMinutes = millisUntilfinished / 60000 % 60;
            long timeSeconds = millisUntilfinished / 1000 % 60;
            String minutes = "" + timeMinutes;
            String seconds = "" + timeSeconds;

            if (timeSeconds < 10) // Solves te no leading 0 problem in seconds
            {
                text.setText("" + minutes + ":0" + seconds);
            } else {
                text.setText("" + minutes + ":" + seconds);
            }
            pauseTime = millisUntilfinished;
        }
    }

    Intent intent = getIntent(); //For MainActivity to Link to This Activity
    //Need to change intent to link to either UserProfileActivity or recipe activity
}
