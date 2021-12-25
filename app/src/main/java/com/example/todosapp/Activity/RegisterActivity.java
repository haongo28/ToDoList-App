package com.example.todosapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todosapp.R;

public class RegisterActivity extends AppCompatActivity {

    ImageButton btnregisterback;
    EditText username, email, password, repassword;
    Button btnregister;
    DBLogin DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnregisterback = findViewById(R.id.to_register_back);
        username = findViewById(R.id.register_username1);
        password = findViewById(R.id.register_password1);
        email = findViewById(R.id.register_email1);
        repassword = findViewById(R.id.register_confirmpassword1);
        btnregister = findViewById(R.id.register_button);
        DB = new DBLogin(this);

        btnregisterback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);

                overridePendingTransition(R.anim.traiquaphai1,R.anim.traiquaphai2);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String emaill = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (user.equals("") || emaill.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        Boolean checkemail = DB.checkemail(emaill);
                        if (checkuser==false && checkemail==false) {
                            Boolean insert = DB.insertData(user, emaill, pass);
                            if (insert == true){
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(i);

                                overridePendingTransition(R.anim.phaiquatrai1,R.anim.phaiquatrai2);
                            } else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Email or Username existed", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
