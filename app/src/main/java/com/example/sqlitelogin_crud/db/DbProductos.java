package com.example.sqlitelogin_crud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sqlitelogin_crud.DBHelper;
import com.example.sqlitelogin_crud.entidades.Productos;

import java.util.ArrayList;

public class DbProductos extends DBHelper {

        Context context;
    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarProducto(String nomprod , String categoria , String precio , String cantidad){
        long id = 0;
        try{
         DBHelper dbHelper = new DBHelper(context);
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put("nomprod" ,nomprod);
            values.put("categoria" ,categoria);
            values.put("precio" ,precio);
            values.put("cantidad" ,cantidad);
            id = db.insert(TABLE_PRODUCTOS , null , values);
        }catch (Exception ex){
            ex.toString();
        }
        return  id;
    }

    public ArrayList<Productos> mostrarProductos(){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Productos> listaContactos = new ArrayList<>();
        Productos producto = null;
        Cursor cursorProducto = null;

        cursorProducto = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS, null);
            // que el cursor pase al primer elemento o resultado que nos traiga la consulta
        if (cursorProducto.moveToFirst()){
            do {
                producto = new Productos();
                producto.setId(cursorProducto.getInt(0));
                producto.setNomprod(cursorProducto.getString(1));
                producto.setCategoria(cursorProducto.getString(2));
                producto.setPrecio(cursorProducto.getString(3));
                producto.setCantidad(cursorProducto.getString(4));
                listaContactos.add(producto);
            }while (cursorProducto.moveToNext());
        }
        cursorProducto.close();
        return listaContactos;
    }
    /* funcion que permite selecionar un producto para editar*/
    public Productos verProducto(int id){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Productos producto = null;
        Cursor cursorProducto ;

        cursorProducto = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorProducto.moveToFirst()){

                producto = new Productos();
                producto.setId(cursorProducto.getInt(0));
                producto.setNomprod(cursorProducto.getString(1));
                producto.setCategoria(cursorProducto.getString(2));
                producto.setPrecio(cursorProducto.getString(3));
                producto.setCantidad(cursorProducto.getString(4));
        }
        cursorProducto.close();
        return producto;
    }


    public boolean editarProducto(int id ,String nomprod , String categoria , String precio , String cantidad){
       boolean correcto = false;
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
          db.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET nomprod ='"+ nomprod +"', categoria ='"+ categoria +"', precio ='"+ precio +"', cantidad ='"+ cantidad +"' WHERE id = '"+ id + "'");
          correcto = true;
        }catch (Exception ex){
            correcto= false;
        }finally {
            db.close();
        }
        return  correcto;
    }

    public boolean eliminarProducto(int id ){
        boolean correcto = false;
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            db.execSQL("DELETE FROM " + TABLE_PRODUCTOS + " WHERE id = '"+id+"' ");
            correcto = true;
        }catch (Exception ex){
            correcto= false;
        }finally {
            db.close();
        }
        return  correcto;
    }

}
