package com.aggregation.api.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);

    @Autowired
    RefreshDataTask refreshDataTask;

    @Scheduled(cron = "${scheduler.cron.expression}")
    public void schedulesTask() {
        LOGGER.info("Job to refresh data started.");

        refreshDataTask.updateData();

        LOGGER.info("Job to refresh data completed.");
    }

}
