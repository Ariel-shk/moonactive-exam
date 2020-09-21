package com.moonactive.exam.arielshkalimexem.echoattime.service.impl;

import com.moonactive.exam.arielshkalimexem.echoattime.model.dto.EchoAtTimeRequest;
import com.moonactive.exam.arielshkalimexem.echoattime.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Demo {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private IExamService examService;

    /**
     * Uncomment the Scheduled annotation for auto echo at time requests.
     * Adjust the fixed rate param to test performance.
     */
    //@org.springframework.scheduling.annotation.Scheduled(fixedRate = 100)
    public void demo() {
        executorService.execute(() -> {
            try {
                LocalDateTime dateTime = LocalDateTime.now().plus(ThreadLocalRandom.current().nextInt(5, 65 + 1), ChronoUnit.SECONDS);
                String message = "Hello " + ThreadLocalRandom.current().nextInt(1, 1000 + 1);
                examService.echoAtTime(new EchoAtTimeRequest(dateTime, message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
