package com.paquete.misericapp;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

// EXCLUSIVO LECTURA DB

public class InformesList extends ArrayAdapter<Informe> {
    private Activity context;
    List<Informe> informes;

    public InformesList(Activity context, List<Informe> informes)
    {
        super(context, R.layout.informe_list, informes);
        this.context = context;
        this.informes = informes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.informe_list, null, true);

        TextView mTextViewLecturaEmail = listViewItem.findViewById(R.id.textViewLecturaEmail);
        TextView mTextViewLecturaHoras = listViewItem.findViewById(R.id.textViewLecturaHoras);
        TextView mTextViewLecturaPublicaciones = listViewItem.findViewById(R.id.textViewLecturaPublicaciones);
        TextView mTextViewLecturaVideos = listViewItem.findViewById(R.id.textViewLecturaVideos);
        TextView mTextViewLecturaRevisitas = listViewItem.findViewById(R.id.textViewLecturaRevisitas);
        TextView mTextViewLecturaEstudios = listViewItem.findViewById(R.id.textViewLecturaEstudios);

        Informe informe = informes.get(position);

        mTextViewLecturaEmail.setText("Email: " +informe.getA_email());
        mTextViewLecturaHoras.setText("Horas: " +informe.getA_horas());
        mTextViewLecturaPublicaciones.setText("Publicaciones: " +informe.getB_publicaciones());
        mTextViewLecturaVideos.setText("Videos: " +informe.getC_videos());
        mTextViewLecturaRevisitas.setText("Revisitas: " +informe.getD_revisitas());
        mTextViewLecturaEstudios.setText("Estudios: " +informe.getE_estudios());

        return listViewItem;

//        String horas_lectura = dataSnapshot.child("a_horas").getValue().toString();
//        String publicaciones_lectura = dataSnapshot.child("b_publicaciones").getValue().toString();
//        String videos_lectura = dataSnapshot.child("c_videos").getValue().toString();
//        String revisitas_lectura = dataSnapshot.child("d_revisitas").getValue().toString();
//        String estudios_lectura = dataSnapshot.child("e_estudios").getValue().toString();
    }
}