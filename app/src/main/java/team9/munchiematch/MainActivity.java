package team9.munchiematch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /** Called when user hits goToTimerButton */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

<<<<<<< HEAD
=======
    public void changeActivity(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
>>>>>>> 7afa155393c9edabc423ac96018345dad6837c3c
}
