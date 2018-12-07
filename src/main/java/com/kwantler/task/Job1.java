package com.kwantler.task;

import com.kwantler.task.job.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@DisallowConcurrentExecution
public class Job1 implements BaseJob {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new Date()+"进入Job1");
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("http://localhost:8080/aliveResp?a=1&b=23",Map.class);
        Map<String,Object> map = responseEntity.getBody();
        System.out.println(map);

    }
}
