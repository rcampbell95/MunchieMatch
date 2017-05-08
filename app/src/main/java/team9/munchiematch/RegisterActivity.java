package team9.munchiematch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    //defining view objects
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextAge;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);


        //Edit Text fields to do the thing

        buttonRegister = (Button) findViewById(R.id.bRegister);

        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextUsername = (EditText) findViewById(R.id.etUserName);
        editTextPassword = (EditText) findViewById(R.id.etPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        editTextAge = (EditText) findViewById(R.id.etAge);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);


    }

    //RegisterUser() method implementation
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        //check if fields are empty
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        if(TextUtils.isEmpty(username)){
            //username field is empty
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(age)){
            //age is empty
            Toast.makeText(this, "Please enter age", Toast.LENGTH_SHORT).show();
            return;
        }
        //if validations are ok
        //we will first show a progressbar

        progressDialog.setMessage("Registering User...");
        progressDialog.show(); // Show the message dialog

        firebaseAuth.createUserWithEmailAndPassword(email,password) // see if user completes email and user fields
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            //profile activity start the profile
                            //right now let's display a toast
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), UserProfileFragment.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Could not register...Please try again.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });



    }

    @Override
    //Allow user to go to -> AnotherActivity
    public void onClick(View v) {
        if(v == buttonRegister) {
            registerUser();
        }

        if(v == textViewSignin) {
            //will open login activity
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}

