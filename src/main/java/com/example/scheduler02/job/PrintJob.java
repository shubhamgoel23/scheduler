package com.example.scheduler02.job;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.scheduler02.model.JobDetails;
import com.example.scheduler02.model.JobDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrintJob implements Job {

	private final JobDetailsRepository jobDetailsRepository;
	private final PrintNumbers printNumbers;

	@Override
	public void execute(Object... obj) {
		int processors = 10;
		long number = (Long) obj[0];
		long batchSize = number / processors + ((number % processors == 0) ? 0 : 1);
		long start = 0;
		long end = batchSize;
		while (end <= number) {
			long i = start + 1;
			long j = end;
			printNumbers.create(System.currentTimeMillis(), i, j);
			start = end;
			end += batchSize;
			if (start == number)
				break;
			if (end > number)
				end = number;
		}
	}

	@Override
	public void create(long scheduleAt, Object... obj) {
		jobDetailsRepository.save(JobDetails.builder().id(UUID.randomUUID().toString()).jobName("printJob")
				.scheduleAt(scheduleAt).inputs(obj).status("PENDING").lastUpdateAt(System.currentTimeMillis()).build());

	}

}
