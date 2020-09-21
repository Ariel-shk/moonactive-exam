package com.moonactive.exam.arielshkalimexem.echoattime.controller;

import com.moonactive.exam.arielshkalimexem.echoattime.model.dto.EchoAtTimeRequest;
import com.moonactive.exam.arielshkalimexem.echoattime.model.dto.EchoAtTimeResponse;
import com.moonactive.exam.arielshkalimexem.echoattime.model.exceptions.BaseServerException;
import com.moonactive.exam.arielshkalimexem.echoattime.model.exceptions.ErrorResponse;
import com.moonactive.exam.arielshkalimexem.echoattime.service.IExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    private IExamService examService;

    @PostMapping("/echoAtTime")
    public EchoAtTimeResponse postEchoAtTime(@RequestBody EchoAtTimeRequest request) {
        return examService.echoAtTime(request);
    }

    @ExceptionHandler(BaseServerException.class)
    public ResponseEntity<ErrorResponse> handleErrors(BaseServerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getTimestamp(),
                ex.getHttpStatus().value(),
                ex.getHttpStatus().name(),
                ex.getMessage()
        );

        LOGGER.warn("Exception caught in the controller [{}]", errorResponse);
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}

