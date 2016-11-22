package com.example.dm2.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ejer1 extends AppCompatActivity {
    private EditText nombre;
    Spinner provincia;
    RadioButton masculino;
    RadioButton femenino;
    CheckBox php;
    CheckBox java;
    CheckBox html;
    CheckBox css;
    TextView operador1;
    TextView operador2;
    EditText resultado;
    Button evaluar;
    String provinciaSelecionada;
    int contador = 3;
    TextView candidatos;
    int concan = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer1);
        candidatos = (TextView) findViewById(R.id.candidatos);
        nombre = (EditText) findViewById(R.id.etNombre);
        provincia = (Spinner) findViewById(R.id.spProvincia);
        masculino = (RadioButton) findViewById(R.id.rbMasculino);
        femenino = (RadioButton) findViewById(R.id.rbFemenino);
        php = (CheckBox) findViewById(R.id.chPhp);
        java = (CheckBox) findViewById(R.id.chJava);
        html = (CheckBox) findViewById(R.id.chHtml);
        css = (CheckBox) findViewById(R.id.chCss);
        operador1 = (TextView) findViewById(R.id.tvOperador1);
        operador2 = (TextView) findViewById(R.id.tvOperador2);
        resultado = (EditText) findViewById(R.id.etResultado);
        evaluar = (Button) findViewById(R.id.btnEvaluar);

        final int op1 = (int) Math.round(Math.random()*100);
        final int op2 = (int) Math.round(Math.random()*100);
        operador1.setText(op1+"");
        operador2.setText(op2+"");

        final String[] datos = new String[] {"Alava","Bizkaia",
                "Gipuzkua"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provincia.setAdapter(adaptador);

        provincia.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view, int i, long l) {
                        provinciaSelecionada = provincia.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        provinciaSelecionada = "No se ha realizado ninguna selección";
                    }
                });

        evaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.getText().toString().equals("") || nombre.getText() == null){
                    Toast.makeText(Ejer1.this, "NOMBRE OBLIGATORIO",Toast.LENGTH_SHORT).show();
                }else if (resultado.getText().toString().equals("")|| resultado.getText() == null){
                    Toast.makeText(Ejer1.this, "OPERACION MATEMATICA OBLIGATORIA",Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(resultado.getText().toString()) != (op1+op2) ) {
                    contador --;
                    Toast.makeText(Ejer1.this, "OPERACION MATEMATICA INCORRECTA: Intentos restantes "+contador,Toast.LENGTH_SHORT).show();
                    if (contador == 0){
                        Intent intent = new Intent(Ejer1.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                else{
                    String conocimientos ="";
                    String sxo="";
                    Intent intent = new Intent(Ejer1.this, Ejer1B.class);
                    intent.putExtra("Nombre", nombre.getText().toString());
                    intent.putExtra("Provincia", provinciaSelecionada);
                    if (masculino.isSelected()){
                        sxo = "Masculino";
                    }else if (femenino.isSelected()) {
                        sxo = "Femenimo";
                    }
                    intent.putExtra("Sexo", sxo);
                    if(php.isChecked()){
                        conocimientos += " php";

                    }
                    if(java.isChecked()){
                        conocimientos += " java";

                    }
                    if(css.isChecked()){
                        conocimientos += " css";

                    }
                    if(html.isChecked()){
                        conocimientos += " html";

                    }
                    intent.putExtra("Conocimiento", conocimientos);
                    startActivityForResult(intent, 123);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK){
            concan ++;
            candidatos.setText("candidatos"+ concan);
        }else if (requestCode == 123 && resultCode == RESULT_CANCELED){
            candidatos.setText("candidatos" + concan);
        }
    }
}
