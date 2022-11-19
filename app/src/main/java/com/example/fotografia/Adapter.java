package com.example.fotografia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    ArrayList<Photograph> foto;

    public Adapter(ArrayList<Photograph> foto) {
        this.foto = foto;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new Holder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.foto, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Photograph object = foto.get(position);
        holder.Nombre.setText(object.getDescripcion());
        holder.imageView.setImageBitmap(object.getImagen());

    }

    @Override
    public int getItemCount() {
        return foto.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        TextView Nombre;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.TxtFotoDescripcion);
            imageView = itemView.findViewById(R.id.foto);
        }
    }

}
