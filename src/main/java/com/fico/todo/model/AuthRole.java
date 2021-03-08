package com.fico.todo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "auth_role")
@ApiModel
public class AuthRole {
    @Id
    @Column(name = "auth_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Role's Id", position = 1)
    private int id;
    @Column(name = "role_name")
    @ApiModelProperty(notes = "Role's name", position = 2)
    private String role;

    public AuthRole(){}

    public AuthRole(String role, String description) {
        this.role = role;
        this.description = description;
    }

    @Column(name = "role_description")
    @ApiModelProperty(notes = "Role's description", position = 3)
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
