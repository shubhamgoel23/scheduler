package com.example.scheduler02.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Table(name = "job_details")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class JobDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String jobName;
	
	private long scheduleAt;

	private Object[] inputs;

	private String status;
	
	private long lastUpdateAt;

}
