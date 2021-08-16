package com.example.scheduler02.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JobDispatcher {

	private final JobService jobService;

	@Value("${job.poolSize}")
	private int poolSize;

	public void executeJobs() {
		try {
			ExecutorService service = Executors.newFixedThreadPool(poolSize);
			for (int i = 0; i < poolSize; i++)
				service.execute(() -> jobService.processJob(System.currentTimeMillis()));
			service.shutdown();
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}

	}

}
