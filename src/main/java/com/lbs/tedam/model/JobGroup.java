/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.lbs.tedam.util.EnumsV2.JobStatus;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "JOB_GROUP")
public class JobGroup extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4378420222319078983L;

	@Column(name = "NAME", unique = true)
	@Size(min = 1, max = 255)
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IS_ACTIVE")
	private boolean active = false;

	@Column(name = "STATUS")
	private JobStatus status = JobStatus.NOT_STARTED;

	@Column(name = "LAST_EXECUTED_START_DATE")
	private LocalDateTime lastExecutedStartDate;

	@ManyToOne
	@JoinColumn(name = "LAST_EXECUTING_USER_ID")
	@Where(clause = "IS_DELETED=0")
	private TedamUser lastExecutingUser;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	@Where(clause = "IS_DELETED=0")
	private Project project;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Job> jobs = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public JobStatus getStatus() {
		return status;
	}

	public LocalDateTime getLastExecutedStartDate() {
		return lastExecutedStartDate;
	}

	public void setLastExecutedStartDate(LocalDateTime lastExecutedStartDate) {
		this.lastExecutedStartDate = lastExecutedStartDate;
	}

	public TedamUser getLastExecutingUser() {
		return lastExecutingUser;
	}

	public void setLastExecutingUser(TedamUser lastExecutingUser) {
		this.lastExecutingUser = lastExecutingUser;
	}

}
