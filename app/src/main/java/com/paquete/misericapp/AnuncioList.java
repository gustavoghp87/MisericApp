package com.paquete.misericapp;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

// EXCLUSIVO LECTURA DB

    public class AnuncioList extends ArrayAdapter<Anuncio> {
        private Activity context;
        List<Anuncio> anuncios;

        public AnuncioList(Activity context, List<Anuncio> anuncios) {
            super(context, R.layout.home_list, anuncios);
            this.context = context;
            this.anuncios = anuncios;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.home_list, null, true);

            TextView mTextViewAnuncio1 = listViewItem.findViewById(R.id.anuncio1);
            TextView mTextViewAnuncio2 = listViewItem.findViewById(R.id.anuncio2);
            TextView mTextViewAnuncio3 = listViewItem.findViewById(R.id.anuncio3);
            TextView mTextViewAnuncio4 = listViewItem.findViewById(R.id.anuncio4);
            TextView mTextViewAnuncio5 = listViewItem.findViewById(R.id.anuncio5);
            TextView mTextViewAnuncio6 = listViewItem.findViewById(R.id.anuncio6);

            Anuncio anuncio = anuncios.get(position);

            mTextViewAnuncio1.setText(anuncio.getAnuncio1());
            mTextViewAnuncio2.setText(anuncio.getAnuncio2());
            mTextViewAnuncio3.setText(anuncio.getAnuncio3());
            mTextViewAnuncio4.setText(anuncio.getAnuncio4());
            mTextViewAnuncio5.setText(anuncio.getAnuncio5());
            mTextViewAnuncio6.setText(anuncio.getAnuncio6());

            return listViewItem;
        }
    }