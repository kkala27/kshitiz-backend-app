package com.kk.app.backend.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kk.app.backend.dto.GenericResponseDto;
import com.kk.app.backend.entity.DashboardData;
import com.kk.app.backend.entity.UserEntity;
import com.kk.app.backend.repo.DashboardRepo;
import com.kk.app.backend.repo.UsersRepo;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {
	private final SendGrid sendGrid;
	private final String fromEmail;

	@Autowired
	DashboardRepo repo;

	@Autowired
	UsersRepo usersRepo;

	public EmailService(
			// get the SendGrid bean automatically created by Spring Boot
			@Autowired SendGrid sendGrid,
			// read your email to use as sender from application.properties
			@Value("${twilio.sendgrid.from-email}") String fromEmail) {
		this.sendGrid = sendGrid;
		this.fromEmail = fromEmail;
	}

	public void sendSingleEmail(String toEmail) {
		// specify the email details
		Email from = new Email(this.fromEmail);
		String subject = "Hello, World!";
		Email to = new Email(toEmail);
		Content content = new Content("text/plain", "Welcome to the Twilio SendGrid world!");

		// initialize the Mail helper class
		Mail mail = new Mail(from, subject, to, content);

		// send the single email
		sendEmail(mail);
	}

	private void sendEmail(Mail mail) {
		try {
			// set the SendGrid API endpoint details as described
			// in the doc (https://docs.sendgrid.com/api-reference/mail-send/mail-send)
			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			// perform the request and send the email
			Response response = sendGrid.api(request);
			int statusCode = response.getStatusCode();
			// if the status code is not 2xx
			if (statusCode < 200 || statusCode >= 300) {
				throw new RuntimeException(response.getBody());
			}
		} catch (IOException e) {
			// log the error message in case of network failures
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public GenericResponseDto sendReminderEmail(int id) {
		try {
			DashboardData data = repo.findById(id).get();
			UserEntity user = usersRepo.findById(data.getAssignedUser()).get();
			Email from = new Email("kkala27@gmail.com");
			if (data != null) {
				String dataRecord = data.toString();
				String subject = "Reminder for assigned task.";
				Email to = new Email(user.getEmailId());
				Content content = new Content("text/plain", dataRecord);

				// initialize the Mail helper class
				Mail mail = new Mail(from, subject, to, content);

				// send the single email
				sendEmail(mail);

			}
			GenericResponseDto response = new GenericResponseDto("Reminder email sent to " + user.getEmailId(), true);
			return response;
		} catch (Exception e) {
			GenericResponseDto response = new GenericResponseDto(
					"Failed to send Reminder email getting error: " + e.getMessage(), false);
			return response;
		}
	}

}