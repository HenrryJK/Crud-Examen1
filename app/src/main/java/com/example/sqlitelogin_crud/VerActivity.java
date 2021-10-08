package com.example.sqlitelogin_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlitelogin_crud.db.DbProductos;
import com.example.sqlitelogin_crud.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {
    EditText txtNomprod , txtCategoria , txtPrecio , txtCantidad;
    Button btnGuarda;
    FloatingActionButton fabEditar;
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
        fabEditar = findViewById(R.id.fabEditar);

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

        DbProductos dbProductos = new DbProductos(VerActivity.this);
        producto = dbProductos.verProducto(id);

        if (producto != null){
            txtNomprod.setText(producto.getNomprod());
            txtCategoria.setText(producto.getCategoria());
            txtPrecio.setText(producto.getPrecio());
            txtCantidad.setText(producto.getCantidad());
            btnGuarda.setVisibility(View.INVISIBLE);

            txtNomprod.setInputType(InputType.TYPE_NULL);
            txtCategoria.setInputType(InputType.TYPE_NULL);
            txtPrecio.setInputType(InputType.TYPE_NULL);
            txtCantidad.setInputType(InputType.TYPE_NULL);

        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this , EditarActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }
}