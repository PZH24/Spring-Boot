package com.example.demo.service;

import com.example.demo.dao.ICronDao;
import com.example.demo.entity.Cron;
import com.example.demo.pojo.ICronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CronService implements ICronService {
    @Autowired
    private ICronDao cronDao;
    @Override
    public Cron findByCronIdAndStatus(String cronId, String status) {
    return cronDao.findCronByCronIdAndState(cronId, status);
    }
    @Override
    public  Cron findByNo(Integer id){
        return cronDao.findById(id).get();
    }
}
