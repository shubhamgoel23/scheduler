package com.example.scheduler02.job;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.scheduler02.model.JobDetails;
import com.example.scheduler02.model.JobDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrintNumbers implements Job {

	private final JobDetailsRepository jobDetailsRepository;

	@Override
	public void execute(Object... obj) {
		long start = (Long) obj[0];
		long end = (Long) obj[1];
		for (long i = start; i <= end; i++) {
			System.out.println("Number: " + i);
		}
	}

	@Override
	public void create(long scheduleAt, Object... obj) {
		jobDetailsRepository.save(JobDetails.builder().id(UUID.randomUUID().toString()).jobName("printNumbers")
				.scheduleAt(scheduleAt).inputs(obj).status("PENDING").lastUpdateAt(System.currentTimeMillis()).build());

	}

}
