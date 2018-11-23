package com.example.demo.controller;

import com.example.demo.config.DynamicSchedulingConfig;
import com.example.demo.entity.Cron;
import com.example.demo.pojo.ICronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    private ICronService cronService;
    @Autowired
    private DynamicSchedulingConfig schedulingConfig;
    private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    @GetMapping("/{id}/{state}")
    public  void startScheduling(@PathVariable("id") String cronId,@PathVariable("state") String state){
        Cron cron = cronService.findByCronIdAndStatus(cronId,state);
//        Cron cron = cronService.findByNo(Integer.parseInt(cronId));
        if(cron==null){
            logger.info("开启定时任务失败，请检测是否有定义");
            return;
        }
       String cronStr = cron.getCron();
       if(!StringUtils.isEmpty(cronStr)){
         schedulingConfig.setCron(cronStr);
           logger.info("开启定时任务成功");
       }
    }
    @GetMapping("/stopScheduling")
    public  void stopScheduling(){
       schedulingConfig.stopCron();
        logger.info("停止定时任务成功");
    }
}
