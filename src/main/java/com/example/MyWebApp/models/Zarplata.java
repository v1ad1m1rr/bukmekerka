package com.example.MyWebApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Zarplata")
public class Zarplata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_zarplata;
    private int zarplata;

    public Zarplata() {}

    public Zarplata(int zarplata) {
        this.zarplata = zarplata;
    }

    public int getId() {
        return id_zarplata;
    }

    public int getZarplata() {
        return zarplata;
    }

    public void setZarplata(int zarplata) {
        this.zarplata = zarplata;
    }

    @Override
    public String toString() {
        return "Зарплата " + id_zarplata + ": " +
                "Сумма: '" + zarplata + '\'';
    }
}
