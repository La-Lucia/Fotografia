package com.example.fotografia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    Context context;
    private static String DATABASE_NAME = "mydb.db";
    private static int DATABASE_VERSION = 1;
    private static String CREATETABLEQUERY = "create table imageInfo (Nombre TEXT" +
            ", imagen BLOB)";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            db.execSQL(CREATETABLEQUERY);
            Toast.makeText(context, "Tabla Creada Correctamente", Toast.LENGTH_SHORT).show();

        }catch (Exception e){

            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {



    }

    public void GuardarImagen(Photograph p){

        try{

            SQLiteDatabase objectSQLiteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = p.getImagen();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100,objectByteArrayOutputStream);

            imageInBytes = objectByteArrayOutputStream.toByteArray();
            ContentValues obj = new ContentValues();

            obj.put("Nombre",p.getDescripcion());
            obj.put("imagen", imageInBytes);

            long mensaje = objectSQLiteDatabase.insert("imageInfo", null, obj);
            if (mensaje!=0){

                Toast.makeText(context, "Agregado a la base de datos", Toast.LENGTH_SHORT).show();

            }else{

                Toast.makeText(context, "No pudo ser agregado a la base de datos", Toast.LENGTH_SHORT).show();
                objectSQLiteDatabase.close();
            }

        }catch (Exception e){

        }

    }

    public ArrayList<Photograph> obtenerImagenes(){

        try{
            SQLiteDatabase object = this.getReadableDatabase();
            ArrayList<Photograph> lista = new ArrayList<>();
            Cursor cursor = object.rawQuery("select * from imageInfo", null);
            if (cursor.getCount()!=0){

                while (cursor.moveToNext()){
                    String nombre = cursor.getString(0);
                    byte[] imageBytes = cursor.getBlob(1);

                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    lista.add(new Photograph(nombre, bitmap));
                }

                return lista;

            } else{

                Toast.makeText(context, "No hay ningun registro en la base de datos", Toast.LENGTH_SHORT).show();
                return null;
            }

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

    }


}
