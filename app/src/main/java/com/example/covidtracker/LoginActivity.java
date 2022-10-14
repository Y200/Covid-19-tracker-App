package com.example.covidtracker;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import android.app.Activity;
//import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
//import android.view.View;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPass;
    private Button signInBtn;
    private Button signUpBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.mEmail);
        mPass = findViewById(R.id.mPass);
        signInBtn = findViewById(R.id.signInBtn);
        //    private TextView mTextView;
        signUpBtn = findViewById(R.id.signUpBtn);


        mAuth = FirebaseAuth.getInstance();

        signInBtn.setOnClickListener(view -> {
            String email = mEmail.getText().toString();
            String pass = mPass.getText().toString();

            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (!pass.isEmpty()){
                    mAuth.signInWithEmailAndPassword(email , pass)
                            .addOnSuccessListener(authResult -> {

                                Toast.makeText(LoginActivity.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                                finish();
                            }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show());
                }else{
                    mPass.setError("Empty Fields Are not Allowed");
                }
            }else if(email.isEmpty()){
                mEmail.setError("Empty Fields Are not Allowed");
            }else{
                mEmail.setError("Pleas Enter Correct Email");
            }

        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });
//        mTextView.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

    }

}