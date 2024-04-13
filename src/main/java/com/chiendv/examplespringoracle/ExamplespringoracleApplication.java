package com.chiendv.examplespringoracle;

import com.chiendv.examplespringoracle.exception.TpbException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAsync
@EnableJpaAuditing
public class ExamplespringoracleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamplespringoracleApplication.class, args);
//        Map<String, String> test = new TreeMap<>();
//        String a = "A";
//        String b = "B";
//       // test.put(null, a);
//        test.put("A", a);
//        test.put("B", a);
//        for(String k : test.keySet()) {
//            System.out.println(k +" " +test.get(k));
//        }
    }

}
