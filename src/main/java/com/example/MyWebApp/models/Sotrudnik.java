package com.example.MyWebApp.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "Sotrudnik")
public class Sotrudnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sotrudnik;
    private String second_name;
    private String first_name;
    private String otchestvo;
    private int tel;
    private LocalDate birth_date;
    private LocalDate hire_date;

    @JoinColumn(name = "id_otdel", foreignKey = @ForeignKey(name = "Otdel_id_otdel"))
    private int id_otdel;

    @JoinColumn(name = "id_adres", foreignKey = @ForeignKey(name = "Adres_id_adres"))
    private int id_adres;

    @JoinColumn(name = "id_dolzgnost", foreignKey = @ForeignKey(name = "Dolzgnost_id_dolzgnost"))
    private int id_dolzgnost;

    @JoinColumn(name = "id_zarplata", foreignKey = @ForeignKey(name = "Zarplata_id_zarplata"))
    private int id_zarplata;

    private String email;
    private String password;
    @Transient
    private String confirmPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Lob
    @Column(name = "avatar")
    private byte[] avatar;

    public Sotrudnik() {}

    public Sotrudnik(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Sotrudnik(String second_name, String first_name, String otchestvo, int tel, LocalDate birth_date, LocalDate hire_date
            , int id_otdel, int id_adres, int id_dolzgnost, int id_zarplata
            , String email, String password) {
        this.second_name = second_name;
        this.first_name = first_name;
        this.otchestvo = otchestvo;
        this.tel = tel;
        this.birth_date = birth_date;
        this.hire_date = hire_date;
        this.id_otdel = id_otdel;
        this.id_adres = id_adres;
        this.id_dolzgnost = id_dolzgnost;
        this.id_zarplata = id_zarplata;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id_sotrudnik;
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

    public LocalDate getHireDate() {
        return hire_date;
    }

    public int getTel() {
        return tel;
    }

    public int getId_otdel() {
        return id_otdel;
    }

    public int getId_dolzgnost() {
        return id_dolzgnost;
    }

    public int getId_zarplata() {
        return id_zarplata;
    }

    public int getId_adres() {
        return id_adres;
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

    public void setId_adres(int id_adres) {
        this.id_adres = id_adres;
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

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setHireDate(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public void setBirthDate(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public void setIdOtdel(int id_otdel) {this.id_otdel = id_otdel;}

    public void setId_dolzgnost(int id_dolzgnost) {
        this.id_dolzgnost = id_dolzgnost;
    }

    public void setId_zarplata(int id_zarplata) {
        this.id_zarplata = id_zarplata;
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
        return "Фамилия: '" + second_name + '\'' +
                ", Имя: '" + first_name + '\'' +
                ", Отчество: '" + otchestvo + '\'' +
                ", Дата рождения: '" + birth_date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) + '\'' +
                ", Дата найма: '" + hire_date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) + '\'' +
                ", Телефон: '" + tel + '\'' +
                ", Номер отдела: '" + id_otdel + '\'' +
                ", Номер Адреса: '" + id_adres + '\'' +
                ", Номер Должности'" + id_dolzgnost + '\'' +
                ", Номер Зарплаты'" + id_zarplata + '\'' +
                ", Почта: '" + email + '\'' +
                ", Пароль: '" + password + '\'';
    }
}
