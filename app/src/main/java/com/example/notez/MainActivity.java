package com.example.notez;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final NotaDBAdapter ndba = new NotaDBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ndba.open();
        final CustomCursorAdapter customAdapter = new CustomCursorAdapter(MainActivity.this, ndba.getAllData());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView txtAggiungi = findViewById(R.id.txtAggiungi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notez");
        final ListView lvCards = findViewById(R.id.list_cards);
        lvCards.setAdapter(customAdapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NuovaNotaActivity.class));
            }
        });

        if (customAdapter != null) {
            txtAggiungi.setVisibility(View.INVISIBLE);

        } else {
            txtAggiungi.setVisibility(View.VISIBLE);
        }

        lvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Button btnModifica = view.findViewById(R.id.btnModifica);
                Button btnCancella = view.findViewById(R.id.btnCancella);

                if (btnModifica.getVisibility() == View.GONE) {
                    btnCancella.setVisibility(View.VISIBLE);
                    btnModifica.setVisibility(View.VISIBLE);

                } else {
                    btnCancella.setVisibility(View.GONE);
                    btnModifica.setVisibility(View.GONE);
                }
                btnCancella.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ndba.open();
                        Cursor cursor = (Cursor) customAdapter.getItem(position);
                        int u = cursor.getInt(0);
                        if (ndba.deleteNota(u)) {
                            Toast.makeText(MainActivity.this, "Nota cancellata", Toast.LENGTH_SHORT).show();
                            customAdapter.swapCursor(ndba.getAllData());
                            lvCards.invalidate();
                        }

                    }
                });
                btnModifica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ndba.open();
                        Cursor cursor = (Cursor) customAdapter.getItem(position);
                        int id = cursor.getInt(0);
                        String autore = cursor.getString(cursor.getColumnIndexOrThrow("autore"));
                        String titolo = cursor.getString(cursor.getColumnIndexOrThrow("titolo"));
                        String testo = cursor.getString(cursor.getColumnIndexOrThrow("testo"));
                        String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
                        final Bundle bundle = new Bundle();
                        Intent modifica = new Intent(MainActivity.this, ModificaActivity.class);
                        bundle.putInt("id", id);
                        bundle.putString("autore", autore);
                        bundle.putString("titolo", titolo);
                        bundle.putString("testo", testo);
                        bundle.putString("data", data);
                        modifica.putExtras(bundle);
                        startActivity(modifica);
                    }
                });

            }

        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ndba.close();
    }


}