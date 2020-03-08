package com.paquete.misericapp;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Informe {
    private String name1;
    private String a_horas;
    private String b_publicaciones;
    private String c_videos;
    private String d_revisitas;
    private String e_estudios;

    public Informe() {
    }

    public Informe(String name1, String a_horas, String b_publicaciones, String c_videos, String d_revisitas, String e_estudios) {
        this.name1 = name1;
        this.a_horas = a_horas;
        this.b_publicaciones = b_publicaciones;
        this.c_videos = c_videos;
        this.d_revisitas = d_revisitas;
        this.e_estudios = e_estudios;
    }

    public Informe(String a_horas, String b_publicaciones, String c_videos, String d_revisitas, String e_estudios) {
        this.a_horas = a_horas;
        this.b_publicaciones = b_publicaciones;
        this.c_videos = c_videos;
        this.d_revisitas = d_revisitas;
        this.e_estudios = e_estudios;
    }

    public String getName1() { return name1; }

    public String getA_horas() { return a_horas; }

    public String getB_publicaciones() { return b_publicaciones; }

    public String getC_videos() {
        return c_videos;
    }

    public String getD_revisitas() {
        return d_revisitas;
    }

    public String getE_estudios() { return e_estudios; }
}
