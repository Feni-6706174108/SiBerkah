package org.d3ifcool.siberkah;

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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button masukButton;
    private EditText emailEditText;
    private EditText passEditText;
    private TextView regisTextView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        masukButton = (Button) findViewById(R.id.buttonMasuk);
        emailEditText = (EditText) findViewById(R.id.editTextEmail);
        passEditText = (EditText) findViewById(R.id.editTextPass);
        regisTextView = (TextView) findViewById(R.id.textViewRegis);

        masukButton.setOnClickListener(this);
        regisTextView.setOnClickListener(this);

    }

    public void userLogin() {
        if (!validateForm()) {
            Toast.makeText(this, "lengkapi form", Toast.LENGTH_SHORT).show();
        } else {
            UserDB userDB = new UserDB(getApplicationContext());
            String email = emailEditText.getText().toString();
            String password = passEditText.getText().toString();
            UserInformation userInformation = userDB.login(email, password);
            if (userInformation == null) {
                Toast.makeText(this, "Akun tidak valid", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Berhail Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainMenu.class);
                startActivity(intent);
            }
        }


//        String email = emailEditText.getText().toString().trim();
//        String pass = passEditText.getText().toString().trim();
//
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "E-mail harus diisi", Toast.LENGTH_SHORT).show();
//            return;
//        }if (TextUtils.isEmpty(pass)){
//            Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        firebaseAuth.signInWithEmailAndPassword(email,pass)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            Intent intent = new Intent(Login.this,MainActivity.class);
//                            startActivity(intent);
//                        }
//                        else {
//                            Toast.makeText(Login.this,"gagal login",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

    @Override
    public void onClick(View v) {
        if (v == masukButton) {
            userLogin();
        }
        if (v == regisTextView) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(emailEditText.getText().toString())) {
            Toast.makeText(this, "Email harus diisi", Toast.LENGTH_SHORT).show();
            result = false;
        }else if (TextUtils.isEmpty(passEditText.getText().toString())) {
            Toast.makeText(this, "password harus diisi", Toast.LENGTH_SHORT).show();
        }else {
            return true;
        }
        return result;
    }
}
