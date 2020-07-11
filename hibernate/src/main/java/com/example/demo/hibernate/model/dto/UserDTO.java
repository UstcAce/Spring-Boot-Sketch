package com.example.demo.hibernate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
/**
 * 数据传输对象（DTO)(Data Transfer Object)
 */
public class UserDTO {
    private String name;

    private int age;

    private String companyName;

    private String schoolName;
}
