package com.example.sqlitelogin_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText correo , password , repassword;
    Button btnRegistrar , btnLogin;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo =(EditText)findViewById(R.id.correo);
        password =(EditText)findViewById(R.id.password);
        repassword =(EditText)findViewById(R.id.repassword);

        btnRegistrar =(Button)findViewById(R.id.btnRegistrar);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        mydb = new DBHelper(this);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crreo = correo.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(crreo.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(MainActivity.this , "Rellene todas los campos" , Toast.LENGTH_SHORT).show();

                }else {
                    if (pass.equals(repass)){
                        Boolean correocheckResult = mydb.checkCorreo(crreo);

                        if (correocheckResult == false){
                           Boolean registResult = mydb.insertData(crreo , pass);
                            if (registResult == true){
                                Toast.makeText(MainActivity.this, "Registrado exitosamente..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Upps.. no se pudo registrar....", Toast.LENGTH_SHORT).show();
                            }
                        
                        }else{
                            Toast.makeText(MainActivity.this , "Este Correo ya existe.. " ,Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(MainActivity.this , "contraseña no coincide" , Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}