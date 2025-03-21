package com.example.MyWebApp.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "Role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Client> clients;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Sotrudnik> sotrudniks;

    public Role() {}

    public Role(int id) {
        this.id = id;
    }

    public Role(int id, String name) {
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

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Sotrudnik> getSotrudniks() {
        return sotrudniks;
    }

    public void setSotrudniks(Set<Sotrudnik> sotrudniks) {
        this.sotrudniks = sotrudniks;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clients=" + clients +
                ", sotrudniks=" + sotrudniks +
                '}';
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
