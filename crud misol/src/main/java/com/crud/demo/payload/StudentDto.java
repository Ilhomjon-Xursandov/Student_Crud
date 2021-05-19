package com.crud.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto  {
    private Integer id;
    private String fistname;
    private String lastname;
    private String phone;
    private String isActive;

    public StudentDto(String fistname, String lastname, String phone, String isActive) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.phone = phone;
        this.isActive = isActive;
    }
}
