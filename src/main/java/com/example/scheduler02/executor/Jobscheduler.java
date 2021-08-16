package com.example.scheduler02.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Jobscheduler {

	@Value("${job.poolSize}")
	private int poolSize;

	@Value("${spring.datasource.url}")
	private String datasource;
	
	private final JobDispatcher jobDispatcher;

	private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

	@PostConstruct
	public void scheduleJobDispatcher() {
		System.out.println(datasource);
		scheduler.scheduleAtFixedRate(() -> jobDispatcher.executeJobs(), 5l, 5l, TimeUnit.SECONDS);
	}
}
