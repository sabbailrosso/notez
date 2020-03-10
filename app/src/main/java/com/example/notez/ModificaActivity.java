package com.example.notez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class ModificaActivity extends AppCompatActivity {

    NotaDBAdapter ndba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica);
        ndba = new NotaDBAdapter(this);
        ndba.open();
        Button btnSalva = findViewById(R.id.BtnSalva);
        final EditText ettitolo = findViewById(R.id.ETTitolo);
        final EditText etautore = findViewById(R.id.ETAutore);
        final EditText ettesto = findViewById(R.id.ETTesto);

        final Bundle extras = getIntent().getExtras();
        final int id = extras.getInt("id");
        final String autore = extras.getString("autore");
        final String titolo = extras.getString("titolo");
        final String testo = extras.getString("testo");
        final String data= extras.getString("data");
        /*i dati forniti dal intent li mettiamo sui campi*/
        ettesto.setText(testo);
        etautore.setText(autore);
        ettitolo.setText(titolo);
        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String autore = etautore.getText().toString();
                    String titolo = ettitolo.getText().toString();
                    String testo = ettesto.getText().toString();
                    CardModel n = new CardModel(autore,titolo, testo, data);
                    ndba.open();

                    if (titolo.length() > 0 && testo.length() > 0 && autore.length() > 0) {
                        ndba.updateNote(id, n);
                        Toast.makeText(ModificaActivity.this, "Modifica avvenuta", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ModificaActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(ModificaActivity.this, "Errore, compila tutti i campi", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(ModificaActivity.this, "Errore nella modifica della nota", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                startActivity(new Intent(ModificaActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        ndba.close();
        super.onDestroy();
    }

}


