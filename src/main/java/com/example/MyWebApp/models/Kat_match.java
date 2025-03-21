package com.example.MyWebApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Kat_match")
public class Kat_match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_kat_match;
    private String nazvanie;

    public Kat_match() {}

    public Kat_match(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public int getId() {
        return id_kat_match;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    @Override
    public String toString() {
        return "Категория матча " + id_kat_match + ": " +
                "Название: '" + nazvanie + '\'';
    }
}
