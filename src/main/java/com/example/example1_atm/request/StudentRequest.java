package com.example.example1_atm.request;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String name;
    private int age;
    private String gender;
}
