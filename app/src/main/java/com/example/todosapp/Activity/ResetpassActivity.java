package com.example.todosapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todosapp.R;

public class ResetpassActivity extends AppCompatActivity {

    ImageButton btnforgotback;
    Button reset;
    EditText email, password, repassword;
    DBLogin DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btnforgotback = findViewById(R.id.to_resetpass_back);
        password = findViewById(R.id.forgot_password1);
        email = findViewById(R.id.forgot_email1);
        repassword = findViewById(R.id.forgot_confirmpassword1);
        reset = findViewById(R.id.resetpass_button);
        DB = new DBLogin(this);

        btnforgotback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResetpassActivity.this, LoginActivity.class);
                startActivity(i);

                overridePendingTransition(R.anim.traiquaphai1,R.anim.traiquaphai2);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (pass.equals(repass)) {
                    Boolean checkemail = DB.checkemail(emaill);
                    if (checkemail == true) {
                        Boolean checkpasswordupdate = DB.updatepassword(emaill, pass);
                        if (checkpasswordupdate == true) {
                            Intent i = new Intent(ResetpassActivity.this, LoginActivity.class);
                            startActivity(i);

                            overridePendingTransition(R.anim.traiquaphai1, R.anim.traiquaphai2);
                            Toast.makeText(ResetpassActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetpassActivity.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ResetpassActivity.this, "Email Error", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ResetpassActivity.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
