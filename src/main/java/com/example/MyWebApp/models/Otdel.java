package com.example.MyWebApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Otdel")
public class Otdel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_otdel;
    private String nazvanie;
    private int tel;

    public Otdel() {}

    public Otdel(String nazvanie, int tel) {
        this.nazvanie = nazvanie;
        this.tel = tel;
    }

    public int getId() {
        return id_otdel;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public int getTel() {
        return tel;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Отдел " + id_otdel + ": " +
                "Название: '" + nazvanie + '\'' +
                ", Телефон: '" + tel + '\'';
    }
}
