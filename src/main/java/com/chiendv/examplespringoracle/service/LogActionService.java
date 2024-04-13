package com.chiendv.examplespringoracle.service;

import com.chiendv.examplespringoracle.entity.LogAction;

public interface LogActionService {
    LogAction saveLog(int type);
    LogAction findOne(Long id);
}
