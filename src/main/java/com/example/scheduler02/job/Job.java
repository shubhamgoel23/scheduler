package com.example.scheduler02.job;

public interface Job {

	void execute(Object... obj);

	void create(long scheduleAt, Object... obj);
}
