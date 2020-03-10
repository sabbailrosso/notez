package com.example.notez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class NuovaNotaActivity extends AppCompatActivity {

    NotaDBAdapter ndba = new NotaDBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_nota);

        Button button = findViewById(R.id.BtnSalva);
        final EditText ettitolo = findViewById(R.id.ETTitolo);
        final EditText etautore = findViewById(R.id.ETAutore);
        final EditText ettesto = findViewById(R.id.ETTesto);
//        final EditText etpassword = findViewById(R.id.ETPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String titolo = ettitolo.getText().toString();
                    String testo = ettesto.getText().toString();
                    String autore = etautore.getText().toString();
                    String data = new Date().toString();
                    CardModel n = new CardModel(autore,titolo, testo, data);
                    ndba.open();
                    if (titolo.length() > 0 && testo.length() > 0 && autore.length() > 0) {
                        ndba.createNota(n);
                        ndba.close();
                        Toast.makeText(NuovaNotaActivity.this, "Creazione avvenuta", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(NuovaNotaActivity.this, "Errore, compila tutti i campi", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(NuovaNotaActivity.this, "Errore nella creazione della nota", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                startActivity(new Intent(NuovaNotaActivity.this, MainActivity.class));
            }
        });
    }
}
