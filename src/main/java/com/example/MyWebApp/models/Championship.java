package com.example.MyWebApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "championship")
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Championship() {}

    public Championship(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Championship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
