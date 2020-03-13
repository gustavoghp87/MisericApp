package com.paquete.misericapp;

public class UploadFoto {
    private String mName;
    private String eMail;
    private String fechayhora;
    private String ImagenUrl;

    public UploadFoto() {
    }

    public UploadFoto(String email, String fechaYhora, String name, String mImagenUrl) {
        if (name.trim().equals("")) {
            name = "";
        }
        mName = name;
        eMail = email;
        fechayhora = fechaYhora;
        ImagenUrl = mImagenUrl;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() { return eMail; }

    public String getFechayhora() { return fechayhora; }

    public String getmImagenUrl() {
        return ImagenUrl;
    }


    public void setName(String name) {
        mName = name;
    }

    public void setEmail(String email) { eMail = email; }

    public void setFechayhora(String fechaYhora) { fechayhora = fechaYhora; }

    public void setmImagenUrl(String mImagenUrl) {
        ImagenUrl = mImagenUrl;
    }
}