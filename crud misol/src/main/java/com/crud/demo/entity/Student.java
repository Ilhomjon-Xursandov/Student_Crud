package com.crud.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fistname;
    private String lastname;

    @Column(nullable = false)
    private String phone;

    private boolean isActive;

    public Student(String fistname, String lastname, String phone, boolean isactive) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.phone = phone;
        this.isActive = isactive;
    }
}
