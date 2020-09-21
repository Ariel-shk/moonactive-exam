package com.moonactive.exam.arielshkalimexem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        setRandomPort(8000, 9000);
        SpringApplication.run(ExamApplication.class, args);
    }

    public static void setRandomPort(int minPort, int maxPort) {
        String userDefinedPort = System.getProperty("server.port", System.getenv("SERVER_PORT"));
        if (StringUtils.isEmpty(userDefinedPort)) {
            int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
            System.setProperty("server.port", String.valueOf(port));
        }
    }

}
