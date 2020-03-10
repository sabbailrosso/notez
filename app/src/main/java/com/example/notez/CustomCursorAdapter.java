package com.example.notez;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {
    public CustomCursorAdapter(Context context, Cursor c){
        super(context, c);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
// when the view will be created for first time,
// we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.item_card, parent, false);
        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textViewData = (TextView) view.findViewById(R.id.txtData);
        textViewData.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));

        TextView textViewAutore = (TextView) view.findViewById(R.id.txtAutore);
        textViewAutore.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

        TextView textViewTitolo = (TextView) view.findViewById(R.id.txtTitolo);
        textViewTitolo.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

        TextView textViewTesto = (TextView) view.findViewById(R.id.txtTesto);
        textViewTesto.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
    }


}
