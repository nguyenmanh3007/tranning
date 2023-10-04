package com.example.example1_atm.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Student")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name="age")
    private int age;
    @Column(name="gender")
    private String gender;
    @Column(name = "address")
    private String address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="classId",nullable = false)
    private Classes classes;
}
