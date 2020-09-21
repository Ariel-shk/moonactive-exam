package com.moonactive.exam.arielshkalimexem.echoattime.service.impl;

import com.moonactive.exam.arielshkalimexem.echoattime.dal.EchoTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

@Service
@EnableScheduling
public class EchoMessagesManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoMessagesManager.class);
    private static final long MESSAGES_PULLER_CYCLES_MS = 1000;

    @Autowired
    private EchoTimeRepository repository;

    @Scheduled(fixedRate = MESSAGES_PULLER_CYCLES_MS)
    protected void pullMessages() {
        LOGGER.trace("pulling messages to print..");
        Collection<Long> echoTimes = repository.getAllEchoTimesUntil(Instant.now().getEpochSecond());
        if (echoTimes != null) {
            for (Long echoTime : echoTimes) {
                String message;
                while ((message = repository.popNextMessage(echoTime)) != null) {
                    echo(echoTime, message);
                }

                repository.delete(echoTime);
            }
        }
    }

    public void echo(Long echoTime, String message) {
        long diff = Instant.now().getEpochSecond() - echoTime;
        LOGGER.debug("Printing message [echoTime={}, message={}] | time passed={} seconds", echoTime, message, diff);
        LOGGER.info(message);
    }
}
