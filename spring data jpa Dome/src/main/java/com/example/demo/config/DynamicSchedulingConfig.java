package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Component
@Configuration
public class DynamicSchedulingConfig {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }
    private ScheduledFuture<?>future;
    private String cron = "";

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
        stopCron();
        future = threadPoolTaskScheduler.schedule(new Runnable() {
              @Override
              public void run() {
                  try {
                      System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
      }, new Trigger() {
              @Nullable
              @Override
              public Date nextExecutionTime(TriggerContext triggerContext) {
                  if("".equals(cron)||cron==null){
                      return null;
                  }
                  CronTrigger cronTrigger = new CronTrigger(cron);
                  Date nextExeDate = cronTrigger.nextExecutionTime(triggerContext);
                  return nextExeDate;
              }
          });
    }

    public void stopCron(){
        if(future!=null){
            future.cancel(true);
        }
    }
}
