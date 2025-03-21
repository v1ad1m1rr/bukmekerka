package com.example.MyWebApp.models;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Lob
    private byte[] logo;
    private int rating;
    @JoinColumn(name = "kat_match", foreignKey = @ForeignKey(name = "Team_Kat_Match"))
    private int kat_match;

    public Team() {}

    public Team(int id, String name, byte[] logo, int rating, int kat_match) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.rating = rating;
        this.kat_match = kat_match;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public int getKat_match() {
        return kat_match;
    }

    public void setKat_match(int kat_match) {
        this.kat_match = kat_match;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", rating=" + rating +
                ", kat_match=" + kat_match +
                '}';
    }
}
