package com.paquete.misericapp;

public class InformeInt {
    int A_horas;
    int B_publicaciones;
    int C_videos;
    int D_revisitas;
    int E_estudios;

    public InformeInt(){
    }

    public InformeInt(int A_horas, int B_publicaciones, int C_videos, int D_revisitas, int E_estudios) {
        this.A_horas = A_horas;
        this.B_publicaciones = B_publicaciones;
        this.C_videos = C_videos;
        this.D_revisitas = D_revisitas;
        this.E_estudios = E_estudios;
    }

    public int getA_horas() {
        return A_horas;
    }

    public int getB_publicaciones() {
        return B_publicaciones;
    }

    public int getC_videos() {
        return C_videos;
    }

    public int getD_revisitas() {
        return D_revisitas;
    }

    public int getE_estudios() {
        return E_estudios;
    }
}
