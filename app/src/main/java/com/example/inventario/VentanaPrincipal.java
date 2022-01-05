package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.inventario.db.DbHelper;

public class VentanaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_principal);

    }

    public void IrAAgregarProducto(View view) {

        DbHelper dbHelper = new DbHelper(VentanaPrincipal.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Intent irALaSiguienteActivity = new Intent(VentanaPrincipal.this, AgregarProducto.class);
        startActivity(irALaSiguienteActivity);

    }

    public void IrAListaDeProductos(View view) {
        Intent irALaSiguienteActivity = new Intent(VentanaPrincipal.this, ListaProductos.class);
        startActivity(irALaSiguienteActivity);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            finishAffinity();

        }
        return super.onKeyDown(keyCode, event);

    }
}