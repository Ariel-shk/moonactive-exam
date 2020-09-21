package com.moonactive.exam.arielshkalimexem.echoattime.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class EchoTimeRepository {

    private final String ECHO_TIMES_KEY = "EpochTimes";

    @Autowired
    private ZSetOperations<String, Long> zSetOperations;

    @Autowired
    private ListOperations<String, String> listOperations;

    public boolean saveEchoTime(Long echoEpochTime, String message) {
        Boolean hasAdded = zSetOperations.add(ECHO_TIMES_KEY, echoEpochTime, echoEpochTime);
        hasAdded = (hasAdded != null && hasAdded) || (zSetOperations.score(ECHO_TIMES_KEY, echoEpochTime) != null);
        if (hasAdded) {
            Long messageCount = listOperations.leftPush(echoEpochTime.toString(), message);
            return messageCount != null && messageCount >= 1L;
        }

        return false;
    }

    public Collection<Long> getAllEchoTimesUntil(Long echoEpochTime) {
        return zSetOperations.rangeByScore(ECHO_TIMES_KEY, 0, echoEpochTime);
    }

    public String popNextMessage(Long echoEpochTime) {
        return listOperations.rightPop(echoEpochTime.toString());
    }

    public void delete(Long echoEpochTime) {
        zSetOperations.remove(ECHO_TIMES_KEY, echoEpochTime);
    }
}
