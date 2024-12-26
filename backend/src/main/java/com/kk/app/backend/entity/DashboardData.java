package com.kk.app.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "DASHBOARD_DATA")
public class DashboardData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates the primary key value.
	private int id;
	private String taskName;
	private String taskDescription;
	private LocalDateTime createdDate;
	private String status;
	private String comments;
	@Column(name = "assigned_user", nullable = true)
	private String assignedUser;
	
	

	public String getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Task Details \nTask Name=" + taskName + " \nTask Description=" + taskDescription
				+ "\nCreated Date=" + createdDate + "\nStatus=" + status + "\nComments=" + comments ;
	}
	
	

}
