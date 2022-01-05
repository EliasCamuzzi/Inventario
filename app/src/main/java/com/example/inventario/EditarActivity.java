package com.example.inventario;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventario.database.DbProductos;
import com.example.inventario.entidades.Productos;

public class EditarActivity extends AppCompatActivity {
    EditText textoNombre, textoCategoria, textoPrecio;
    Button guardarCambios;

    ImageButton editarCambios, eliminar;
    boolean c = false;

    Productos producto;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos_del_producto);

        textoNombre = findViewById(R.id.Ingreso_De_Nombre);
        textoCategoria = findViewById(R.id.Ingreso_De_Categoria);
        textoPrecio = findViewById(R.id.Ingreso_De_Precio);

        guardarCambios = findViewById(R.id.Guardar_Cambios);

        editarCambios = findViewById(R.id.Editar_Cambios);
        editarCambios.setVisibility(View.INVISIBLE);

        eliminar = findViewById(R.id.Eliminar);
        eliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                id = Integer.parseInt(null);

            } else {
                id = extras.getInt("ID");

            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");

        }

        DbProductos dbProductos = new DbProductos(EditarActivity.this);
        producto = dbProductos.VerProducto(id);

        if (producto != null) {
            textoNombre.setText(producto.getNombre());
            textoCategoria.setText(producto.getCategoria());
            textoPrecio.setText(producto.getPrecio());

        }

        guardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textoNombre.getText().toString().equals("") && !textoCategoria.getText().toString().equals("") && !textoPrecio.getText().toString().equals("")) {
                    c = dbProductos.EditarProducto(id, textoNombre.getText().toString(), textoCategoria.getText().toString(), textoPrecio.getText().toString());

                    if (c) {
                        Toast.makeText(EditarActivity.this, "El producto ha sido guardado", Toast.LENGTH_LONG).show();

                        Intent irALaSiguienteActivity = new Intent(EditarActivity.this, VentanaPrincipal.class);
                        startActivity(irALaSiguienteActivity);
                        finish();

                    } else {
                        Toast.makeText(EditarActivity.this, "Error al guardar el producto", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(EditarActivity.this, "Asegurese de llenar todos los campos", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
