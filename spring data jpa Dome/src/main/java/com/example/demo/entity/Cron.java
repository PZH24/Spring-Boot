package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Cron {
    @Id
    @GeneratedValue
    private Integer no;
    private String cronId;
    /**
     * 表达式：格式如：0 0/10 * * * ?
     * */
    private  String cron;
    /**
     * 状态{1表示有效。0表示失效}
     * */
    private  String state;
}
