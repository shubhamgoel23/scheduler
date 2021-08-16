package com.example.scheduler02;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.scheduler02.job.PrintJob;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JobController {

	private final PrintJob printJob;

	@PutMapping(value = "/processJob")
	public ResponseEntity<Void> processJob(@RequestParam("number") long num) {
		printJob.create(System.currentTimeMillis(), num);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = "/scheduleJob")
	public ResponseEntity<Void> scheduleJob(@RequestParam("number") long num,
			@RequestParam("scheduletime") long milliseconds) {
		printJob.create(milliseconds, num);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
