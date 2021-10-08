package com.example.sqlitelogin_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.sqlitelogin_crud.adaptadores.ListaProductosAdapter;
import com.example.sqlitelogin_crud.db.DbProductos;
import com.example.sqlitelogin_crud.entidades.Productos;

import java.util.ArrayList;

public class ProductoListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
       SearchView txtBuscar;

        RecyclerView listaProductos;
        ArrayList<Productos> listaArrayContactos;
        ListaProductosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_list);
        listaProductos = findViewById(R.id.listaProductos);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(ProductoListActivity.this);
        listaArrayContactos = new ArrayList<>();
         adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        listaProductos.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }
    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
            nuevoRegistro();

            default:
              return  super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this ,ProductoActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}