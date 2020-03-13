package com.paquete.misericapp;

public class Informe {
    private String a_email;
    private String a_horas;
    private String b_publicaciones;
    private String c_videos;
    private String d_revisitas;
    private String e_estudios;

    public Informe() {
    }

    public Informe(String a_email, String a_horas, String b_publicaciones, String c_videos, String d_revisitas, String e_estudios) {
        this.a_email = a_email;
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

    public String getA_email() { return a_email; }

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
