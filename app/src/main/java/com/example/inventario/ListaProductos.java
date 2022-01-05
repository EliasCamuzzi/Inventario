package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.inventario.adapters.ListaProductosAdapter;
import com.example.inventario.database.DbProductos;
import com.example.inventario.entidades.Productos;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView buscar;
    RecyclerView listaDeProductos;
    ArrayList<Productos> listaArrayDeProductos;
    ListaProductosAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        listaDeProductos = findViewById(R.id.Lista_Productos);
        buscar = findViewById(R.id.Buscar_Producto);
        listaDeProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(ListaProductos.this);

        listaArrayDeProductos = new ArrayList<>();

        adaptador = new ListaProductosAdapter(dbProductos.MostrarProductos());
        listaDeProductos.setAdapter(adaptador);

        buscar.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adaptador.Filtrado(newText);
        return false;

    }
}