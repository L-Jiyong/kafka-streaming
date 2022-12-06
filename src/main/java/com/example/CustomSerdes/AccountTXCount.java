package com.example.CustomSerdes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountTXCount {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
	private LocalDateTime countDt = LocalDateTime.now();
	private String userId;
	private long count;

	public AccountTXCount(String userId, long count) {
		this.userId = userId;
		this.count = count;
	}
}