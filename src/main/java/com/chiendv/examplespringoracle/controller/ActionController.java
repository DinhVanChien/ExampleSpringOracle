package com.chiendv.examplespringoracle.controller;

import com.chiendv.examplespringoracle.entity.LogAction;
import com.chiendv.examplespringoracle.service.LogActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
public class ActionController {
    private final LogActionService logActionService;
    @Autowired
    public ActionController(LogActionService logActionService){
        this.logActionService = logActionService;
    }
    @PostMapping("/create")
    public String testTai(@RequestParam int type) {
        logActionService.saveLog(type);
        return "ok";
    }
    @PostMapping("/get")
    public LogAction testGet() {
        return logActionService.findOne(1L);
    }
}
