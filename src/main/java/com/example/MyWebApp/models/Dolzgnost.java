package com.example.MyWebApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Dolzgnost")
public class Dolzgnost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dolzgnost;
    private String nazvanie;

    public Dolzgnost() {}
    public Dolzgnost(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public int getId() {
        return id_dolzgnost;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    @Override
    public String toString() {
        return "Должность " + id_dolzgnost + ": " +
                "Название: '" + nazvanie + '\'';
    }
}
