package com.chiendv.examplespringoracle.service;

import com.chiendv.examplespringoracle.entity.LogAction;
import com.chiendv.examplespringoracle.repository.LogActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

@Service
public class LogActionServiceImpl implements LogActionService{
    private final LogActionRepository logActionRepository;
    private final EntityManager entityManager;

    @Autowired
    public LogActionServiceImpl(LogActionRepository logActionRepository,
                                EntityManager entityManager) {
        this.logActionRepository = logActionRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public LogAction saveLog(int type) {
        //LogAction logAction = logActionRepository.findById(1L).get();
        LogAction logAction = entityManager.find(LogAction.class, 1L, LockModeType.PESSIMISTIC_WRITE);
        if(type == 0) {
            logAction.setTotal(logAction.getTotal() - 12);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            logAction.setTotal(logAction.getTotal() + 5);
        }
        return logActionRepository.save(logAction);
    }

    @Override
    public LogAction findOne(Long id) {
        return logActionRepository.findById(id).get();
    }
}
