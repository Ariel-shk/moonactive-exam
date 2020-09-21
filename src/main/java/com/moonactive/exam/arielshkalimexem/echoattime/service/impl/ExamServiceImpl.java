package com.moonactive.exam.arielshkalimexem.echoattime.service.impl;

import com.moonactive.exam.arielshkalimexem.echoattime.dal.EchoTimeRepository;
import com.moonactive.exam.arielshkalimexem.echoattime.model.dto.EchoAtTimeRequest;
import com.moonactive.exam.arielshkalimexem.echoattime.model.dto.EchoAtTimeResponse;
import com.moonactive.exam.arielshkalimexem.echoattime.model.exceptions.InternalServerException;
import com.moonactive.exam.arielshkalimexem.echoattime.model.exceptions.ValidationException;
import com.moonactive.exam.arielshkalimexem.echoattime.service.IExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ExamServiceImpl implements IExamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);

    @Autowired
    private EchoTimeRepository repository;

    @Override
    public EchoAtTimeResponse echoAtTime(EchoAtTimeRequest request) {
        LOGGER.debug("echoAtTime has been called [request={}]", request);
        validateRequest(request);

        long epochTime = request.getTime().atZone(ZoneOffset.systemDefault()).toEpochSecond();
        boolean hasAdded = repository.saveEchoTime(epochTime, request.getMessage());
        if (!hasAdded) {
            throw new InternalServerException("Failed to add message");
        }

        LOGGER.debug("request has been successfully submitted [request={}]", request);
        return new EchoAtTimeResponse();
    }

    private void validateRequest(EchoAtTimeRequest request) {
        if (request == null) {
            throw new ValidationException("Request may not be null");
        }

        if (request.getMessage() == null) {
            throw new ValidationException("Message may not be null");
        }

        if (request.getTime() == null || request.getTime().isBefore(LocalDateTime.now().plusSeconds(1))) {
            throw new ValidationException("Time may not be null or from the past");
        }
    }
}
