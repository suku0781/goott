package com.miniproject.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Session {
	@NonNull
	private String userId; 
	private Timestamp sessionLimit;
	private String sessionKey;
	
}
