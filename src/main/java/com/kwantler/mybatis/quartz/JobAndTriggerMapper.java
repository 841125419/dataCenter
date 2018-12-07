package com.kwantler.mybatis.quartz;



import com.kwantler.entity.JobAndTrigger;

import java.util.List;


public interface JobAndTriggerMapper {
	public List<JobAndTrigger> getJobAndTriggerDetails();
	public JobAndTrigger getJobAndTriggerDetailsByClassName(String className);
}
