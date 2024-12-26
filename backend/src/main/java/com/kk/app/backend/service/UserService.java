package com.kk.app.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kk.app.backend.dto.GenericResponseDto;
import com.kk.app.backend.dto.UserDto;
import com.kk.app.backend.entity.UserEntity;
import com.kk.app.backend.repo.UsersRepo;

@Component
public class UserService {

	@Autowired
	UsersRepo repo;

	public List<UserDto> getAllUsers() {
		List<UserEntity> userEntityList = repo.findAll();
		List<UserDto> userDtoList = new ArrayList<>();
		if (!userEntityList.isEmpty()) {
			for (UserEntity userEntity : userEntityList) {
				userDtoList.add(new UserDto(userEntity.getName(), userEntity.getUserName(), userEntity.getEmailId(),
						null, "success",userEntity.isAdmin()));
			}
		}

		return userDtoList;
	}

	public UserDto getUserByUserName(String userName) {

		UserEntity userEntity = repo.findById(userName).get();
		return new UserDto(userEntity.getName(), userEntity.getUserName(), userEntity.getEmailId(),
				null, "success",userEntity.isAdmin());

	}

	public GenericResponseDto saveUser(UserDto userDto) {
		repo.save(userDto.getUserEntityFromUserDto(false));
		return new GenericResponseDto("success", true);
	}

	public UserDto login(UserDto userDto) {
		try {
			UserEntity userEntity = repo.findByUserNameAndPassword(userDto.userName(), userDto.password()).get();
			return new UserDto(userEntity.getName(), userEntity.getUserName(), userEntity.getEmailId(),
					null, "success",userEntity.isAdmin());
		} catch (Exception e) {
			return new UserDto(null, null, null, null, "failed",false);
		}
	}

}
