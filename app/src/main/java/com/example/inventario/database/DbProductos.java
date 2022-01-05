package com.example.inventario.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.inventario.entidades.Productos;

import java.util.ArrayList;

public class DbProductos extends DbHelper {

    Context contexto;

    public DbProductos(@Nullable Context contexto) {
        super(contexto);
        this.contexto = contexto;
    }

    public long InsertarProducto(String nombre, String categoria, String precio) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(contexto);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombre", nombre);
            valores.put("categoria", categoria);
            valores.put("precio", precio);

            id = db.insert(TABLE_PRODUCTOS, null, valores);

        } catch (Exception e) {
            e.toString();

        }

        return id;
    }

    public ArrayList<Productos> MostrarProductos() {

        DbHelper dbHelper = new DbHelper(contexto);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ArrayList<Productos> listaDeProductos = new ArrayList<>();
        Productos producto;
        Cursor cursorProductos;

        cursorProductos = database.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " ORDER BY nombre ASC", null);

        if (cursorProductos.moveToFirst()) {

            do {
                producto = new Productos();
                producto.setId(cursorProductos.getInt(0));
                producto.setNombre(cursorProductos.getString(1));
                producto.setCategoria(cursorProductos.getString(2));
                producto.setPrecio(cursorProductos.getString(3));
                listaDeProductos.add(producto);

            } while (cursorProductos.moveToNext());
        }

        cursorProductos.close();

        return listaDeProductos;
    }

    public Productos VerProducto(int id) {

        DbHelper dbHelper = new DbHelper(contexto);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Productos producto = null;
        Cursor cursorProductos;

        cursorProductos = database.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorProductos.moveToFirst()) {
            producto = new Productos();
            producto.setId(cursorProductos.getInt(0));
            producto.setNombre(cursorProductos.getString(1));
            producto.setCategoria(cursorProductos.getString(2));
            producto.setPrecio(cursorProductos.getString(3));
        }

        cursorProductos.close();

        return producto;
    }

    public boolean EditarProducto(int id, String nombre, String categoria, String precio) {

        boolean c = false;

        DbHelper dbHelper = new DbHelper(contexto);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        try {
            database.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET nombre = '" + nombre + "', categoria = '" + categoria + "', precio = '" + precio + "' WHERE id='" + id + "' ");
            c = true;

        } catch (Exception e) {
            e.toString();
            c = false;

        } finally {
            database.close();

        }

        return c;
    }

    public boolean EliminarProducto(int id) {

        boolean c = false;

        DbHelper dbHelper = new DbHelper(contexto);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        try {
            database.execSQL("DELETE FROM " + TABLE_PRODUCTOS + " WHERE id = '" + id + "'");
            c = true;

        } catch (Exception e) {
            e.toString();
            c = false;

        } finally {
            database.close();

        }

        return c;
    }
}
