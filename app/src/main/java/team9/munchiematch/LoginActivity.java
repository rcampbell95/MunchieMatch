package team9.munchiematch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail;
    private EditText etPassword;
    private Button bSignIn;
    private TextView tvSignUp;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();



        //Check if user is logged in
//        if(firebaseAuth.getCurrentUser() != null){
//            //profile activity here When user is login
//            finish();
//            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
//        }

        etEmail = (EditText) findViewById(R.id.etEmailAddress);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bSignIn = (Button) findViewById(R.id.bSignIn);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);

        progressDialog = new ProgressDialog(this);

        bSignIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
    }


    //UserLogin Method
    private void userLogin(){

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();


        //check if email and passwords fields are empty
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Let's get Munchie Matching...");
        progressDialog.show(); // Show the message dialog

        firebaseAuth.signInWithEmailAndPassword(email, password)
                //Down below will be called when Sign in Method is complete
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            //start profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Could not Login...Please try again.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }


    @Override
    public void onClick(View v) {
        if(v == bSignIn) {
            userLogin();
        }
        if(v == tvSignUp){
            //"When register clicks "Don't have an account? Signup Here" -> RegisterActivity
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
