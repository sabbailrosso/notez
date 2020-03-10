package com.example.notez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NotaDBAdapter {

    private static final String LOG_TAG = NotaDBAdapter.class.getSimpleName();
    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    // Database fields
    private static final String DATABASE_TABLE = "Note";
    public static final String KEY_IDNOTA = "_id";
    public static final String KEY_TITOLO = "titolo";
    public static final String KEY_AUTORE = "autore";
    public static final String KEY_TESTO = "testo";
    public static final String KEY_DATA = "data";

    public NotaDBAdapter(Context context) {
        this.context = context;
    }

    public NotaDBAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(CardModel nota) {
        ContentValues values = new ContentValues();
        values.put(KEY_TITOLO, nota.getTitolo());
        values.put(KEY_AUTORE, nota.getAutore());
        values.put(KEY_DATA, nota.getData());
        values.put(KEY_TESTO, nota.getTesto());

        return values;
    }

    public long createNota(CardModel nota) {

        ContentValues initialValues = createContentValues(nota);

        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }


    public Cursor getAllData() {
        try {
            return database.query(DATABASE_TABLE, new String[]{KEY_IDNOTA,
                            KEY_AUTORE, KEY_TITOLO, KEY_TESTO, KEY_DATA},
                    null, null, null, null,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteNota(int id) {
        return database.delete(DATABASE_TABLE, KEY_IDNOTA + "='" + id + "'", null) > 0;
    }

    public boolean updateNote(Integer id, CardModel nota) {
        //CardModel nota = new CardModel(autore,titolo,testo,data);
        ContentValues values = createContentValues(nota);
        return database.update(DATABASE_TABLE, values, KEY_IDNOTA + "=" + id, null) > 0;
    }
}
    
