package com.example.demo.hibernate.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String companyName;

    private String description;

    public Company(String companyName, String description) {
        this.companyName = companyName;
        this.description = description;
    }
}
