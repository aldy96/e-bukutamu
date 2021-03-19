package com.example.e_bukutamu;

public class UserModel {

    int id;
    String name;
    String tall;

    public UserModel(int id, String name, String tall) {
        this.id = id;
        this.name = name;
        this.tall = tall;
    }

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTall() {
        return tall;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }
}
