package com.bhavnish.firebaseauthdemoregistration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email_text, password_text;
    ProgressDialog progressDialog;
    Button btn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        firebaseAuth = FirebaseAuth.getInstance();

        email_text = (EditText) findViewById( R.id.editText );
        password_text = (EditText) findViewById( R.id.editText2 );
        btn = (Button) findViewById( R.id.button2 );

        btn.setOnClickListener( this );
        progressDialog = new ProgressDialog( this );

    }

    private void registerUser() {

        String email = email_text.getText().toString().trim();
        String password = email_text.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText( this, "Provide Email", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (password.isEmpty()) {

            Toast.makeText( this, "Provide Password", Toast.LENGTH_SHORT ).show();
            return;
        }

        progressDialog.setMessage( "Registring Pleas wait" );
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword( email, password )
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( MainActivity.this, "Successfully Registred", Toast.LENGTH_SHORT ).show();

                        } else {

                        }
                        progressDialog.dismiss();
                    }
                } );
    }

    @Override
    public void onClick(View v) {
        registerUser();
    }

}
