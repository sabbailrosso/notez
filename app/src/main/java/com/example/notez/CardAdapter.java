package com.example.notez;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<CardModel> {
    public CardAdapter(Context context) {
        super(context, R.layout.item_card);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_card, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CardModel model = getItem(position);

        holder.txtAutore.setText(model.getAutore());
        holder.txtTitolo.setText(model.getTitolo());
        holder.txtData.setText(model.getData());
        holder.txtTesto.setText(model.getTesto());

        return convertView;
    }

    static class ViewHolder {
        TextView txtTitolo, txtData, txtAutore, txtTesto;

        ViewHolder(View itemView) {
            txtTitolo = itemView.findViewById(R.id.txtTitolo);
            txtData = itemView.findViewById(R.id.txtData);
            txtAutore = itemView.findViewById(R.id.txtAutore);
            txtTesto = itemView.findViewById(R.id.txtTesto);
        }
    }
}

