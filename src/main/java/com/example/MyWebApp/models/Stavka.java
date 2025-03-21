package com.example.MyWebApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Stavka")
public class Stavka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_stavka;
    private int summa;
    private double koef;
    private LocalDateTime date;

    @JoinColumn(name = "id_client", foreignKey = @ForeignKey(name = "Client_id_client"))
    private int id_client;
    @JoinColumn(name = "id_match", foreignKey = @ForeignKey(name = "Match_id_match"))
    private int id_match;
    @JoinColumn(name = "id_match", foreignKey = @ForeignKey(name = "Status_stavki_id_status"))
    private int id_status;
    private int id_bet_team;

    public Stavka() {}

    public Stavka(int summa, double koef, LocalDateTime date, int id_client, int id_match) {
        this.summa = summa;
        this.koef = koef;
        this.date = date;
        this.id_client = id_client;
        this.id_match = id_match;
    }

    public Stavka(int summa, double koef, LocalDateTime date, int id_client, int id_match, int id_status, int id_bet_team) {
        this.summa = summa;
        this.koef = koef;
        this.date = date;
        this.id_client = id_client;
        this.id_match = id_match;
        this.id_status = id_status;
        this.id_bet_team = id_bet_team;
    }

    public Stavka(int summa, double koef, LocalDateTime date, int id_client, int id_match, int id_bet_team) {
        this.summa = summa;
        this.koef = koef;
        this.date = date;
        this.id_client = id_client;
        this.id_match = id_match;
        this.id_bet_team = id_bet_team;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getId_bet_team() {
        return id_bet_team;
    }

    public void setId_bet_team(int id_bet_team) {
        this.id_bet_team = id_bet_team;
    }

    public int getId() {
        return id_stavka;
    }

    public int getSumma() {
        return summa;
    }

    public double getKoef() {
        return koef;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_match() {
        return id_match;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public void setKoef(double koef) {
        this.koef = koef;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    @Override
    public String toString() {
        return "Ставка " + id_stavka + ": " +
                "Сумма: '" + summa + "'" +
                ", Коэффициент: '" + koef + "'" +
                ", Дата: '" + date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH mm")) + "'" +
                ", Номер клиента: '" + id_client + "'" +
                ", Номер матча: '" + id_match + "'" +
                ", Номер поставленной команды: '" + id_bet_team + "'" +
                ", Статус: '" + id_status + "'";
    }
}
