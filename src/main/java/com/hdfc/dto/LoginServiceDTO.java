package com.hdfc.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginServiceDTO {
	private String username;
	private String token;
	private Date loggedInAt;
}