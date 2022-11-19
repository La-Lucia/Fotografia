package com.example.fotografia;

import android.graphics.Bitmap;

public class Photograph {

    private String Descripcion;
    private Bitmap imagen;

    public Photograph(String descripcion, Bitmap imagen) {
        Descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
