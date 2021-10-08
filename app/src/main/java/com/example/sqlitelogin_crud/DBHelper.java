package com.example.sqlitelogin_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public  static final String TABLE_PRODUCTOS = "productos";
    public DBHelper(@Nullable Context context) {
        super(context, "login.db" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create Table correos(correo Text primary key , password Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldVersion, int newVersion) {
        mydb.execSQL("drop Table if exists correos");
    }
    public  Boolean insertData(String correo , String password){
    SQLiteDatabase mydb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correo" , correo);
        contentValues.put("password" , password);
        long result = mydb.insert("correos" , null , contentValues);
        if (result == -1){
            return false;
        }else{
            return  true;
        }
    }

    public  Boolean checkCorreo(String correo){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from correos where correo = ?" , new String[] {correo});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

        public Boolean checkCorreoPassword(String correo , String password){
            SQLiteDatabase mydb = this.getWritableDatabase();
            Cursor cursor = mydb.rawQuery("select * from correos where correo = ? and password = ?" , new String[] {correo , password});
            if(cursor.getCount()>0){
                return true;
            }else {
                return false;
            }
        }

    }

