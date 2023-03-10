package com.lounwb.admin.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Pet {

    private String name;
    private double weight;

    public Pet(String name) {
        this.name = name;
    }
}