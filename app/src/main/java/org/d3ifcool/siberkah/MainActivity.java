package org.d3ifcool.siberkah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNama;
    private Button button;
    private EditText editTextKtp;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPassword2;
    private TextView textViewlogin;
    //    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabaseReference;
    private String nama;
    private String ktp;
    private String password1;
    private String password2;
    private String email;
    private ArrayList<UserInformation> users;
    private ProgressBar progressBar;
    private TextView pesanGagal;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();
//        firebaseAuth = FirebaseAuth.getInstance();
        button = (Button) findViewById(R.id.button2);
        editTextNama = (EditText) findViewById(R.id.nama);
        editTextKtp = (EditText) findViewById(R.id.ktp);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.pass);
        editTextPassword2 = (EditText) findViewById(R.id.password);
        textViewlogin = (TextView) findViewById(R.id.textViewlogin);
        button.setOnClickListener((View.OnClickListener) this);
        textViewlogin.setOnClickListener(this);
        progressBar = new ProgressBar(this);
//        currentUser = firebaseAuth.getCurrentUser();

    }

    public void regis() {
        if (!validateForm()) {
            Toast.makeText(this, "lengkapi form", Toast.LENGTH_SHORT).show();
        } else {
            try {
                UserDB userDB = new UserDB(getApplicationContext());
                UserInformation userInformation = new UserInformation();
                userInformation.setEmail(editTextEmail.getText().toString());
                userInformation.setNamaUser(editTextNama.getText().toString());
                userInformation.setNoKtp(editTextKtp.getText().toString());
                userInformation.setPassUser(editTextPassword.getText().toString());
                UserInformation temp = userDB.
                if (userDB.create(userInformation)) {
                    Toast.makeText(this, "akun berhasil dibuat", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "akun tidak berhasil dibuat", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == button) {
            regis();
        }
        if (view == textViewlogin) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(editTextNama.getText().toString())) {
            Toast.makeText(this, "nama harus diisi", Toast.LENGTH_SHORT).show();
            result = false;
        } else if (TextUtils.isEmpty(editTextEmail.getText().toString())) {
            Toast.makeText(this, "Email harus diisi", Toast.LENGTH_SHORT).show();
            result = false;
        } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
            Toast.makeText(this, "password harus diisi", Toast.LENGTH_SHORT).show();
        } else if (editTextPassword.getText().toString().length() < 6) {
            Toast.makeText(this, "Password harus lebih dari 6 character", Toast.LENGTH_SHORT).show();
            result = false;
        } else if (TextUtils.isEmpty(editTextPassword2.getText().toString())) {
            Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show();
            result = false;
        } else if (editTextPassword2.getText().toString().length() < 6) {
            Toast.makeText(this, "Password harus lebih dari 6 character", Toast.LENGTH_SHORT).show();
            result = false;
        } else if (!editTextPassword.getText().toString().equals(editTextPassword2.getText().toString())) {
            Toast.makeText(this, "Password tidak cocok", Toast.LENGTH_SHORT).show();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}
