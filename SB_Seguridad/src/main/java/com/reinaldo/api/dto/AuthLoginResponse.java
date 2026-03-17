package com.reinaldo.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","token","refreshToken","status"})
public record AuthLoginResponse(
		String username, 
		String token, 
		String refreshToken,
		Boolean status) {

}
