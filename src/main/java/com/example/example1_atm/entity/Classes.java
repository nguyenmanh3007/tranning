package com.example.example1_atm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Classes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nameClass")
    private String nameClass;
    @Column(name = "department")
    private String department;
    @OneToMany(mappedBy = "classes",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> students;
}
