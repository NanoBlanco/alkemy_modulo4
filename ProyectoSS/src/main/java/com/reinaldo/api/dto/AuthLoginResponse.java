package com.reinaldo.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","message","status"})
public record AuthLoginResponse(
		String username,
		String message,
		boolean status
		) {

}
