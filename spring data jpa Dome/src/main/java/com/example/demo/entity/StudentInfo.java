package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
public class StudentInfo {
    @Id
    @GeneratedValue
    Long id ;
    @Column
    String studentName;
    @Column
    String major;
    @Column
    int age;
    Date SchoolTime;

}
