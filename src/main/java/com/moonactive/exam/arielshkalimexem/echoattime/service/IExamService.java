package com.moonactive.exam.arielshkalimexem.echoattime.service;

import com.moonactive.exam.arielshkalimexem.echoattime.model.dto.EchoAtTimeRequest;
import com.moonactive.exam.arielshkalimexem.echoattime.model.dto.EchoAtTimeResponse;

public interface IExamService {

    EchoAtTimeResponse echoAtTime(EchoAtTimeRequest request);
}
