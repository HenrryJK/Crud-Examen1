package com.example.sqlitelogin_crud.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitelogin_crud.R;
import com.example.sqlitelogin_crud.VerActivity;
import com.example.sqlitelogin_crud.entidades.Productos;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder> {
        //agregue el constructor
        ArrayList<Productos> listaProductos;
        // para buscar
    ArrayList<Productos> listaOriginal;
        public ListaProductosAdapter(ArrayList<Productos> listaProductos){
            this.listaProductos = listaProductos;
            listaOriginal = new ArrayList<>();
            listaOriginal.addAll(listaProductos);
        }


    @NonNull
    @Override
    // Ayudara a poder asignar cual va a ser el diseño que va a tener cada element de la lista
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto,null , false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.viewNomprod.setText(listaProductos.get(position).getNomprod());
        holder.viewCategoria.setText(listaProductos.get(position).getCategoria());
        holder.viewPrecio.setText(listaProductos.get(position).getPrecio());
        holder.viewCantidad.setText(listaProductos.get(position).getCantidad());
    }

    /////// metodo para buscar un registro
    public void filtrado(String txtBuscar){
            int longitud = txtBuscar.length();
            if (longitud == 0){
                listaProductos.clear();
                listaProductos.addAll(listaOriginal);
            }else{
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    List<Productos> collecion =  listaProductos.stream().filter(i -> i.getNomprod().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                    listaProductos.clear();
                    listaProductos.addAll(collecion);
                }else{
                    for (Productos p: listaOriginal){
                        if (p.getNomprod().toLowerCase().contains(txtBuscar.toLowerCase())){
                          listaProductos.add(p);
                        }
                    }
                }
            }
            // es para notificar los cambios.
            notifyDataSetChanged();
    }



    // el tamaño de la lista / conocer el tamaño de la lista
    @Override
    public int getItemCount() {
      return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNomprod , viewCategoria , viewPrecio , viewCantidad;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNomprod = itemView.findViewById(R.id.viewNomprod);
            viewCategoria = itemView.findViewById(R.id.viewCategoria);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewCantidad = itemView.findViewById(R.id.viewCantidad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID" , listaProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
