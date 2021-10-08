package com.example.sqlitelogin_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitelogin_crud.db.DbProductos;

public class ProductoActivity extends AppCompatActivity {
    EditText txtNomprod , txtCategoria , txtPrecio , txtCantidad;
    Button btnGuarda;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        txtNomprod = findViewById(R.id.txtNomprod);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCantidad = findViewById(R.id.txtCantidad);
        btnGuarda = findViewById(R.id.btnGuarda);

        mydb = new DBHelper(this);


        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbProductos dbProductos = new DbProductos(ProductoActivity.this);
                long id = dbProductos.insertarProducto(txtNomprod.getText().toString(),txtCategoria.getText().toString(),txtPrecio.getText().toString(),txtCantidad.getText().toString());
                if (id  >  0){
                    Toast.makeText(ProductoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(ProductoActivity.this, "REGISTRO ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void limpiar () {
        txtNomprod.setText("");
        txtCategoria.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
    }
}
