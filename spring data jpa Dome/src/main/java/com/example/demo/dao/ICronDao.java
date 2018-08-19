package com.example.demo.dao;

import com.example.demo.entity.Cron;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICronDao extends CrudRepository<Cron,Integer> {
    Cron findCronByCronIdAndState(String cronId, String state);
}
