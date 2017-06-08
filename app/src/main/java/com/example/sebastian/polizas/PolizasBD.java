package com.example.sebastian.polizas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by sebastian on 19/04/17.
 */

public class PolizasBD extends SQLiteOpenHelper {

    public static  class DatosTabla implements BaseColumns {
        public static final String NOMBRE_TABLA = "Directorio";
        public static final String COLUMNA_CEDULA = "cedula";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_TELEFONO = "telefono";
        public static final String COLUMNA_EMAIL = "email";
        public static final String COLUMNA_COBERTURA = "cobertura";

        private static final String CREAR_TABLA_1 =
                "CREATE TABLE " + DatosTabla.NOMBRE_TABLA + " (" +
                        DatosTabla.COLUMNA_CEDULA + " INTEGER PRIMARY KEY," +
                        DatosTabla.COLUMNA_NOMBRE+ " TEXT," +
                        DatosTabla.COLUMNA_TELEFONO+ " TEXT," +
                        DatosTabla.COLUMNA_EMAIL+ " TEXT," +
                        DatosTabla.COLUMNA_COBERTURA + " TEXT)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosTabla.NOMBRE_TABLA;
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MiBasedeDatos.db";

    public PolizasBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatosTabla.CREAR_TABLA_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
