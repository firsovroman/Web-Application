package ru.irkagr.somesite.schedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.irkagr.somesite.statistics.ApplicationStatistic;

@Component
public class StatisticSchedule {

    private final Logger logger = LoggerFactory.getLogger(StatisticSchedule.class);


    @Value("${statistic.schedulerIsActive}")
    private boolean schedulerIsActive;

    @Autowired
    ApplicationStatistic applicationStatistic;

    @Scheduled(cron = "${statistic.cron:-}")
    public void executeStatistic() {

        if(schedulerIsActive) {
            logger.info("Scheduler execute");

            //TODO добавить отправление статистики по почте перед сбросом

            applicationStatistic.reset();
        }
    }

}
