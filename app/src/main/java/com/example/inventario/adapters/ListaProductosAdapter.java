package com.example.inventario.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventario.R;
import com.example.inventario.VerDatosDelProducto;
import com.example.inventario.entidades.Productos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder> {

    ArrayList<Productos> listaDeProductos;
    ArrayList<Productos> listaPrincipal;

    public ListaProductosAdapter(ArrayList<Productos> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
        listaPrincipal = new ArrayList<>();
        listaPrincipal.addAll(listaDeProductos);

    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_productos, null, false);
        return new ProductoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.informacionNombre.setText(listaDeProductos.get(position).getNombre());
        holder.informacionCategoria.setText(listaDeProductos.get(position).getCategoria());

    }

    public void Filtrado(final String textoBuscar) {
        int longitudDeLaCadena = textoBuscar.length();

        if (longitudDeLaCadena == 0) {
            listaDeProductos.clear();
            listaDeProductos.addAll(listaPrincipal);

        } else {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                List<Productos> lista = listaDeProductos.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(textoBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaDeProductos.clear();
                listaDeProductos.addAll(lista);

            } else {
                for (Productos c : listaPrincipal) {
                    if (c.getNombre().toLowerCase().contains(textoBuscar.toLowerCase())) {
                        listaDeProductos.add(c);

                    }
                }
            }
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();

    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView informacionNombre, informacionCategoria;

        public ProductoViewHolder(@NonNull View vistaItem) {
            super(vistaItem);

            informacionNombre = vistaItem.findViewById(R.id.Informacion_Nombre);
            informacionCategoria = vistaItem.findViewById(R.id.Informacion_Categoria);

            vistaItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerDatosDelProducto.class);
                    intent.putExtra("ID", listaDeProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}