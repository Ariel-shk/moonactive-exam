package com.moonactive.exam.arielshkalimexem.echoattime.model.dto;


import java.time.LocalDateTime;
import java.util.Objects;

public class EchoAtTimeRequest {

    private LocalDateTime time;
    private String message;

    public EchoAtTimeRequest() {
    }

    public EchoAtTimeRequest(LocalDateTime time, String message) {
        this.time = time;
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EchoAtTimeRequest that = (EchoAtTimeRequest) o;
        return Objects.equals(time, that.time) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, message);
    }

    @Override
    public String toString() {
        return "EchoAtTimeRequest{" +
                "time=" + time +
                ", message='" + message + '\'' +
                '}';
    }
}
