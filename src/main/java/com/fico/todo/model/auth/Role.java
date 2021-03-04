package com.fico.todo.model.auth;

import javax.persistence.*;

@Entity
@Table(name = "auth_role")
public class Role {
    @Id
    @Column(name = "auth_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_name")
    private String role;

    public Role(){}

    public Role(String role, String description) {
        this.role = role;
        this.description = description;
    }

    @Column(name = "role_description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
