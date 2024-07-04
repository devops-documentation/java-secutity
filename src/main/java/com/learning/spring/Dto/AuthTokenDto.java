package com.learning.spring.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthTokenDto {

	private String token;
	private String errorMessage;

	public AuthTokenDto(String token){
		this.token = token;
	}
}