package com.example.demo.pojo;

import com.example.demo.entity.Cron;

public interface ICronService {
    Cron findByCronIdAndStatus(String cronId,String status);
    Cron findByNo(Integer id);
}
