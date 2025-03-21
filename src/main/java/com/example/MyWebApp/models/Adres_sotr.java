package com.example.MyWebApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Adres_sotr")
public class Adres_sotr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_adres;
    private String city;
    private String street;
    private int house;
    private String korpus;
    private int kvartira;

    public Adres_sotr() {}

    public Adres_sotr(String city, String street, int house, String korpus, int kvartira) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.korpus = korpus;
        this.kvartira = kvartira;
    }

    public int getId() {
        return id_adres;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public String getKorpus() {
        return korpus;
    }

    public int getKvartira() {
        return kvartira;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public void setKorpus(String korpus) {
        this.korpus = korpus;
    }

    public void setKvartira(int kvartira) {
        this.kvartira = kvartira;
    }

    @Override
    public String toString() {
        return "Адрес " + id_adres + ": " +
                "Город: '" + city + '\'' +
                ", Улица: '" + street + '\'' +
                ", Дом: '" + house + '\'' +
                ", Корпус: '" + korpus + '\'' +
                ", Квартира: '" + kvartira + '\'';
    }
}
