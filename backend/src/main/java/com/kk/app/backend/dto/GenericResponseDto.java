package com.kk.app.backend.dto;

public class GenericResponseDto {

	String responseMessage;
	boolean result;

	public GenericResponseDto(String responseMessage, boolean result) {
		super();
		this.responseMessage = responseMessage;
		this.result = result;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}


}
