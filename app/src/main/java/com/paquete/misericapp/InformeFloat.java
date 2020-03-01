package com.paquete.misericapp;

public class InformeFloat {
    String A_Email;
    float B_horas_float;
    int C_publicaciones;
    int D_videos;
    int E_revisitas;
    int F_estudios;

    public InformeFloat(){
    }

    public InformeFloat(String A_Email, float B_horas, int C_publicaciones, int D_videos, int E_revisitas, int F_estudios) {
        this.A_Email = A_Email;
        this.B_horas_float = B_horas;
        this.C_publicaciones = C_publicaciones;
        this.D_videos = D_videos;
        this.E_revisitas = E_revisitas;
        this.F_estudios = F_estudios;
    }

    public String getA_Email() { return A_Email; }

    public float getB_horas_float() {
        return B_horas_float;
    }

    public int getC_publicaciones() {
        return C_publicaciones;
    }

    public int getD_videos() {
        return D_videos;
    }

    public int getE_revisitas() {
        return E_revisitas;
    }

    public int getF_estudios() {
        return F_estudios;
    }
}
