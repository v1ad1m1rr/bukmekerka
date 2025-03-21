package com.example.MyWebApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Matches")
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_match;
    private LocalDateTime match_date;
    private double koef1;
    private double koef2;
    @JoinColumn(name = "kat_match", foreignKey = @ForeignKey(name = "Kat_match_kat_match"))
    private int kat_match;
    @JoinColumn(name = "championship", foreignKey = @ForeignKey(name = "Matches_Champ"))
    private int championship;
    @JoinColumn(name = "id_team1", foreignKey = @ForeignKey(name = "Matches_team1"))
    private int id_team1;
    @JoinColumn(name = "id_team2", foreignKey = @ForeignKey(name = "Matches_team2"))
    private int id_team2;
    @JoinColumn(name = "id_status", foreignKey = @ForeignKey(name = "Matches_id_Status"))
    private int id_status;

    private int count_team1;
    private int count_team2;

    public Matches() {}

    public Matches(LocalDateTime match_date, double koef1, double koef2, int kat_match, int championship, int id_team1, int id_team2, int id_status, int count_team1, int count_team2) {
        this.match_date = match_date;
        this.koef1 = koef1;
        this.koef2 = koef2;
        this.kat_match = kat_match;
        this.championship = championship;
        this.id_team1 = id_team1;
        this.id_team2 = id_team2;
        this.id_status = id_status;
        this.count_team1 = count_team1;
        this.count_team2 = count_team2;
    }

    public Matches(int id_team1, int id_team2, LocalDateTime match_date, double koef1, double koef2, int kat_match, int championship) {
        this.id_team1 = id_team1;
        this.id_team2 = id_team2;
        this.match_date = match_date;
        this.koef1 = koef1;
        this.koef2 = koef2;
        this.kat_match = kat_match;
        this.championship = championship;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getCount_team1() {
        return count_team1;
    }

    public void setCount_team1(int count_team1) {
        this.count_team1 = count_team1;
    }

    public int getCount_team2() {
        return count_team2;
    }

    public void setCount_team2(int count_team2) {
        this.count_team2 = count_team2;
    }

    public int getId() {
        return id_match;
    }

    public double getKoef1() {
        return koef1;
    }

    public double getKoef2() {
        return koef2;
    }

    public int getKat_match() {
        return kat_match;
    }

    public LocalDateTime getMatch_date() {
        return match_date;
    }

    public void setKat_match(int kat_match) {
        this.kat_match = kat_match;
    }

    public void setKoef1(double koef1) {
        this.koef1 = koef1;
    }

    public void setMatch_date(LocalDateTime match_date) {
        this.match_date = match_date;
    }

    public void setKoef2(double koef2) {
        this.koef2 = koef2;
    }

    public int getChampionship() {
        return championship;
    }

    public void setChampionship(int championship) {
        this.championship = championship;
    }

    public int getId_team1() {
        return id_team1;
    }

    public void setId_team1(int id_team1) {
        this.id_team1 = id_team1;
    }

    public int getId_team2() {
        return id_team2;
    }

    public void setId_team2(int id_team2) {
        this.id_team2 = id_team2;
    }

    @Override
    public String toString() {
        return "Матч " + id_match + ": " +
                "Команда 1: '" + id_team1 + '\'' +
                ", Команда 2: '" + id_team2 + '\'' +
                ", Чемпионат: '" + championship + '\'' +
                ", Дата матча: '" + match_date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH mm")) + '\'' +
                ", Коэффициент 1: '" + koef1 + '\'' +
                ", Коэффициент 2: '" + koef2 + '\'' +
                ", Номер категории матча: '" + kat_match + '\'' +
                ", Номер статуса: '" + id_status + '\'' +
                ", Счет первой команды: '" + count_team1 + '\'' +
                ", Счет второй команды: '" + count_team2 + '\'';
    }
}
