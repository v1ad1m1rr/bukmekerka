package com.example.MyWebApp.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private int id_client;
    @Column(name = "second_name")
    private String second_name;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "otchestvo")
    private String otchestvo;
    @Column(name = "birth_date")
    private LocalDate birth_date;
    @Column(name = "registr_date")
    private LocalDate registr_date;
    @Column(name = "nomer_carti")
    private int nomer_carti;


    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Transient
    private String confirmPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Lob
    @Column(name = "avatar")
    private byte[] avatar;

    public Client() {}

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
        registr_date = LocalDate.now();
    }

    public Client(String second_name, String first_name, String otchestvo, LocalDate birth_date, LocalDate registr_date
            , int nomer_carti, String email, String password) {
        this.second_name = second_name;
        this.first_name = first_name;
        this.otchestvo = otchestvo;
        this.birth_date = birth_date;
        this.registr_date = registr_date;
        this.nomer_carti = nomer_carti;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id_client;
    }

    public String getSecondName() {
        return second_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public LocalDate getBirthDate() {
        return birth_date;
    }

    public LocalDate getRegistrDate() {
        return registr_date;
    }

    public int getNomerCarti() {
        return nomer_carti;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setSecondName(String second_name) {
        this.second_name = second_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public void setBirthDate(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public void setRegistrDate(LocalDate registr_date) {
        this.registr_date = registr_date;
    }

    public void setNomerCarti(int nomer_carti) {
        this.nomer_carti = nomer_carti;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        String birth = "";
        if (birth_date != null) {
            birth = birth_date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        }
        return "Фамилия: '" + second_name + '\'' +
                ", Имя: '" + first_name + '\'' +
                ", Отчество: '" + otchestvo + '\'' +
                ", Дата рождения: '" + birth + '\'' +
                ", Дата регистрации: '" + registr_date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) + '\'' +
                ", Почта: '" + email + '\'' +
                ", Пароль: '" + password + '\'' +
                ", Номер карты: '" + nomer_carti +  '\'';
    }
}
