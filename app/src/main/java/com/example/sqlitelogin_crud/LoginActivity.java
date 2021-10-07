package com.example.sqlitelogin_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText correo , password;
    Button btnLogin ,btnRegistrar;

    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = (EditText)findViewById(R.id.correoLogin);
        password = (EditText)findViewById(R.id.passwordLogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);

        mydb = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String crreo = correo.getText().toString();
                String pass = password.getText().toString();

                if (crreo.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Por favor insertar las credenciales", Toast.LENGTH_SHORT).show();
                }else{
                  Boolean result =   mydb.checkCorreoPassword(crreo,pass);
                  if (result == true){
                      Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                  }else{
                      Toast.makeText(LoginActivity.this, "Credenciales Invalidas", Toast.LENGTH_SHORT).show();
                  }
                }

            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}