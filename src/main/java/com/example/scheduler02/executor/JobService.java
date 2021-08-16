package com.example.scheduler02.executor;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.scheduler02.job.Job;
import com.example.scheduler02.model.JobDetails;
import com.example.scheduler02.model.JobDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {

	private final ApplicationContext applicationContext;
	private final JobDetailsRepository jobDetailsRepository;

	@Transactional
	public void processJob(long endTime) {
		JobDetails jobDetails = jobDetailsRepository.findByStatus("PENDING", endTime);
		if (null == jobDetails)
			return;
		Job job = (Job) applicationContext.getBean(jobDetails.getJobName());
		job.execute(jobDetails.getInputs());
		jobDetails.setStatus("COMPLETED");
		jobDetails.setLastUpdateAt(System.currentTimeMillis());
	}

}
