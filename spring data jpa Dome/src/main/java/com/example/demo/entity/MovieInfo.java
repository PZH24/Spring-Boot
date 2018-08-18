package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MOVIEINFO" ,uniqueConstraints = @UniqueConstraint(name = "MOVIEINFO_Key", columnNames = {
        "movieName", "movieUrl"}))
@Data
public class MovieInfo {
    @Id
    @GeneratedValue
    private  Long id;
    private  String catalog;
    private  String catalogName;
    @Column(length = 500)
    private  String catalogUrl;
    private  String movieName;
    @Column(length = 500)
    private  String movieUrl;
    @Column(length = 500)
    private  String imgUrl;
}
