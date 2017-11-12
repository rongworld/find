package com.ncuhome.find.config;

import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoUpdateDB {
    @Autowired
    private LostRepository lostRepository;

    @Scheduled(cron = "0 0 12 * * ?" )// 每天中午12点触发
    public void log(){
        final Long overdueDate = 5*24*60*60*1000L;//5天未认领，自动确认
        Long now = System.currentTimeMillis();
        Long before = now - overdueDate;//5天以前的日期
        List<Lost> lostList = lostRepository.findByDateBeforeAndStatus(before,0);
        for(int i=0;i<lostList.size();i++){
            Lost lost = lostList.get(i);
            lost.setStatus(1);
            lostRepository.saveAndFlush(lost);
        }
    }
}
