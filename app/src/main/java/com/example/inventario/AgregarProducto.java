package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventario.database.DbProductos;

public class AgregarProducto extends AppCompatActivity {

    EditText textoNombre, textoCategoria, textoPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

    }

    public void Guardar(View view) {
        textoNombre = findViewById(R.id.Ingreso_Nombre);
        textoCategoria = findViewById(R.id.Ingreso_Categoria);
        textoPrecio = findViewById(R.id.Ingreso_Precio);

        if(!textoNombre.getText().toString().equals("") && !textoCategoria.getText().toString().equals("") && !textoPrecio.getText().toString().equals("")) {

            DbProductos dbProductos = new DbProductos(AgregarProducto.this);
            long id = dbProductos.InsertarProducto(textoNombre.getText().toString(), textoCategoria.getText().toString(), textoPrecio.getText().toString());

            if (id > 0) {
                Toast.makeText(AgregarProducto.this, "El producto ha sido guardado", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(AgregarProducto.this, "Error al guardar el producto", Toast.LENGTH_LONG).show();

            }

            finish();

        } else {
            Toast.makeText(AgregarProducto.this, "Asegurese de llenar todos los campos", Toast.LENGTH_LONG).show();

        }

    }
}