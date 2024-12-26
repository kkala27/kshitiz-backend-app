package com.kk.app.backend.dto;

import com.kk.app.backend.entity.UserEntity;

public record UserDto(String name, String userName, String email, String password, String message, boolean isAdmin) {

	public UserEntity getUserEntityFromUserDto(boolean isAdmin) {
		UserEntity entity = new UserEntity();
		entity.setEmailId(email);
		entity.setName(name);
		entity.setPassword(password);
		entity.setUserName(userName);
		entity.setAdmin(isAdmin);
		return entity;
	}

}
