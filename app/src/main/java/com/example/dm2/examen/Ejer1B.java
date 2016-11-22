package com.example.dm2.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ejer1B extends AppCompatActivity implements View.OnClickListener {
 TextView nombre;
    TextView provincia;
    TextView sexo;
    TextView conocimientos;
    Button aceptar;
    Button rechazar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer1_b);
        Bundle extras = getIntent().getExtras();
        nombre = (TextView) findViewById(R.id.rsNombre);
        provincia = (TextView) findViewById(R.id.rsProvincia);
        sexo = (TextView) findViewById(R.id.rsSexo);
        conocimientos = (TextView) findViewById(R.id.rsConocimientos);
        String strNombre = extras.getString("Nombre");
        String strProvincia = extras.getString("Provincia");;
        String strSexo = extras.getString("Sexo");
        String strConocimientos = extras.getString("Conocimiento");
        nombre.setText(strNombre);
        provincia.setText(strProvincia);
        sexo.setText(strSexo);
        conocimientos.setText(strConocimientos);




    }

    @Override
    public void onClick(View view) {
        if (view == aceptar) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        }
    }
}
