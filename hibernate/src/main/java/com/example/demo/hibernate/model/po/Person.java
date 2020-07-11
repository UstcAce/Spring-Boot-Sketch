package com.example.demo.hibernate.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// @Entity 注解代表它是数据库持久化类
@Entity
@Data
@NoArgsConstructor  // 生成一个无参数的构造方法
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer age;
    private Long companyId;
    private Long schoolId;

    public Person(String name, Integer age, Long companyId) {
        this.name = name;
        this.age = age;
        this.companyId = companyId;
    }
}
