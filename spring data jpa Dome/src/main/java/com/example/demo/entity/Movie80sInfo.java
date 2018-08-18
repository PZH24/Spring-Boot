package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Movie80sInfo")
@Data
public class Movie80sInfo {
    @Id
    @GeneratedValue
    private  Long id;
    private  String movieName;
    private  String movieUrl;
}
