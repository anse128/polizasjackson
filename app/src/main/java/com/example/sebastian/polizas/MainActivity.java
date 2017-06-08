package com.example.sebastian.polizas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etCedula, etNombre, etTelefono, etEmail, etCobertura;
    Button btnGuardar, btnBuscar, btnBorrar, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCedula = (EditText) findViewById(R.id.etcedula);
        etNombre = (EditText) findViewById(R.id.etnombre);
        etTelefono = (EditText) findViewById(R.id.ettelefono);
        etEmail = (EditText) findViewById(R.id.etemail);
        etCobertura = (EditText) findViewById(R.id.etcobertura);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        final PolizasBD polizasBD = new PolizasBD(getApplicationContext());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = polizasBD.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(PolizasBD.DatosTabla.COLUMNA_CEDULA,etCedula.getText().toString());
                valores.put(PolizasBD.DatosTabla.COLUMNA_NOMBRE,etNombre.getText().toString());
                valores.put(PolizasBD.DatosTabla.COLUMNA_TELEFONO,etTelefono.getText().toString());
                valores.put(PolizasBD.DatosTabla.COLUMNA_EMAIL,etEmail.getText().toString());
                valores.put(PolizasBD.DatosTabla.COLUMNA_COBERTURA,etCobertura.getText().toString());

                Long IdGuardado = db.insert(PolizasBD.DatosTabla.NOMBRE_TABLA,PolizasBD.DatosTabla.COLUMNA_CEDULA,valores);
                Toast.makeText(getApplicationContext(), "Se guardo el dato: " + IdGuardado, Toast.LENGTH_SHORT).show();

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = polizasBD.getReadableDatabase();
                String[] argsel = {etCedula.getText().toString()};
                String[] projection = {PolizasBD.DatosTabla.COLUMNA_NOMBRE, PolizasBD.DatosTabla.COLUMNA_TELEFONO};
                Cursor c = db.query(PolizasBD.DatosTabla.NOMBRE_TABLA, projection, PolizasBD.DatosTabla.COLUMNA_CEDULA + "=?", argsel, null, null, null);
                c.moveToFirst();
                etNombre.setText(c.getString(0));
                etTelefono.setText(c.getString(1));

            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = polizasBD.getWritableDatabase();
                String Selection = PolizasBD.DatosTabla.COLUMNA_CEDULA+"=?";
                String[] argsel = {etCedula.getText().toString()};

                db.delete(PolizasBD.DatosTabla.NOMBRE_TABLA,Selection,argsel);

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = polizasBD.getWritableDatabase();
                ContentValues valores = new ContentValues();

                valores.put(PolizasBD.DatosTabla.COLUMNA_NOMBRE, etNombre.getText().toString());
                valores.put(PolizasBD.DatosTabla.COLUMNA_TELEFONO, etTelefono.getText().toString());

                String[] argsel = {etCedula.getText().toString()};
                String Selection = PolizasBD.DatosTabla.COLUMNA_CEDULA+"1";

                int count = db.update(PolizasBD.DatosTabla.NOMBRE_TABLA,valores,Selection,argsel);
            }
        });

    }
}
