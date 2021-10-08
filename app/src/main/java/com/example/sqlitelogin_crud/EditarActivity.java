package com.example.sqlitelogin_crud;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlitelogin_crud.db.DbProductos;
import com.example.sqlitelogin_crud.entidades.Productos;

public class EditarActivity  extends AppCompatActivity {
    EditText txtNomprod , txtCategoria , txtPrecio , txtCantidad;
    Button btnGuarda;
    boolean correcto = false;
    Productos producto;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNomprod = findViewById(R.id.txtNomprod);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCantidad = findViewById(R.id.txtCantidad);
        btnGuarda = findViewById(R.id.btnGuarda);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbProductos dbProductos = new DbProductos(EditarActivity.this);
        producto = dbProductos.verProducto(id);

        if (producto != null){
            txtNomprod.setText(producto.getNomprod());
            txtCategoria.setText(producto.getCategoria());
            txtPrecio.setText(producto.getPrecio());
            txtCantidad.setText(producto.getCantidad());

        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (!txtNomprod.getText().toString().equals("") && !txtCategoria.getText().toString().equals("") && !txtPrecio.getText().toString().equals("")&& !txtCantidad.getText().toString().equals("")){
                      correcto =  dbProductos.editarProducto(id,txtNomprod.getText().toString(),txtCategoria.getText().toString(),txtPrecio.getText().toString() , txtCantidad.getText().toString());
                    if (correcto){
                        Toast.makeText(EditarActivity.this, "EL REGISTRO HA SIDO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
                    }
                    }else{
                        Toast.makeText(EditarActivity.this, "Debe llenar los campos obligatorios", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }

    private void verRegistro() {
        Intent intent = new Intent(this ,VerActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}
