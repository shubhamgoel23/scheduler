package com.example.scheduler02.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobDetailsRepository extends JpaRepository<JobDetails, String> {

	@Query(value = "SELECT * FROM job_details where status=:status and schedule_at<=:scheduleAt LIMIT 1 FOR UPDATE SKIP LOCKED", nativeQuery = true)
	JobDetails findByStatus(@Param("status") String status, @Param("scheduleAt") long scheduleAt);

}
