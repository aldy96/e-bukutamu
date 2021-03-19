package com.example.e_bukutamu;

public class TamuModel {

    private int ID;
    private String Nama;

    public TamuModel() {
    }

    public TamuModel(int ID, String nama) {
        this.ID = ID;
        Nama = nama;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }
}
