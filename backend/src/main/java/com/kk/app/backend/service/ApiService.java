package com.kk.app.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kk.app.backend.dto.CreateTaskDto;
import com.kk.app.backend.entity.DashboardData;
import com.kk.app.backend.entity.Status;
import com.kk.app.backend.entity.UserEntity;
import com.kk.app.backend.repo.DashboardRepo;
import com.kk.app.backend.repo.UsersRepo;

@Component
public class ApiService {
	
	@Autowired
	DashboardRepo repo;
	
	@Autowired
	UsersRepo usersRepo;

	public List<DashboardData> getDashBoardData(String assignedUser) {
		// TODO Auto-generated method stub
		List<DashboardData> dashBoardDataList = new ArrayList<>();
		UserEntity user = usersRepo.findById(assignedUser).get();
		if(user.isAdmin()) {
			dashBoardDataList = repo.findAll();	
		}else {
		dashBoardDataList = repo.findByAssignedUser(assignedUser);
		}
		Collections.sort(dashBoardDataList, Comparator.comparing(DashboardData::getCreatedDate).reversed());
		return dashBoardDataList;
	}

	public List<DashboardData>  saveTask(CreateTaskDto data, String assignedUser) {
		DashboardData dashboardData = new DashboardData();
		dashboardData.setTaskDescription(data.getTaskDescription());
		dashboardData.setTaskName(data.getTaskName());
		dashboardData.setCreatedDate(LocalDateTime.now());
		dashboardData.setStatus(Status.CREATED.toString());
		dashboardData.setAssignedUser(data.getAssignedUser());
		repo.save(dashboardData);
		return getDashBoardData(assignedUser);
	}
	
	public List<DashboardData> updateTask(DashboardData data,String assignedUser){
		data.setCreatedDate(LocalDateTime.now());
		repo.save(data);
		return getDashBoardData(assignedUser);
	}

}
