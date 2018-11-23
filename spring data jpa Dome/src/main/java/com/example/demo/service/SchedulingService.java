package com.example.demo.service;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.util.Date;

@Service
public class SchedulingService {
    private static  Logger logger = LoggerFactory.getLogger(SchedulingService.class);
    //cron=""：表达式；initialDelay 延时；fixedRate：周期
//    @Scheduled(initialDelay = 1000,fixedRate = 2000)
//    public void  ScheduleTest(){
//      logger.info("定时任务时间隔："+new Date());
//    }
}
