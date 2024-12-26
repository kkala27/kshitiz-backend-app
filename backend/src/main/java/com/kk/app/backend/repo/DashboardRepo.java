package com.kk.app.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kk.app.backend.entity.DashboardData;

@Repository
public interface DashboardRepo extends JpaRepository<DashboardData,Integer>{

	List<DashboardData> findByAssignedUser(String assignedUser);

}
