package com.reinaldo.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","token","status"})
public record AuthLoginResponse(
		String username, 
		String token, 
		Boolean status) {

}
