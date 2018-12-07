package com.kwantler.bo;


import com.github.pagehelper.PageInfo;
import com.kwantler.entity.JobAndTrigger;

public interface JobAndTriggerService {
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
