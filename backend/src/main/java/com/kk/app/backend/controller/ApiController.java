package com.kk.app.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.app.backend.dto.CreateTaskDto;
import com.kk.app.backend.dto.GenericResponseDto;
import com.kk.app.backend.dto.UserDto;
import com.kk.app.backend.entity.DashboardData;
import com.kk.app.backend.service.ApiService;
import com.kk.app.backend.service.EmailService;
import com.kk.app.backend.service.UserService;

@RestController
public class ApiController {

	@Autowired
	ApiService apiService;

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@CrossOrigin
	@GetMapping("/dashboard-data")
	public List<DashboardData> getDashBoardData(@RequestHeader Map<String, String> headers) {
        UserDto userInfo = getUserSession(headers);
		return apiService.getDashBoardData(userInfo.userName());
	}

	@PostMapping("/create-task")
	@CrossOrigin
	public List<DashboardData> createTask(@RequestBody CreateTaskDto data,@RequestHeader Map<String, String> headers) {
		UserDto userInfo = getUserSession(headers);
		return apiService.saveTask(data,userInfo.userName());
	}

	@PostMapping("/update-task")
	@CrossOrigin
	public List<DashboardData> updateTask(@RequestBody DashboardData data,@RequestHeader Map<String, String> headers) {
		UserDto userInfo = getUserSession(headers);
		return apiService.updateTask(data,userInfo.userName());
	}

	@CrossOrigin
	@GetMapping("/send-email")
	public String sendEmail() {
		emailService.sendSingleEmail("kkala27@gmail.com");
		return "Successfull";
	}

	@CrossOrigin
	@GetMapping("/send-reminder/{id}")
	public GenericResponseDto sendReminderEmail(@PathVariable int id) {
		
		return emailService.sendReminderEmail(id);

	}

	@PostMapping("/save-user")
	@CrossOrigin
	public GenericResponseDto saveUser(@RequestBody UserDto data) {

		return userService.saveUser(data);
	}

	@CrossOrigin
	@GetMapping("/list-users")
	public List<UserDto> listUsers() {
		return userService.getAllUsers();
	}

	@CrossOrigin
	@PostMapping("/login")
	public UserDto login(@RequestBody UserDto user) {
		return userService.login(user);
	}
	
	public UserDto getUserSession(Map<String, String> headers) {
		String userInfo = headers.get("usersession");
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			UserDto user = mapper.readValue(userInfo, UserDto.class);
			return user;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
}
