package team9.munchiematch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team9.munchiematch.R;

public class youveBeenMatched extends AppCompatActivity {

    private Button acceptButton;
    private Button rejectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youve_been_matched);
        acceptButton = (Button) this.findViewById(R.id.acceptButton);
        rejectButton = (Button) this.findViewById(R.id.rejectButton);

    }
    public void acceptPerson (View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
    public void rejectPerson(View view) {
        this.finish();
    }

}
