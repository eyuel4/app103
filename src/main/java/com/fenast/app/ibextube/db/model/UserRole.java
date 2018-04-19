package com.fenast.app.ibextube.db.model;


import javax.persistence.*;

@Entity
@Table(name="USER_ROLES")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    UserRole() {
    }

    public UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
