package org.d3ifcool.siberkah;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Registrasi extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNama;
    private Button button;
    private EditText editTextKtp;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPassword2;
    private TextView textViewlogin;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String nama;
    private String ktp;
    private String email;
    private ProgressBar progressBar;
    private ArrayList<UserInformation> usersArray;
    private TextView pesanGagal;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener mAuthlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usersArray = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
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
        currentUser = firebaseAuth.getCurrentUser();


    }

    @Override
    public void onClick(View view) {
        if (view == button){
            button.setVisibility(view.INVISIBLE);
            progressBar.setVisibility(view.VISIBLE);
            final String nama = editTextNama.getText().toString().trim();
            final String email = editTextEmail.getText().toString().trim();
            final String password1 = editTextPassword.getText().toString().trim();
            final String password2 = editTextPassword2.getText().toString().trim();
            final String ktp = editTextKtp.getText().toString().trim();

            if (nama.isEmpty() || email.isEmpty() || password1.isEmpty() || ktp.isEmpty() || password2.isEmpty()){

                showMessage("Please complete all field");
                button.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

            }else {
                createUserAccount(email,password1);
            }



        }if (view == textViewlogin){
            Intent intent = new Intent(Registrasi.this,Login.class);
            startActivity(intent);
        }
    }

    private void createUserAccount(final String email,final String password1) {
        firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Registrasi.this,"Account created", Toast.LENGTH_LONG).show();
                    showMessage("Account created");
                    updateUserInfo();
                }else {
                    Toast.makeText(Registrasi.this,"Account creation failed", Toast.LENGTH_LONG).show();
                    showMessage("Account creation failed"+task.getException());
                    button.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }


    private void updateUserInfo() {
        final DatabaseReference dbs = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid());
        dbs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nama = editTextNama.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password1 = editTextPassword.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();
                String ktp = editTextKtp.getText().toString().trim();
                UserInformation use = dataSnapshot.getValue(UserInformation.class);
                FirebaseUser users = firebaseAuth.getCurrentUser();
                String key = dbs.push().getKey();
                nama = use.getNamaUser();

                dataSnapshot.getRef().child("Id").setValue(currentUser.getUid());
                dataSnapshot.getRef().child("nama").setValue(nama);
                dataSnapshot.getRef().child("email").setValue(email);
                dataSnapshot.getRef().child("password").setValue(password1);
                dataSnapshot.getRef().child("ktp").setValue(ktp);




//                if (dataSnapshot.child("nama").exists()){
//                    nama = use.getNamaUser();
//                }if (dataSnapshot.child("email").exists()){
//                    email = use.getEmail();
//                }if (dataSnapshot.child("password").exists()){
//                    password1 = use.getPassUser();
//                }
//                if (dataSnapshot.child("ktp").exists()){
//                    ktp = use.getNoKtp();
//                }
//                final UserInformation user = new UserInformation(users.getUid(),nama,email,password1,ktp);
//                usersArray.add(user);
//                dbs.child(key).setValue(user);
//                dbs.push().setValue(usersArray);
                showMessage("Register complete");
                Intent intent = new Intent(Registrasi.this,Login.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Intent intent = new Intent(Registrasi.this,Login.class);
                startActivity(intent);
            }
        });
//        user.setId(currentUser.getUid());
//        user.setNamaUser(nama);
//        user.setEmail(email);
//        user.setPassUser(pass);
//        user.setNoKtp(ktp);
//        users.add(user);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
