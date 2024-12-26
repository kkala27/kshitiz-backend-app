package com.kk.app.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.app.backend.entity.UserEntity;

public interface UsersRepo extends JpaRepository<UserEntity,String>{

	Optional<UserEntity> findByUserNameAndPassword(String userName, String password);

}
