package com.example.inventario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.inventario.db.DbProductos;
import com.example.inventario.entidades.Productos;

public class VerDatosDelProducto extends AppCompatActivity {

    EditText textoNombre, textoCategoria, textoPrecio;
    ImageButton editarCambios, eliminar;
    Button guardarCambios;

    Productos producto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos_del_producto);

        textoNombre = findViewById(R.id.Ingreso_De_Nombre);
        textoCategoria = findViewById(R.id.Ingreso_De_Categoria);
        textoPrecio = findViewById(R.id.Ingreso_De_Precio);

        editarCambios = findViewById(R.id.Editar_Cambios);
        eliminar = findViewById(R.id.Eliminar);

        guardarCambios = findViewById(R.id.Guardar_Cambios);

        guardarCambios.setVisibility(View.GONE);

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

        final DbProductos dbProductos = new DbProductos(VerDatosDelProducto.this);
        producto = dbProductos.VerProducto(id);

        if (producto != null) {
            textoNombre.setText(producto.getNombre());
            textoCategoria.setText(producto.getCategoria());
            textoPrecio.setText(producto.getPrecio());

            textoNombre.setInputType(InputType.TYPE_NULL);
            textoCategoria.setInputType(InputType.TYPE_NULL);
            textoPrecio.setInputType(InputType.TYPE_NULL);

        }

        editarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCambios.setVisibility(View.VISIBLE);
                Intent intent = new Intent(VerDatosDelProducto.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
                finish();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerDatosDelProducto.this);
                builder.setMessage("¿Esta seguro que desea eliminar este producto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (dbProductos.EliminarProducto(id)) {
                                    Intent irALaSiguienteActivity = new Intent(VerDatosDelProducto.this, VentanaPrincipal.class);
                                    startActivity(irALaSiguienteActivity);
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        });
    }
}