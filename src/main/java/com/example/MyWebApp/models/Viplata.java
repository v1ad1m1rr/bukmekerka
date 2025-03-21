package com.example.MyWebApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Viplata")
public class Viplata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_viplata;
    private int summa;
    private LocalDateTime date;
    @JoinColumn(name = "id_stavka", foreignKey = @ForeignKey(name = "Stavka_id_stavka"))
    private int id_stavka;
    @JoinColumn(name = "id_client", foreignKey = @ForeignKey(name = "Client_Viplata"))
    private int id_client;

    public Viplata() {}

    public Viplata(int summa, LocalDateTime date, int id_stavka, int id_client) {
        this.summa = summa;
        this.date = date;
        this.id_stavka = id_stavka;
        this.id_client = id_client;
    }

    public int getSumma() {
        return summa;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getId() {
        return id_stavka;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_viplata() {
        return id_viplata;
    }

    public int getId_stavka() {
        return id_stavka;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public void setId_stavka(int id_stavka) {
        this.id_stavka = id_stavka;
    }

    @Override
    public String toString() {
        return "Выплата " + id_viplata + ": " +
                "Сумма: '" + summa + '\'' +
                ", Дата: '" + date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH mm")) + '\'' +
                ", Номер ставки: '" + id_stavka + '\'' +
                ", Номер клиента: '" + id_client + '\'';
    }
}
